package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.order.model.OrderApiConfig;

public class UpdateOrderApiConfig {
    private String id;
    private CreateOrderApiConfig createOrderApiConfig;
    @JsonIgnore
    private OrderApiConfig orderApiConfig;

    public String getId() {
        return id;
    }

    public <T extends UpdateOrderApiConfig> T setId(String id) {
        this.id = id;
        return (T) this;
    }

    @JsonIgnore
    public OrderApiConfig getOrderApiConfig() {
        return orderApiConfig;
    }

    public <T extends UpdateOrderApiConfig> T setOrderApiConfig(OrderApiConfig orderApiConfig) {
        this.orderApiConfig = orderApiConfig;
        return (T) this;
    }

    public CreateOrderApiConfig getCreateOrderApiConfig() {
        return createOrderApiConfig;
    }

    public <T extends UpdateOrderApiConfig> T setCreateOrderApiConfig(CreateOrderApiConfig createOrderApiConfig) {
        this.createOrderApiConfig = createOrderApiConfig;
        return (T) this;
    }
}
