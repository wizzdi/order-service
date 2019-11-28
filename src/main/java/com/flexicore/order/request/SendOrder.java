package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.order.interfaces.IOrderApiService;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderItem;
import com.flexicore.product.model.Product;

import java.util.List;

public class SendOrder {

    private String id;
    @JsonIgnore
    private Order order;
    private List<IOrderApiService.OrderItem> orderItems;

    public String getId() {
        return id;
    }

    public <T extends SendOrder> T setId(String id) {
        this.id = id;
        return (T) this;
    }

    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    public <T extends SendOrder> T setOrder(Order order) {
        this.order = order;
        return (T) this;
    }


}
