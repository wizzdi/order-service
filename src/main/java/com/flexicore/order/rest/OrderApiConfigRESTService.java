package com.flexicore.order.rest;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.interceptors.DynamicResourceInjector;
import com.flexicore.interceptors.SecurityImposer;
import com.flexicore.interfaces.RestServicePlugin;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.CreateOrderApiConfig;
import com.flexicore.order.request.OrderApiConfigFiltering;
import com.flexicore.order.request.UpdateOrderApiConfig;
import com.flexicore.order.service.OrderApiConfigService;
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
@Path("plugins/OrderApiConfig")
@Tag(name = "OrderApiConfig")
public class OrderApiConfigRESTService implements RestServicePlugin {

    @Inject
    @PluginInfo(version = 1)
    private OrderApiConfigService service;

    @POST
    @Produces("application/json")
    @Path("/createOrderApiConfig")
    @Operation(summary = "createOrderApiConfig", description = "Creates Configuration instance for API")
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
    @Operation(summary = "updateOrderApiConfig", description = "Updates Configuration instance for API")
    public OrderApiConfig updateOrderApiConfig(
            @HeaderParam("authenticationKey") String authenticationKey,
            UpdateOrderApiConfig updateContainer,
            @Context SecurityContext securityContext) {
        service.validate(updateContainer, securityContext);
        return service.createOrderApiConfig(updateContainer, securityContext);
    }

    @POST
    @Produces("application/json")
    @Path("/listOrderApiConfigs")
    @Operation(summary = "listOrderApiConfigs", description = "Get Configuration instances for API")
    public List<OrderApiConfig> listOrderApiConfigs(
            @HeaderParam("authenticationKey") String authenticationKey,
            OrderApiConfigFiltering filtering,
            @Context SecurityContext securityContext) {
        return service.getOrderApiConfigs(filtering, securityContext);
    }

}
