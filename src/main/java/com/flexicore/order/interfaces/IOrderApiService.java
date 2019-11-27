package com.flexicore.order.interfaces;
import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.Order;
import com.flexicore.security.SecurityContext;

public interface IOrderApiService extends ServicePlugin {
    class Credentials {
        public String host;
        public String username;
        public String password;
    }
    void sendOrder(SecurityContext securityContext, Credentials providerCredentials, Order order) throws Exception;
    boolean IsMatchImplementorCanonicalName(String implementorCanonicalName);
}
