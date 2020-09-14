package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.model.FilteringInformationHolder;
import com.flexicore.order.model.Order;
import com.flexicore.product.model.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderItemFiltering extends FilteringInformationHolder {

	private Set<String> orderIds = new HashSet<>();
	@JsonIgnore
	private List<Order> orders = new ArrayList<>();
	private Set<String> productIds = new HashSet<>();
	@JsonIgnore
	private List<Product> products = new ArrayList<>();

	public Set<String> getOrderIds() {
		return orderIds;
	}

	public <T extends OrderItemFiltering> T setOrderIds(Set<String> orderIds) {
		this.orderIds = orderIds;
		return (T) this;
	}

	@JsonIgnore
	public List<Order> getOrders() {
		return orders;
	}

	public <T extends OrderItemFiltering> T setOrders(List<Order> orders) {
		this.orders = orders;
		return (T) this;
	}

	public Set<String> getProductIds() {
		return productIds;
	}

	public <T extends OrderItemFiltering> T setProductIds(Set<String> productIds) {
		this.productIds = productIds;
		return (T) this;
	}

	@JsonIgnore
	public List<Product> getProducts() {
		return products;
	}

	public <T extends OrderItemFiltering> T setProducts(List<Product> products) {
		this.products = products;
		return (T) this;
	}
}
