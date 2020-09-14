package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.order.interfaces.IOrderApiService;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.model.OrderItem;

import java.util.List;

public class SendOrder {

	private String orderId;
	private String orderApiConfigId;
	@JsonIgnore
	private Order order;
	@JsonIgnore
	private OrderApiConfig orderApiConfig;

	public String getOrderId() {
		return orderId;
	}

	public <T extends SendOrder> T setOrderId(String orderId) {
		this.orderId = orderId;
		return (T) this;
	}

	public String getOrderApiConfigId() {
		return orderApiConfigId;
	}

	public <T extends SendOrder> T setOrderApiConfigId(String orderApiConfigId) {
		this.orderApiConfigId = orderApiConfigId;
		return (T) this;
	}

	@JsonIgnore
	public Order getOrder() {
		return order;
	}

	public <T extends SendOrder> T setOrder(Order order) {
		this.order = order;
		return (T) this;
	}

	@JsonIgnore
	public OrderApiConfig getOrderApiConfig() {
		return orderApiConfig;
	}

	public <T extends SendOrder> T setOrderApiConfig(
			OrderApiConfig orderApiConfig) {
		this.orderApiConfig = orderApiConfig;
		return (T) this;
	}
}
