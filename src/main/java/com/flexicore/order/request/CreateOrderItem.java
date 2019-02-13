package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.order.model.Order;
import com.flexicore.product.model.Product;

public class CreateOrderItem {
    private String name;
    private String description;
    private String orderId;
    @JsonIgnore
    private Order order;
    private String productId;
    @JsonIgnore
    private Product product;
    private Integer quantity;


    public String getName() {
        return name;
    }

    public <T extends CreateOrderItem> T setName(String name) {
        this.name = name;
        return (T) this;
    }

    public String getDescription() {
        return description;
    }

    public <T extends CreateOrderItem> T setDescription(String description) {
        this.description = description;
        return (T) this;
    }

    public String getOrderId() {
        return orderId;
    }

    public <T extends CreateOrderItem> T setOrderId(String orderId) {
        this.orderId = orderId;
        return (T) this;
    }

    @JsonIgnore
    public Order getOrder() {
        return order;
    }

    public <T extends CreateOrderItem> T setOrder(Order order) {
        this.order = order;
        return (T) this;
    }

    public String getProductId() {
        return productId;
    }

    public <T extends CreateOrderItem> T setProductId(String productId) {
        this.productId = productId;
        return (T) this;
    }

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public <T extends CreateOrderItem> T setProduct(Product product) {
        this.product = product;
        return (T) this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public <T extends CreateOrderItem> T setQuantity(Integer quantity) {
        this.quantity = quantity;
        return (T) this;
    }
}
