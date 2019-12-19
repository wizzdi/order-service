package com.flexicore.order.data;

import com.flexicore.interfaces.AbstractRepositoryPlugin;
import com.flexicore.model.Baselink_;
import com.flexicore.model.FilteringInformationHolder;
import com.flexicore.model.QueryInformationHolder;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.model.OrderApiConfigToSupplier;
import com.flexicore.order.model.OrderApiConfigToSupplier_;
import com.flexicore.order.model.OrderApiConfig_;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.order.request.UnlinkOrderApiConfigToSupplierRequest;
import com.flexicore.organization.model.Supplier;
import com.flexicore.organization.model.Supplier_;
import com.flexicore.security.SecurityContext;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderApiConfigRepository extends AbstractRepositoryPlugin {
    public List<OrderApiConfig> listAll(SecurityContext securityContext, OrderApiConfigFiltering filtering) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderApiConfig> q = cb.createQuery(OrderApiConfig.class);
        Root<OrderApiConfig> r = q.from(OrderApiConfig.class);
        List<Predicate> preds = new ArrayList<>();
        addPredicate(filtering, cb, r, preds);
        QueryInformationHolder<OrderApiConfig> queryInformationHolder = new QueryInformationHolder<>(filtering, OrderApiConfig.class, securityContext);
        return getAllFiltered(queryInformationHolder, preds, cb, q, r);
    }

    public Long countAll(SecurityContext securityContext, OrderApiConfigFiltering filtering) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<OrderApiConfig> r = q.from(OrderApiConfig.class);
        List<Predicate> preds = new ArrayList<>();
        addPredicate(filtering, cb, r, preds);
        QueryInformationHolder<OrderApiConfig> queryInformationHolder = new QueryInformationHolder<>(filtering, OrderApiConfig.class, securityContext);
        return countAllFiltered(queryInformationHolder, preds, cb, q, r);
    }

    private void addPredicate(OrderApiConfigFiltering filtering, CriteriaBuilder cb, Root<OrderApiConfig> r, List<Predicate> preds) {
        Join<OrderApiConfig, OrderApiConfigToSupplier> linkJoin = null;
        if(filtering.getHasActiveLinks() && filtering.getOrderApiConfigs() != null && !filtering.getSuppliers().isEmpty()){
            linkJoin = linkJoin == null ? r.join(OrderApiConfig_.suppliers) : linkJoin;
            preds.add(cb.equal(linkJoin.get(OrderApiConfigToSupplier_.enabled),filtering.getHasActiveLinks()));
        }
        if (filtering.getSuppliers() != null && !filtering.getSuppliers().isEmpty()) {
            Set<String> ids = filtering.getSuppliers().parallelStream().map(f -> f.getId()).collect(Collectors.toSet());
            linkJoin = linkJoin == null ? r.join(OrderApiConfig_.suppliers) : linkJoin;
            Join<OrderApiConfigToSupplier, Supplier> join = cb.treat(linkJoin.join(Baselink_.rightside), Supplier.class);
            preds.add(join.get(Supplier_.id).in(ids));
        }

        if (filtering.getOrderApiConfigs() != null && !filtering.getOrderApiConfigs().isEmpty()) {
            Set<String> ids = filtering.getOrderApiConfigs().parallelStream().map(f -> f.getId()).collect(Collectors.toSet());
            preds.add(r.get(OrderApiConfig_.id).in(ids));
        }
    }


    public List<OrderApiConfigToSupplier> getAllTransactionToEndpointLinks(UnlinkOrderApiConfigToSupplierRequest unlinkContainer, SecurityContext securityContext) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderApiConfigToSupplier> q = cb.createQuery(OrderApiConfigToSupplier.class);
        Root<OrderApiConfigToSupplier> r = q.from(OrderApiConfigToSupplier.class);
        List<Predicate> preds = new ArrayList<>();
        if(unlinkContainer.getOrderApiConfig()!=null){
            preds.add(cb.equal(r.get(Baselink_.leftside), unlinkContainer.getOrderApiConfig()));

        }
        if(unlinkContainer.getSupplier()!=null){
            preds.add(cb.equal(r.get(Baselink_.rightside), unlinkContainer.getSupplier()));
        }

        QueryInformationHolder<OrderApiConfigToSupplier> queryInformationHolder = new QueryInformationHolder<>(new FilteringInformationHolder(), OrderApiConfigToSupplier.class, securityContext);
        return getAllFiltered(queryInformationHolder, preds, cb, q, r);
    }
}
