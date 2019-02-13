package com.flexicore.order.request;

public class CreateOrder {
    private String name;
    private String description;
    private String externalId;


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
}
