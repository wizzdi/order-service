package com.flexicore.order.service;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.model.Baseclass;
import com.flexicore.order.data.OrderItemRepository;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderItem;
import com.flexicore.order.request.CreateOrderItem;
import com.flexicore.order.request.OrderItemFiltering;
import com.flexicore.order.request.UpdateOrderItem;
import com.flexicore.product.model.Product;
import com.flexicore.security.SecurityContext;

import javax.ws.rs.BadRequestException;
import java.util.*;
import java.util.stream.Collectors;
import org.pf4j.Extension;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@PluginInfo(version = 1)
@Extension
@Component
public class OrderItemService implements com.flexicore.order.interfaces.IOrderItemService {

	@PluginInfo(version = 1)
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public PaginationResponse<OrderItem> getAllOrderItems(
			OrderItemFiltering orderItemFiltering,
			SecurityContext securityContext) {
		List<OrderItem> list = listAllOrderItems(orderItemFiltering,
				securityContext);
		long count = orderItemRepository.countAllOrderItems(orderItemFiltering,
				securityContext);
		return new PaginationResponse<>(list, orderItemFiltering, count);
	}

	@Override
	public List<OrderItem> listAllOrderItems(
			OrderItemFiltering orderItemFiltering,
			SecurityContext securityContext) {
		return orderItemRepository.listAllOrderItems(orderItemFiltering,
				securityContext);
	}

	@Override
	public void validate(OrderItemFiltering orderItemFiltering,
			SecurityContext securityContext) {
		Set<String> orderIds = orderItemFiltering.getOrderIds();
		Map<String, Order> orderMap = !orderIds.isEmpty()
				? listByIds(Order.class, orderIds, securityContext)
						.parallelStream().collect(
								Collectors.toMap(f -> f.getId(), f -> f))
				: new HashMap<>();
		orderIds.removeAll(orderMap.keySet());
		if (!orderIds.isEmpty()) {
			throw new BadRequestException("No Orders with ids " + orderIds);
		}
		orderItemFiltering.setOrders(new ArrayList<>(orderMap.values()));

		Set<String> productIds = orderItemFiltering.getProductIds();
		Map<String, Product> productMap = !productIds.isEmpty()
				? listByIds(Product.class, productIds, securityContext)
						.parallelStream().collect(
								Collectors.toMap(f -> f.getId(), f -> f))
				: new HashMap<>();
		productIds.removeAll(productMap.keySet());
		if (!productIds.isEmpty()) {
			throw new BadRequestException("No Products with ids " + productIds);
		}
		orderItemFiltering.setProducts(new ArrayList<>(productMap.values()));
	}

	@Override
	public void validate(CreateOrderItem createOrderItem,
			SecurityContext securityContext) {
		String orderId = createOrderItem.getOrderId();
		Order order = orderId != null ? getByIdOrNull(orderId, Order.class,
				null, securityContext) : null;
		if (order == null) {
			throw new BadRequestException("No order with id " + orderId);
		}
		createOrderItem.setOrder(order);

		String productId = createOrderItem.getProductId();
		Product product = productId != null ? getByIdOrNull(productId,
				Product.class, null, securityContext) : null;
		if (product == null) {
			throw new BadRequestException("No Product with id " + productId);
		}
		createOrderItem.setProduct(product);

	}

	@Override
	public OrderItem createOrderItem(CreateOrderItem createOrderItem,
			SecurityContext securityContext) {
		OrderItem orderItem = createOrderItemNoMerge(createOrderItem,
				securityContext);
		orderItemRepository.merge(orderItem);
		return orderItem;
	}
	@Override
	public OrderItem updateOrderItem(UpdateOrderItem updateOrderItem,
			SecurityContext securityContext) {
		OrderItem orderItem = updateOrderItem.getOrderItem();
		if (updateOrderItemNoMerge(orderItem, updateOrderItem)) {
			orderItemRepository.merge(orderItem);
		}
		return orderItem;
	}

	@Override
	public OrderItem createOrderItemNoMerge(CreateOrderItem createOrderItem,
			SecurityContext securityContext) {
		OrderItem orderItem = new OrderItem(
				createOrderItem.getName(), securityContext);
		updateOrderItemNoMerge(orderItem, createOrderItem);
		return orderItem;
	}

	@Override
	public boolean updateOrderItemNoMerge(OrderItem orderItem,
			CreateOrderItem createOrderItem) {
		boolean update = false;
		if (createOrderItem.getName() != null
				&& !createOrderItem.getName().equals(orderItem.getName())) {
			orderItem.setName(createOrderItem.getName());
			update = true;
		}
		if (createOrderItem.getDescription() != null
				&& !createOrderItem.getDescription().equals(
						orderItem.getDescription())) {
			orderItem.setDescription(createOrderItem.getDescription());
			update = true;
		}
		if (createOrderItem.getQuantity() != null
				&& createOrderItem.getQuantity() != orderItem.getQuantity()) {
			orderItem.setQuantity(createOrderItem.getQuantity());
			update = true;
		}

		if (createOrderItem.getOrder() != null
				&& (orderItem.getOrder() == null || !createOrderItem.getOrder()
						.getId().equals(orderItem.getOrder().getId()))) {
			orderItem.setOrder(createOrderItem.getOrder());
			update = true;
		}

		if (createOrderItem.getProduct() != null
				&& (orderItem.getProduct() == null || !createOrderItem
						.getProduct().getId()
						.equals(orderItem.getProduct().getId()))) {
			orderItem.setProduct(createOrderItem.getProduct());
			update = true;
		}

		return update;
	}

	public <T extends Baseclass> List<T> listByIds(Class<T> c, Set<String> ids,
			SecurityContext securityContext) {
		return orderItemRepository.listByIds(c, ids, securityContext);
	}

	public <T extends Baseclass> T getByIdOrNull(String id, Class<T> c,
			List<String> batchString, SecurityContext securityContext) {
		return orderItemRepository.getByIdOrNull(id, c, batchString,
				securityContext);
	}
}
