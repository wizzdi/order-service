package com.flexicore.order.interfaces;

import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.OrderItem;
import com.flexicore.order.request.CreateOrderItem;
import com.flexicore.order.request.OrderItemFiltering;
import com.flexicore.order.request.UpdateOrderItem;
import com.flexicore.security.SecurityContext;

import java.util.List;

public interface IOrderItemService extends ServicePlugin {
    PaginationResponse<OrderItem> getAllOrderItems(OrderItemFiltering orderItemFiltering, SecurityContext securityContext);

    List<OrderItem> listAllOrderItems(OrderItemFiltering orderItemFiltering, SecurityContext securityContext);

    void validate(OrderItemFiltering orderItemFiltering, SecurityContext securityContext);

    void validate(CreateOrderItem createOrderItem, SecurityContext securityContext);

    OrderItem createOrderItem(CreateOrderItem createOrderItem, SecurityContext securityContext);

    OrderItem updateOrderItem(UpdateOrderItem updateOrderItem, SecurityContext securityContext);

    OrderItem createOrderItemNoMerge(CreateOrderItem createOrderItem, SecurityContext securityContext);

    boolean updateOrderItemNoMerge(OrderItem orderItem, CreateOrderItem createOrderItem);
}
