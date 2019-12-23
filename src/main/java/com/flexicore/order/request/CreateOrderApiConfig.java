package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.flexicore.data.jsoncontainers.CrossLoaderResolver;
import com.flexicore.organization.model.Supplier;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,property = "type")
@JsonTypeIdResolver(CrossLoaderResolver.class)
public class CreateOrderApiConfig {
    private String supplierId;
    @JsonIgnore
    private Supplier supplier;
    private String host;
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public <T extends CreateOrderApiConfig> T setHost(String host) {
        this.host = host;
        return (T) this;
    }

    public String getUsername() {
        return username;
    }

    public <T extends CreateOrderApiConfig> T setUsername(String username) {
        this.username = username;
        return (T) this;
    }

    public String getPassword() {
        return password;
    }

    public <T extends CreateOrderApiConfig> T setPassword(String password) {
        this.password = password;
        return (T) this;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public <T extends CreateOrderApiConfig> T setSupplierId(String supplierId) {
        this.supplierId = supplierId;
        return (T) this;
    }

    @JsonIgnore
    public Supplier getSupplier() {
        return supplier;
    }

    public <T extends CreateOrderApiConfig> T setSupplier(Supplier supplier) {
        this.supplier = supplier;
        return (T) this;
    }

}
