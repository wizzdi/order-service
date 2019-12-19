package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.organization.model.Supplier;

public class UnlinkOrderApiConfigToSupplierRequest {
    private String orderApiConfigId;
    @JsonIgnore
    private OrderApiConfig orderApiConfig;
    private  String supplierId;
    @JsonIgnore
    private Supplier supplier;

    public String getOrderApiConfigId() {
        return orderApiConfigId;
    }

    public <T extends UnlinkOrderApiConfigToSupplierRequest> T setOrderApiConfigId(String orderApiConfigId) {
        this.orderApiConfigId = orderApiConfigId;
        return (T) this;
    }

    public OrderApiConfig getOrderApiConfig() {
        return orderApiConfig;
    }

    public <T extends UnlinkOrderApiConfigToSupplierRequest> T setOrderApiConfig(OrderApiConfig orderApiConfig) {
        this.orderApiConfig = orderApiConfig;
        return (T) this;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public <T extends UnlinkOrderApiConfigToSupplierRequest> T setSupplierId(String supplierId) {
        this.supplierId = supplierId;
        return (T) this;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public <T extends UnlinkOrderApiConfigToSupplierRequest> T setSupplier(Supplier supplier) {
        this.supplier = supplier;
        return (T) this;
    }
}
