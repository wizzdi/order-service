package com.flexicore.order.interfaces;

import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.CreateOrder;
import com.flexicore.order.request.CreateOrderApiConfig;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.order.request.UpdateOrderApiConfig;
import com.flexicore.security.SecurityContext;

import java.util.List;

public interface IOrderApiConfigService extends ServicePlugin {
    void validate(CreateOrderApiConfig createOrderApiConfig, SecurityContext securityContext);
    OrderApiConfig createOrderApiConfig(CreateOrderApiConfig createOrderApiConfig, SecurityContext securityContext);
    void validate(UpdateOrderApiConfig updateOrderApiConfig, SecurityContext securityContext);
    OrderApiConfig updateOrderApiConfig(UpdateOrderApiConfig updateOrderApiConfig, SecurityContext securityContext);
    void validate(OrderApiConfigFiltering filtering, SecurityContext securityContext);
    List<OrderApiConfig> getOrderApiConfigs(OrderApiConfigFiltering filtering, SecurityContext securityContext);
}
