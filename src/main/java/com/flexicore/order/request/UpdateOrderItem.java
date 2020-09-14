package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.order.model.OrderItem;

public class UpdateOrderItem extends CreateOrderItem {

	private String id;
	@JsonIgnore
	private OrderItem orderItem;

	public String getId() {
		return id;
	}

	public <T extends UpdateOrderItem> T setId(String id) {
		this.id = id;
		return (T) this;
	}

	@JsonIgnore
	public OrderItem getOrderItem() {
		return orderItem;
	}

	public <T extends UpdateOrderItem> T setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
		return (T) this;
	}
}
