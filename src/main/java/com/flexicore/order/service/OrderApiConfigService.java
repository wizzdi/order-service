package com.flexicore.order.service;

import com.flexicore.order.interfaces.IOrderApiConfigService;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.CreateOrderApiConfig;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.order.request.UpdateOrderApiConfig;

import java.util.List;

public class OrderApiConfigService implements IOrderApiConfigService {
    @Override
    public OrderApiConfig createOrderApiConfig(CreateOrderApiConfig orderApiConfig) {
        return null;
    }

    @Override
    public OrderApiConfig updateOrderApiConfig(UpdateOrderApiConfig orderApiConfig) {
        return null;
    }

    @Override
    public List<OrderApiConfig> getOrderApiConfigs(OrderApiConfigFiltering filtering) {
        return null;
    }

}
