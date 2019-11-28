package com.flexicore.order.request;

public class UpdateOrderApiConfig extends CreateOrderApiConfig {
    private String id;

    public String getId() {
        return id;
    }

    public <T extends UpdateOrderApiConfig> T setId(String id) {
        this.id = id;
        return (T) this;
    }
}
