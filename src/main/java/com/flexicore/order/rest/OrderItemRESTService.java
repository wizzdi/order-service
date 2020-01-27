package com.flexicore.order.rest;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.data.jsoncontainers.PaginationResponse;

import com.flexicore.annotations.ProtectedREST;
import com.flexicore.interfaces.RestServicePlugin;
import com.flexicore.order.model.OrderItem;
import com.flexicore.order.request.CreateOrderItem;
import com.flexicore.order.request.OrderItemFiltering;
import com.flexicore.order.request.UpdateOrderItem;
import com.flexicore.order.service.OrderItemService;
import com.flexicore.security.SecurityContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

@PluginInfo(version = 1)
@OperationsInside
@ProtectedREST
@Path("plugins/OrderItem")
@Tag(name="OrderItem")
public class OrderItemRESTService implements RestServicePlugin {

	@Inject
	@PluginInfo(version = 1)
	private OrderItemService service;



	@POST
	@Produces("application/json")
	@Path("/createOrderItem")
	@Operation(summary = "createOrderItem", description = "Creates OrderItem")
	public OrderItem createOrderItem(
			@HeaderParam("authenticationKey") String authenticationKey,
			CreateOrderItem creationContainer,
			@Context SecurityContext securityContext) {
		service.validate(creationContainer, securityContext);
		return service.createOrderItem(creationContainer, securityContext);
	}



	@POST
	@Produces("application/json")
	@Operation(summary = "getAllOrderItems", description = "Lists all OrderItems Filtered")
	@Path("getAllOrderItems")
	public PaginationResponse<OrderItem> getAllOrderItems(
			@HeaderParam("authenticationKey") String authenticationKey,
			OrderItemFiltering filtering,
			@Context SecurityContext securityContext) {
		service.validate(filtering,securityContext);
		return service.getAllOrderItems(filtering, securityContext);
	}

	@POST
	@Produces("application/json")
	@Path("/updateOrderItem")
	@Operation(summary = "updateOrderItem", description = "Updates OrderItem")
	public OrderItem updateOrderItem(
			@HeaderParam("authenticationKey") String authenticationKey,
			UpdateOrderItem updateContainer,
			@Context SecurityContext securityContext) {
		OrderItem OrderItem = service.getByIdOrNull(updateContainer.getId(), OrderItem.class, null, securityContext);
		if (OrderItem == null) {
			throw new BadRequestException("no OrderItem with id " + updateContainer.getId());
		}
		updateContainer.setOrderItem(OrderItem);
		service.validate(updateContainer,securityContext);
		return service.updateOrderItem(updateContainer, securityContext);
	}



}