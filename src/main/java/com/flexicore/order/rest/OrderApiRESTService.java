package com.flexicore.order.rest;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.annotations.ProtectedREST;
import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.annotations.ProtectedREST;
import com.flexicore.interfaces.RestServicePlugin;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.CreateOrderApiConfig;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.order.request.SendOrder;
import com.flexicore.order.request.UpdateOrderApiConfig;
import com.flexicore.order.service.OrderApiInvokerService;
import com.flexicore.security.SecurityContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.interceptor.Interceptors;
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
@Path("plugins/OrderApi")
@Tag(name = "OrderApi")
@Extension
@Component
public class OrderApiRESTService implements RestServicePlugin {

	@PluginInfo(version = 1)
	@Autowired
	private OrderApiInvokerService service;

	@POST
	@Produces("application/json")
	@Path("/createOrderApiConfig")
	@Operation(summary = "createOrderApiConfig", description = "Creates configuration instance for API")
	public OrderApiConfig createOrderApiConfig(
			@HeaderParam("authenticationKey") String authenticationKey,
			CreateOrderApiConfig creationContainer,
			@Context SecurityContext securityContext) {
		service.validate(creationContainer, securityContext);
		return service.createOrderApiConfig(creationContainer, securityContext);
	}

	@POST
	@Produces("application/json")
	@Path("/updateOrderApiConfig")
	@Operation(summary = "updateOrderApiConfig", description = "Updates configuration instance for API")
	public OrderApiConfig updateOrderApiConfig(
			@HeaderParam("authenticationKey") String authenticationKey,
			UpdateOrderApiConfig updateContainer,
			@Context SecurityContext securityContext) {
		service.validate(updateContainer, securityContext);
		return service.updateOrderApiConfig(updateContainer, securityContext);
	}

	@POST
	@Produces("application/json")
	@Path("/listAllOrderApiConfigs")
	@Operation(summary = "listAllOrderApiConfigs", description = "Get all configuration instances for API")
	public PaginationResponse<OrderApiConfig> listAllOrderApiConfigs(
			@HeaderParam("authenticationKey") String authenticationKey,
			OrderApiConfigFiltering filtering,
			@Context SecurityContext securityContext) {
		service.validate(filtering, securityContext);
		return service.getOrderApiConfigs(filtering, securityContext);
	}

	@POST
	@Produces("application/json")
	@Path("/sendOrder")
	@Operation(summary = "sendOrder", description = "Get all configuration instances for API")
	public Order sendOrder(
			@HeaderParam("authenticationKey") String authenticationKey,
			SendOrder sendOrder, @Context SecurityContext securityContext) {
		service.validate(sendOrder, securityContext);
		return service.sendOrder(sendOrder, securityContext);
	}

}
