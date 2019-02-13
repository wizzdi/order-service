package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.order.model.Order;

public class UpdateOrder extends CreateOrder{

    private String id;
    @JsonIgnore
    private Order order;

    public String getId() {
        return id;
    }

    public <T extends UpdateOrder> T setId(String id) {
        this.id = id;
        return (T) this;
    }

    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    public <T extends UpdateOrder> T setOrder(Order order) {
        this.order = order;
        return (T) this;
    }
}
