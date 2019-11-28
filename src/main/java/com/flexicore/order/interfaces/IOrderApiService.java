package com.flexicore.order.interfaces;

import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.Order;
import com.flexicore.security.SecurityContext;

import java.util.List;

public interface IOrderApiService extends ServicePlugin {

    class OrderItem extends com.flexicore.order.model.OrderItem {
        private String customerId;

        public String getCustomerId() {
            return customerId;
        }

        public <T extends OrderItem> T setCustomerId(String customerId) {
            this.customerId = customerId;
            return (T) this;
        }
    }

    String implementorCanonicalName = null;

    void sendOrder(Order order, List<OrderItem> orderItems, SecurityContext securityContext) throws Exception;

}
