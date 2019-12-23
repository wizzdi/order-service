package com.flexicore.order.interfaces;

import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.CreateOrderApiConfig;
import com.flexicore.security.SecurityContext;

public interface IOrderApiConfigBaseService extends ServicePlugin {
    boolean isInit();
    InitResponse init(OrderApiConfig orderApiConfig);

    Class<? extends OrderApiConfig> getConfigurationType();
    Class<? extends CreateOrderApiConfig> getCreateContainerType();
    OrderApiConfig createNoMerge(CreateOrderApiConfig createOrderApiConfig, SecurityContext securityContext);
    boolean updateNoMerge(CreateOrderApiConfig createOrderApiConfig, OrderApiConfig orderApiConfig);
}
