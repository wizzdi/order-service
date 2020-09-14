package com.flexicore.order.rest;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.annotations.ProtectedREST;
import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.interfaces.RestServicePlugin;
import com.flexicore.order.model.Order;
import com.flexicore.order.request.CreateOrder;
import com.flexicore.order.request.OrderFiltering;
import com.flexicore.order.request.UpdateOrder;
import com.flexicore.order.service.OrderService;
import com.flexicore.security.SecurityContext;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.pf4j.Extension;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@PluginInfo(version = 1)
@OperationsInside
@ProtectedREST
@Path("plugins/Order")
@OpenAPIDefinition(tags = {@Tag(name = "Order", description = "Order Api"),
		@Tag(name = "OrderItem", description = "OrderItem Api")

})
@Tag(name = "Order")
@Extension
@Component
public class OrderRESTService implements RestServicePlugin {

	@PluginInfo(version = 1)
	@Autowired
	private OrderService service;

	@POST
	@Produces("application/json")
	@Path("/createOrder")
	@Operation(summary = "createOrder", description = "Creates Order")
	public Order createOrder(
			@HeaderParam("authenticationKey") String authenticationKey,
			CreateOrder creationContainer,
			@Context SecurityContext securityContext) {
		service.validate(creationContainer, securityContext);
		return service.createOrder(creationContainer, securityContext);
	}

	@POST
	@Produces("application/json")
	@Operation(summary = "getAllOrders", description = "Lists all Orders Filtered")
	@Path("getAllOrders")
	public PaginationResponse<Order> getAllOrders(
			@HeaderParam("authenticationKey") String authenticationKey,
			OrderFiltering filtering, @Context SecurityContext securityContext) {
		service.validate(filtering, securityContext);
		return service.getAllOrders(filtering, securityContext);
	}

	@POST
	@Produces("application/json")
	@Path("/updateOrder")
	@Operation(summary = "updateOrder", description = "Updates Order")
	public Order updateOrder(
			@HeaderParam("authenticationKey") String authenticationKey,
			UpdateOrder updateContainer,
			@Context SecurityContext securityContext) {
		service.validate(updateContainer, securityContext);
		return service.updateOrder(updateContainer, securityContext);
	}

	// @POST
	// @Produces("application/json")
	// @Path("/sendOrder")
	// @Operation(summary = "sendOrder", description =
	// "Send Order to provider using API")
	// public Order sendOrder(
	// @HeaderParam("authenticationKey") String authenticationKey,
	// SendOrder sendOrder,
	// @Context SecurityContext securityContext) {
	// service.validate(sendOrder, securityContext);
	// return service.sendOrder(sendOrder, securityContext);
	// }

}