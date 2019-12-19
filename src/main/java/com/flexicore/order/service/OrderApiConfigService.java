package com.flexicore.order.service;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.order.data.OrderApiConfigRepository;
import com.flexicore.order.interfaces.IOrderApiConfigBaseService;
import com.flexicore.order.interfaces.IOrderApiConfigService;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.CreateOrderApiConfig;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.order.request.UpdateOrderApiConfig;
import com.flexicore.security.SecurityContext;
import com.flexicore.service.PluginService;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@PluginInfo(version = 1)
public class OrderApiConfigService implements IOrderApiConfigService {

    @Inject
    @PluginInfo(version = 1)
    private PluginService pluginService;

    @Inject
    @PluginInfo(version = 1)
    private OrderApiConfigRepository repository;

    @Override
    public OrderApiConfig createOrderApiConfig(CreateOrderApiConfig createOrderApiConfig, SecurityContext securityContext) {
        List<IOrderApiConfigBaseService> plugins = new ArrayList<>((Collection<IOrderApiConfigBaseService>) pluginService.getPlugins(OrderApiConfigService.class, null, null));
        try {
            Class<? extends CreateOrderApiConfig> aClass = createOrderApiConfig.getClass();
            List<IOrderApiConfigBaseService> suitable = plugins.parallelStream().filter(f -> aClass.equals(f.getCreateContainerType())).collect(Collectors.toList());
            if (suitable.isEmpty()) {
                throw new BadRequestException("No OrderApiConfigService is suitable to handle " + aClass);
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
    public OrderApiConfig updateOrderApiConfig(UpdateOrderApiConfig orderApiConfig, SecurityContext securityContext) {
        return null;
    }

    @Override
    public List<OrderApiConfig> getOrderApiConfigs(OrderApiConfigFiltering filtering, SecurityContext securityContext) {
        return null;
    }
}
