package com.flexicore.order.data;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.interfaces.AbstractRepositoryPlugin;
import com.flexicore.model.QueryInformationHolder;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.model.OrderApiConfig_;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.organization.model.Supplier;
import com.flexicore.organization.model.Supplier_;
import com.flexicore.security.SecurityContext;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PluginInfo(version = 1)
public class OrderApiRepository extends AbstractRepositoryPlugin {
    public List<OrderApiConfig> listAllOrderApiConfig(SecurityContext securityContext, OrderApiConfigFiltering filtering) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderApiConfig> q = cb.createQuery(OrderApiConfig.class);
        Root<OrderApiConfig> r = q.from(OrderApiConfig.class);
        List<Predicate> preds = new ArrayList<>();
        addPredicate(filtering, cb, r, preds);
        QueryInformationHolder<OrderApiConfig> queryInformationHolder = new QueryInformationHolder<>(filtering, OrderApiConfig.class, securityContext);
        return getAllFiltered(queryInformationHolder, preds, cb, q, r);
    }

    public Long countAllOrderApiConfig(SecurityContext securityContext, OrderApiConfigFiltering filtering) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<OrderApiConfig> r = q.from(OrderApiConfig.class);
        List<Predicate> preds = new ArrayList<>();
        addPredicate(filtering, cb, r, preds);
        QueryInformationHolder<OrderApiConfig> queryInformationHolder = new QueryInformationHolder<>(filtering, OrderApiConfig.class, securityContext);
        return countAllFiltered(queryInformationHolder, preds, cb, q, r);
    }

    private void addPredicate(OrderApiConfigFiltering filtering, CriteriaBuilder cb, Root<OrderApiConfig> r, List<Predicate> preds) {
        if (filtering.getSuppliers() != null && !filtering.getSuppliers().isEmpty()) {
            Set<String> ids = filtering.getSuppliers().parallelStream().map(f -> f.getId()).collect(Collectors.toSet());
            Join<OrderApiConfig, Supplier> join = r.join(OrderApiConfig_.supplier);
            preds.add(join.get(Supplier_.id).in(ids));
        }
    }
}
