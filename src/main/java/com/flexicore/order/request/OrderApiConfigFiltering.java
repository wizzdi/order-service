package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.model.FilteringInformationHolder;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.organization.model.Supplier;

import java.util.HashSet;
import java.util.Set;

public class OrderApiConfigFiltering extends FilteringInformationHolder {
    private Set<String> supplierIds = new HashSet<>();
    private Set<String> orderApiConfigIds = new HashSet<>();
    @JsonIgnore
    private Set<Supplier> suppliers;
    @JsonIgnore
    private Set<OrderApiConfig> orderApiConfigs;

    public Set<String> getSupplierIds() {
        return supplierIds;
    }

    public <T extends OrderApiConfigFiltering> T setSupplierIds(Set<String> supplierIds) {
        this.supplierIds = supplierIds;
        return (T) this;
    }

    public Set<String> getOrderApiConfigIds() {
        return orderApiConfigIds;
    }

    public <T extends OrderApiConfigFiltering> T setOrderApiConfigIds(Set<String> orderApiConfigIds) {
        this.orderApiConfigIds = orderApiConfigIds;
        return (T) this;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public <T extends OrderApiConfigFiltering> T setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
        return (T) this;
    }

    public Set<OrderApiConfig> getOrderApiConfigs() {
        return orderApiConfigs;
    }

    public <T extends OrderApiConfigFiltering> T setOrderApiConfigs(Set<OrderApiConfig> orderApiConfigs) {
        this.orderApiConfigs = orderApiConfigs;
        return (T) this;
    }
}


