package com.flexicore.order.interfaces;

import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.CreateOrderApiConfig;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.order.request.UpdateOrderApiConfig;

import java.util.List;

public interface IOrderApiConfigService extends ServicePlugin {
    OrderApiConfig createOrderApiConfig(CreateOrderApiConfig orderApiConfig);
    OrderApiConfig updateOrderApiConfig(UpdateOrderApiConfig orderApiConfig);
    List<OrderApiConfig> getOrderApiConfigs(OrderApiConfigFiltering filtering);
}
