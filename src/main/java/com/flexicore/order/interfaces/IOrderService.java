package com.flexicore.order.interfaces;

import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.Order;
import com.flexicore.order.request.CreateOrder;
import com.flexicore.order.request.OrderFiltering;
import com.flexicore.order.request.SendOrder;
import com.flexicore.order.request.UpdateOrder;
import com.flexicore.security.SecurityContext;

import java.util.List;

public interface IOrderService extends ServicePlugin {
    PaginationResponse<Order> getAllOrders(OrderFiltering orderFiltering, SecurityContext securityContext);

    List<Order> listAllOrders(OrderFiltering orderFiltering, SecurityContext securityContext);

    void validate(OrderFiltering orderFiltering, SecurityContext securityContext);

    void validate(CreateOrder order, SecurityContext securityContext);

    Order createOrder(CreateOrder createOrder, SecurityContext securityContext);

    Order updateOrder(UpdateOrder updateOrder, SecurityContext securityContext);

    Order createOrderNoMerge(CreateOrder createOrder, SecurityContext securityContext);

    boolean updateOrderNoMerge(Order order, CreateOrder createOrder);

    void validate(SendOrder sendOrder, SecurityContext securityContext);
    //    Order sendOrder(SendOrder sendOrder, List<IOrderApiService.OrderItem> orderItems, SecurityContext securityContext);
    Order sendOrder(Order order, List<IOrderApiService.OrderItem> orderItems, SecurityContext securityContext);
}
