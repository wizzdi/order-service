package com.flexicore.order.service;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.model.Baseclass;
import com.flexicore.order.data.OrderRepository;
import com.flexicore.order.model.Order;
import com.flexicore.order.request.CreateOrder;
import com.flexicore.order.request.OrderFiltering;
import com.flexicore.order.request.UpdateOrder;
import com.flexicore.security.SecurityContext;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@PluginInfo(version = 1)
public class OrderService implements com.flexicore.order.interfaces.IOrderService {

    @Inject
    @PluginInfo(version = 1)
    private OrderRepository orderRepository;



    @Override
    public PaginationResponse<Order> getAllOrders(OrderFiltering orderFiltering, SecurityContext securityContext){
        List<Order> list= listAllOrders(orderFiltering, securityContext);
        long count=orderRepository.countAllOrders(orderFiltering,securityContext);
        return new PaginationResponse<>(list,orderFiltering,count);
    }

    @Override
    public List<Order> listAllOrders(OrderFiltering orderFiltering, SecurityContext securityContext) {
        return orderRepository.listAllOrders(orderFiltering,securityContext);
    }

    @Override
    public void validate(OrderFiltering orderFiltering, SecurityContext securityContext){

    }

    @Override
    public void validate(CreateOrder order, SecurityContext securityContext){

    }

    @Override
    public Order createOrder(CreateOrder createOrder, SecurityContext securityContext){
        Order order=createOrderNoMerge(createOrder,securityContext);
        orderRepository.merge(order);
        return order;
    }
    @Override
    public Order updateOrder(UpdateOrder updateOrder, SecurityContext securityContext){
        Order order=updateOrder.getOrder();
        if(updateOrderNoMerge(order,updateOrder)){
            orderRepository.merge(order);
        }
        return order;
    }

    @Override
    public Order createOrderNoMerge(CreateOrder createOrder, SecurityContext securityContext) {
        Order order=Order.s().CreateUnchecked(createOrder.getName(),securityContext);
        order.Init();
        updateOrderNoMerge(order,createOrder);
        return order;
    }

    @Override
    public boolean updateOrderNoMerge(Order order, CreateOrder createOrder) {
        boolean update=false;
        if(createOrder.getName()!=null && !createOrder.getName().equals(order.getName())){
            order.setName(createOrder.getName());
            update=true;
        }
        if(createOrder.getDescription()!=null && !createOrder.getDescription().equals(order.getDescription())){
            order.setDescription(createOrder.getDescription());
            update=true;
        }
        if(createOrder.getExternalId()!=null && !createOrder.getExternalId().equals(order.getExternalId())){
            order.setExternalId(createOrder.getExternalId());
            update=true;
        }
        return update;
    }

    public <T extends Baseclass> List<T> listByIds(Class<T> c, Set<String> ids, SecurityContext securityContext) {
        return orderRepository.listByIds(c, ids, securityContext);
    }

    public <T extends Baseclass> T getByIdOrNull(String id, Class<T> c, List<String> batchString, SecurityContext securityContext) {
        return orderRepository.getByIdOrNull(id, c, batchString, securityContext);
    }
}
