package com.flexicore.order.interfaces;

import com.flexicore.interfaces.PluginRepository;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderItem;
import com.flexicore.order.model.OrderItem_;
import com.flexicore.order.model.Order_;
import com.flexicore.order.request.OrderItemFiltering;
import com.flexicore.product.model.Product;
import com.flexicore.product.model.Product_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface IOrderItemRepository extends PluginRepository {

	static <T extends OrderItem> void addOrderItemPredicates(
			List<Predicate> preds, CriteriaBuilder cb, Root<T> r,
			OrderItemFiltering orderItemFiltering) {
		if (orderItemFiltering.getOrders() != null
				&& !orderItemFiltering.getOrders().isEmpty()) {
			Set<String> ids = orderItemFiltering.getOrders().parallelStream()
					.map(f -> f.getId()).collect(Collectors.toSet());
			Join<T, Order> join = r.join(OrderItem_.order);
			preds.add(join.get(Order_.id).in(ids));
		}
		if (orderItemFiltering.getProducts() != null
				&& !orderItemFiltering.getProducts().isEmpty()) {
			Set<String> ids = orderItemFiltering.getProducts().parallelStream()
					.map(f -> f.getId()).collect(Collectors.toSet());
			Join<T, Product> join = r.join(OrderItem_.product);
			preds.add(join.get(Product_.id).in(ids));
		}
	}
}
