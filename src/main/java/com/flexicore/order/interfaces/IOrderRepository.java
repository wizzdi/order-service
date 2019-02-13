package com.flexicore.order.interfaces;

import com.flexicore.interfaces.PluginRepository;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.Order_;
import com.flexicore.order.request.OrderFiltering;
import com.flexicore.organization.model.Organization;
import com.flexicore.organization.model.Organization_;
import com.flexicore.organization.model.Supplier;
import com.flexicore.organization.model.Supplier_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface IOrderRepository extends PluginRepository {

    static <T extends Order> void addOrderPredicates(List<Predicate> preds, CriteriaBuilder cb, Root<T> r, OrderFiltering orderFiltering) {
        if (orderFiltering.getExternalIds() != null && !orderFiltering.getExternalIds().isEmpty()) {
            preds.add(r.get(Order_.externalId).in(orderFiltering.getExternalIds()));
        }
        if (orderFiltering.getConsumingOrganizations() != null && !orderFiltering.getConsumingOrganizations().isEmpty()) {
            Set<String> ids = orderFiltering.getConsumingOrganizations().parallelStream().map(f -> f.getId()).collect(Collectors.toSet());
            Join<T, Organization> join = r.join(Order_.consumingOrganization);
            preds.add(join.get(Organization_.id).in(ids));
        }
        if (orderFiltering.getSuppliers() != null && !orderFiltering.getSuppliers().isEmpty()) {
            Set<String> ids = orderFiltering.getSuppliers().parallelStream().map(f -> f.getId()).collect(Collectors.toSet());
            Join<T, Supplier> join = r.join(Order_.supplier);
            preds.add(join.get(Supplier_.id).in(ids));
        }
    }
}
