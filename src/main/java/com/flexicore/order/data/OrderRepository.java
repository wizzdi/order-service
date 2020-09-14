package com.flexicore.order.data;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.interfaces.AbstractRepositoryPlugin;
import com.flexicore.model.QueryInformationHolder;
import com.flexicore.order.interfaces.IOrderRepository;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.Order_;
import com.flexicore.order.request.OrderFiltering;
import com.flexicore.security.SecurityContext;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.pf4j.Extension;
import org.springframework.stereotype.Component;

@PluginInfo(version = 1)
@Extension
@Component
public class OrderRepository extends AbstractRepositoryPlugin
		implements
			IOrderRepository {

	public List<Order> listAllOrders(OrderFiltering orderFiltering,
			SecurityContext securityContext) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> q = cb.createQuery(Order.class);
		Root<Order> r = q.from(Order.class);
		List<Predicate> preds = new ArrayList<>();
		IOrderRepository.addOrderPredicates(preds, cb, r, orderFiltering);
		QueryInformationHolder<Order> queryInformationHolder = new QueryInformationHolder<>(
				orderFiltering, Order.class, securityContext);
		return getAllFiltered(queryInformationHolder, preds, cb, q, r);
	}

	public long countAllOrders(OrderFiltering orderFiltering,
			SecurityContext securityContext) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<Order> r = q.from(Order.class);
		List<Predicate> preds = new ArrayList<>();
		IOrderRepository.addOrderPredicates(preds, cb, r, orderFiltering);
		QueryInformationHolder<Order> queryInformationHolder = new QueryInformationHolder<>(
				orderFiltering, Order.class, securityContext);
		return countAllFiltered(queryInformationHolder, preds, cb, q, r);
	}

	public int getCurrentOrdinal(SecurityContext securityContext) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> q = cb.createQuery(Order.class);
		Root<Order> r = q.from(Order.class);
		q.select(r)
				.where(cb.equal(r.get(Order_.tenant),
						securityContext.getTenantToCreateIn()))
				.orderBy(cb.desc(r.get(Order_.ordinal)));
		TypedQuery<Order> query = em.createQuery(q);
		query.setFirstResult(0).setMaxResults(1);
		List<Order> resultList = query.getResultList();
		return resultList.isEmpty() ? 0 : resultList.get(0).getOrdinal();
	}
}
