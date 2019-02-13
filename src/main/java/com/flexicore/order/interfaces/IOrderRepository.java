package com.flexicore.order.interfaces;

import com.flexicore.interfaces.PluginRepository;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.Order_;
import com.flexicore.order.request.OrderFiltering;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface IOrderRepository extends PluginRepository {

    static void addOrderPredicates(List<Predicate> preds, CriteriaBuilder cb, Root<Order> r, OrderFiltering orderFiltering) {
        if(orderFiltering.getExternalIds()!=null && !orderFiltering.getExternalIds().isEmpty()){
            preds.add(r.get(Order_.externalId).in(orderFiltering.getExternalIds()));
        }
    }
}
