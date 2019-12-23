package com.flexicore.order.service;

import com.amazonaws.util.StringUtils;
import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.model.Baseclass;
import com.flexicore.order.data.OrderApiConfigRepository;
import com.flexicore.order.interfaces.IOrderApiConfigBaseService;
import com.flexicore.order.interfaces.IOrderApiConfigService;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.CreateOrderApiConfig;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.order.request.UpdateOrderApiConfig;
import com.flexicore.organization.model.Supplier;
import com.flexicore.security.SecurityContext;
import com.flexicore.service.PluginService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@PluginInfo(version = 1)
public class OrderApiConfigService implements IOrderApiConfigService {

    @Inject
    private PluginService pluginService;

    @Inject
    @PluginInfo(version = 1)
    private OrderApiConfigRepository repository;

    @Override
    public void validate(CreateOrderApiConfig createOrderApiConfig, SecurityContext securityContext) {
        createOrderApiConfig.setSupplier(this.repository.getByIdOrNull(createOrderApiConfig.getSupplierId(), Supplier.class, null , securityContext));
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

    @Override
    public OrderApiConfig createOrderApiConfig(CreateOrderApiConfig createOrderApiConfig, SecurityContext securityContext) {
        List<IOrderApiConfigBaseService> plugins = new ArrayList<>((Collection<IOrderApiConfigBaseService>) pluginService.getPlugins(IOrderApiConfigBaseService.class, null, null));
        try {
            Class<? extends CreateOrderApiConfig> aClass = createOrderApiConfig.getClass();
            List<IOrderApiConfigBaseService> suitable = plugins.parallelStream().filter(f -> aClass.equals(f.getCreateContainerType())).collect(Collectors.toList());
            if (suitable.isEmpty()) {
                throw new BadRequestException("No service is suitable to handle request:" + aClass);
            }
            IOrderApiConfigBaseService orderApiConfigImplementor = suitable.get(0);
            OrderApiConfig orderApiConfig = orderApiConfigImplementor.createNoMerge(createOrderApiConfig, securityContext);
            repository.merge(orderApiConfig);
            return orderApiConfig;
        } finally {

            for (IOrderApiConfigBaseService plugin : plugins) {
                pluginService.cleanUpInstance(plugin);
            }
        }
    }

    @Override
    public void validate(UpdateOrderApiConfig updateOrderApiConfig, SecurityContext securityContext) {
        throw new NotImplementedException();
    }

    @Override
    public OrderApiConfig updateOrderApiConfig(UpdateOrderApiConfig updateOrderApiConfig, SecurityContext securityContext) {
        return null;
    }

    @Override
    public void validate(OrderApiConfigFiltering filtering, SecurityContext securityContext) {

    }

    @Override
    public List<OrderApiConfig> getOrderApiConfigs(OrderApiConfigFiltering filtering, SecurityContext securityContext) {
        return repository.listAllOrderApiConfig(securityContext, filtering);
    }
}
