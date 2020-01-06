package com.flexicore.order.rest;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.interceptors.DynamicResourceInjector;
import com.flexicore.interceptors.SecurityImposer;
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

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.List;

@PluginInfo(version = 1)
@OperationsInside
@Interceptors({SecurityImposer.class, DynamicResourceInjector.class})
@Path("plugins/OrderApi")
@Tag(name = "OrderApi")
public class OrderApiRESTService implements RestServicePlugin {

    @Inject
    @PluginInfo(version = 1)
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
        return service.getOrderApiConfigs(filtering, securityContext);
    }

    @POST
    @Produces("application/json")
    @Path("/SendOrder")
    @Operation(summary = "SendOrder", description = "Get all configuration instances for API")
    public Order SendOrder(
            @HeaderParam("authenticationKey") String authenticationKey,
            SendOrder sendOrder,
            @Context SecurityContext securityContext) {
        service.validate(sendOrder, securityContext);
        return service.sendOrder(sendOrder, securityContext);
    }

}
