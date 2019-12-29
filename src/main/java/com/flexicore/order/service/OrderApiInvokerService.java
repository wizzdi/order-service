package com.flexicore.order.service;

import com.amazonaws.util.StringUtils;
import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.order.data.OrderApiRepository;
import com.flexicore.order.data.OrderItemRepository;
import com.flexicore.order.interfaces.IOrderApiInvokerService;
import com.flexicore.order.interfaces.IOrderApiService;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.CreateOrderApiConfig;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.order.request.SendOrder;
import com.flexicore.order.request.UpdateOrderApiConfig;
import com.flexicore.organization.model.Supplier;
import com.flexicore.security.SecurityContext;
import com.flexicore.service.PluginService;
import org.apache.http.HttpException;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@PluginInfo(version = 1)
public class OrderApiInvokerService implements IOrderApiInvokerService {

    @Inject
    private Logger logger;

    @Inject
    private PluginService pluginService;

    @Inject
    @PluginInfo(version = 1)
    private OrderApiRepository repository;

    @Inject
    @PluginInfo(version = 1)
    private OrderItemRepository orderItemRepository;

    @Override
    public void validate(CreateOrderApiConfig createOrderApiConfig, SecurityContext securityContext) {
        createOrderApiConfig.setSupplier(this.repository.getByIdOrNull(createOrderApiConfig.getSupplierId(), Supplier.class, null, securityContext));
        if (createOrderApiConfig.getSupplier() == null) {
            throw new BadRequestException("No Supplier with id " + createOrderApiConfig.getSupplierId());
        }
        if (StringUtils.isNullOrEmpty(createOrderApiConfig.getHost())) {
            throw new BadRequestException("Host in not provided");
        }
        if (StringUtils.isNullOrEmpty(createOrderApiConfig.getUsername())) {
            throw new BadRequestException("Username in not provided");
        }
        if (StringUtils.isNullOrEmpty(createOrderApiConfig.getPassword())) {
            throw new BadRequestException("Password in not provided");
        }
    }

    private boolean updateNoMerge(CreateOrderApiConfig createOrderApiConfig, OrderApiConfig orderApiConfig) {
        boolean update = false;
        if (createOrderApiConfig.getSupplier() != null && !createOrderApiConfig.getSupplierId().equals(orderApiConfig.getSupplierId())) {
            orderApiConfig.setSupplier(createOrderApiConfig.getSupplier());
            orderApiConfig.setSupplierId(createOrderApiConfig.getSupplierId());
            update = true;
        }
        if (createOrderApiConfig.getHost() != null && !createOrderApiConfig.getHost().equals(orderApiConfig.getHost())) {
            orderApiConfig.setHost(createOrderApiConfig.getHost());
            update = true;
        }
        if (createOrderApiConfig.getUsername() != null && !createOrderApiConfig.getUsername().equals(orderApiConfig.getUsername())) {
            orderApiConfig.setUsername(createOrderApiConfig.getUsername());
            update = true;
        }
        if (createOrderApiConfig.getPassword() != null && !createOrderApiConfig.getPassword().equals(orderApiConfig.getPassword())) {
            orderApiConfig.setPassword(createOrderApiConfig.getPassword());
            update = true;
        }

        return update;
    }


    private IOrderApiService getOrderApiConfigImplementor(CreateOrderApiConfig createOrderApiConfig) {
        List<IOrderApiService> plugins = new ArrayList<>((Collection<IOrderApiService>) pluginService.getPlugins(IOrderApiService.class, null, null));
        IOrderApiService result = null;
        try {
            Class<? extends CreateOrderApiConfig> aClass = createOrderApiConfig.getClass();
            List<IOrderApiService> suitable = plugins.parallelStream().filter(f -> aClass.equals(f.getCreateContainerType())).collect(Collectors.toList());
            if (suitable.isEmpty()) {
                throw new BadRequestException("No service is suitable to handle request:" + aClass);
            }
            result = suitable.get(0);
            return result;
        } finally {
            for (IOrderApiService plugin : plugins) {
                if (plugin != result) {
                    pluginService.cleanUpInstance(plugin);
                }
            }
        }
    }

    @Override
    public OrderApiConfig createOrderApiConfig(CreateOrderApiConfig createOrderApiConfig, SecurityContext securityContext) {
        IOrderApiService orderApiConfigImplementor = this.getOrderApiConfigImplementor(createOrderApiConfig);
        try {
            OrderApiConfig orderApiConfig = orderApiConfigImplementor.createNoMerge(createOrderApiConfig, securityContext);
            this.updateNoMerge(createOrderApiConfig, orderApiConfig);
            repository.merge(orderApiConfig);
            return orderApiConfig;
        } finally {
            if (orderApiConfigImplementor != null) {
                pluginService.cleanUpInstance(orderApiConfigImplementor);
            }
        }
    }

    @Override
    public void validate(UpdateOrderApiConfig updateOrderApiConfig, SecurityContext securityContext) {
        OrderApiConfig orderApiConfig = repository.getById(updateOrderApiConfig.getId(), OrderApiConfig.class, null, securityContext);
        updateOrderApiConfig.setOrderApiConfig(orderApiConfig);
        validate(updateOrderApiConfig.getCreateOrderApiConfig(), securityContext);
    }

    @Override
    public OrderApiConfig updateOrderApiConfig(UpdateOrderApiConfig updateOrderApiConfig, SecurityContext securityContext) {
        IOrderApiService orderApiConfigImplementor = this.getOrderApiConfigImplementor(updateOrderApiConfig.getCreateOrderApiConfig());
        try {
            OrderApiConfig orderApiConfig = updateOrderApiConfig.getOrderApiConfig();
            boolean updated = orderApiConfigImplementor.updateNoMerge(updateOrderApiConfig.getCreateOrderApiConfig(), orderApiConfig);
            updated = updated || this.updateNoMerge(updateOrderApiConfig.getCreateOrderApiConfig(), orderApiConfig);
            if (updated) {
                repository.merge(orderApiConfig);
            }
            return orderApiConfig;
        } finally {
            if (orderApiConfigImplementor != null) {
                pluginService.cleanUpInstance(orderApiConfigImplementor);
            }
        }
    }

    @Override
    public void validate(OrderApiConfigFiltering filtering, SecurityContext securityContext) {

    }

    @Override
    public List<OrderApiConfig> getOrderApiConfigs(OrderApiConfigFiltering filtering, SecurityContext securityContext) {
        return repository.listAllOrderApiConfig(securityContext, filtering);
    }

    @Override
    public void validate(SendOrder sendOrder, SecurityContext securityContext) {
        String orderId = sendOrder.getOrderId();
        Order order = repository.getByIdOrNull(orderId, Order.class, null, securityContext);
        if (order == null) {
            throw new BadRequestException("No Order with id " + orderId);
        }
        sendOrder.setOrder(order);
        if (order.getOrderSentDate() != null) {
            throw new BadRequestException("Order is already sent");
        }

        OrderApiConfig orderApiConfig = repository.getByIdOrNull(sendOrder.getOrderApiConfigId(), OrderApiConfig.class, null, securityContext);
        if (orderApiConfig == null) {
            throw new BadRequestException("No OrderApiConfig with id " + orderId);
        }
        sendOrder.setOrderApiConfig(orderApiConfig);
    }

    @Override
    public void sendOrder(SendOrder sendOrder, SecurityContext securityContext) {
        List<IOrderApiService> plugins = new ArrayList<>((Collection<IOrderApiService>) pluginService.getPlugins(IOrderApiService.class, null, null));
        try {
            Class<? extends OrderApiConfig> aClass = sendOrder.getOrderApiConfig().getClass();
            List<IOrderApiService> suitable = plugins.parallelStream().filter(f -> aClass.equals(f.getConfigurationType())).collect(Collectors.toList());
            if (suitable.isEmpty()) {
                throw new BadRequestException("No service is suitable to handle request:" + aClass);
            }
            IOrderApiService orderApiService = suitable.get(0);
            orderApiService.sendOrder(sendOrder, securityContext);
            Order order = sendOrder.getOrder();
            order.setOrderSentDate(LocalDateTime.now());
            repository.merge(order);
        } catch (Exception ex) {
            String error = "Failed to send order to provider, orderId: " + sendOrder.getOrderId();
            logger.log(Level.SEVERE, error, ex);
            throw new BadRequestException(error);
        } finally {
            for (IOrderApiService plugin : plugins) {
                pluginService.cleanUpInstance(plugin);
            }
        }
    }
}
