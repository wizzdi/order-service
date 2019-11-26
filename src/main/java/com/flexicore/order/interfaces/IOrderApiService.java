package com.flexicore.order.interfaces;
import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.Order;

public interface IOrderApiService extends ServicePlugin {
    boolean sendOrder(Order order);
    boolean CheckImplementorCanonicalName(String implementorCanonicalName);
}
