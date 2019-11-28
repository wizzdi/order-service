package com.flexicore.order.request;

import com.flexicore.order.model.OrderApiConfig;

public class CreateOrderApiConfig {
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

}
