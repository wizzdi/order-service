package com.flexicore.order.data;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.interfaces.AbstractRepositoryPlugin;
import com.flexicore.model.QueryInformationHolder;

import com.flexicore.order.interfaces.IOrderItemRepository;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderItem;
import com.flexicore.order.model.OrderItem_;
import com.flexicore.order.model.Order_;
import com.flexicore.order.request.OrderItemFiltering;
import com.flexicore.product.model.Product;
import com.flexicore.product.model.Product_;
import com.flexicore.security.SecurityContext;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.pf4j.Extension;
import org.springframework.stereotype.Component;

@PluginInfo(version = 1)
@Extension
@Component
public class OrderItemRepository extends AbstractRepositoryPlugin implements IOrderItemRepository {

	public List<OrderItem> listAllOrderItems(
			OrderItemFiltering orderItemFiltering,
			SecurityContext securityContext) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderItem> q = cb.createQuery(OrderItem.class);
		Root<OrderItem> r = q.from(OrderItem.class);
		List<Predicate> preds = new ArrayList<>();
		IOrderItemRepository.addOrderItemPredicates(preds, cb, r, orderItemFiltering);
		QueryInformationHolder<OrderItem> queryInformationHolder = new QueryInformationHolder<>(orderItemFiltering, OrderItem.class, securityContext);
		return getAllFiltered(queryInformationHolder, preds, cb, q, r);
	}

	public long countAllOrderItems(OrderItemFiltering orderItemFiltering,
			SecurityContext securityContext) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<OrderItem> r = q.from(OrderItem.class);
		List<Predicate> preds = new ArrayList<>();
		IOrderItemRepository.addOrderItemPredicates(preds, cb, r, orderItemFiltering);
		QueryInformationHolder<OrderItem> queryInformationHolder = new QueryInformationHolder<>(orderItemFiltering, OrderItem.class, securityContext);
		return countAllFiltered(queryInformationHolder, preds, cb, q, r);
	}

}
