package com.flexicore.order.request;

import com.flexicore.model.FilteringInformationHolder;

import java.util.Set;

public class OrderFiltering extends FilteringInformationHolder {

    private Set<String> externalIds;

    public Set<String> getExternalIds() {
        return externalIds;
    }

    public <T extends OrderFiltering> T setExternalIds(Set<String> externalIds) {
        this.externalIds = externalIds;
        return (T) this;
    }
}
