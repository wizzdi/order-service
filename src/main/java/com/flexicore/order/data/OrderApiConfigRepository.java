package com.flexicore.order.data;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.interfaces.AbstractRepositoryPlugin;
import com.flexicore.model.QueryInformationHolder;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.security.SecurityContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@PluginInfo(version = 1)
public class OrderApiConfigRepository extends AbstractRepositoryPlugin {
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
//        Join<OrderApiConfig, OrderApiConfigToSupplier> linkJoin = null;
//        if(filtering.getHasActiveLinks() && filtering.getOrderApiConfigs() != null && !filtering.getSuppliers().isEmpty()){
//            linkJoin = linkJoin == null ? r.join(OrderApiConfig_.suppliers) : linkJoin;
//            preds.add(cb.equal(linkJoin.get(OrderApiConfigToSupplier_.enabled),filtering.getHasActiveLinks()));
//        }
//        if (filtering.getSuppliers() != null && !filtering.getSuppliers().isEmpty()) {
//            Set<String> ids = filtering.getSuppliers().parallelStream().map(f -> f.getId()).collect(Collectors.toSet());
//            linkJoin = linkJoin == null ? r.join(OrderApiConfig_.suppliers) : linkJoin;
//            Join<OrderApiConfigToSupplier, Supplier> join = cb.treat(linkJoin.join(Baselink_.rightside), Supplier.class);
//            preds.add(join.get(Supplier_.id).in(ids));
//        }
//
//        if (filtering.getOrderApiConfigs() != null && !filtering.getOrderApiConfigs().isEmpty()) {
//            Set<String> ids = filtering.getOrderApiConfigs().parallelStream().map(f -> f.getId()).collect(Collectors.toSet());
//            preds.add(r.get(OrderApiConfig_.id).in(ids));
//        }
//    }
    }
}
