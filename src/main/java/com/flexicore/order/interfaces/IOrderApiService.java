package com.flexicore.order.interfaces;

import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.CreateOrderApiConfig;
import com.flexicore.order.request.SendOrder;
import com.flexicore.security.SecurityContext;

import java.util.List;

public interface IOrderApiService extends ServicePlugin {

    Class<? extends OrderApiConfig> getConfigurationType();
    Class<? extends CreateOrderApiConfig> getCreateContainerType();
    OrderApiConfig createNoMerge(CreateOrderApiConfig createOrderApiConfig, SecurityContext securityContext);
    boolean updateNoMerge(CreateOrderApiConfig createOrderApiConfig, OrderApiConfig orderApiConfig);
    Order sendOrder(SendOrder sendOrder, SecurityContext securityContext);
}
