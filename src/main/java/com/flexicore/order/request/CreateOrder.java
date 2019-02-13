package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.organization.model.Organization;
import com.flexicore.organization.model.Supplier;

public class CreateOrder {
    private String name;
    private String description;
    private String externalId;
    private String consumingOrganizationId;
    @JsonIgnore
    private Organization consumingOrganization;
    private String supplierId;
    @JsonIgnore
    private Supplier supplier;


    public String getName() {
        return name;
    }

    public <T extends CreateOrder> T setName(String name) {
        this.name = name;
        return (T) this;
    }

    public String getDescription() {
        return description;
    }

    public <T extends CreateOrder> T setDescription(String description) {
        this.description = description;
        return (T) this;
    }

    public String getExternalId() {
        return externalId;
    }

    public <T extends CreateOrder> T setExternalId(String externalId) {
        this.externalId = externalId;
        return (T) this;
    }

    public String getConsumingOrganizationId() {
        return consumingOrganizationId;
    }

    public <T extends CreateOrder> T setConsumingOrganizationId(String consumingOrganizationId) {
        this.consumingOrganizationId = consumingOrganizationId;
        return (T) this;
    }

    @JsonIgnore
    public Organization getConsumingOrganization() {
        return consumingOrganization;
    }

    public <T extends CreateOrder> T setConsumingOrganization(Organization consumingOrganization) {
        this.consumingOrganization = consumingOrganization;
        return (T) this;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public <T extends CreateOrder> T setSupplierId(String supplierId) {
        this.supplierId = supplierId;
        return (T) this;
    }

    @JsonIgnore
    public Supplier getSupplier() {
        return supplier;
    }

    public <T extends CreateOrder> T setSupplier(Supplier supplier) {
        this.supplier = supplier;
        return (T) this;
    }
}
