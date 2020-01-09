package com.flexicore.order.rest;

import com.flexicore.annotations.OperationsInside;
import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.data.jsoncontainers.PaginationResponse;

import com.flexicore.annotations.ProtectedREST;
import com.flexicore.interfaces.RestServicePlugin;
import com.flexicore.order.model.SupplyTime;
import com.flexicore.order.request.CreateSupplyTime;
import com.flexicore.order.request.SupplyTimeFiltering;
import com.flexicore.order.request.UpdateSupplyTime;
import com.flexicore.order.service.SupplyTimeService;
import com.flexicore.security.SecurityContext;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

@PluginInfo(version = 1)
@OperationsInside
@ProtectedREST
@Path("plugins/SupplyTime")
@OpenAPIDefinition(tags = {
		@Tag(name = "SupplyTime",description = "SupplyTime Api"),
		@Tag(name = "SupplyTimeItem",description = "SupplyTimeItem Api")

})
@Tag(name="SupplyTime")
public class SupplyTimeRESTService implements RestServicePlugin {

	@Inject
	@PluginInfo(version = 1)
	private SupplyTimeService service;



	@POST
	@Produces("application/json")
	@Path("/createSupplyTime")
	@Operation(summary = "createSupplyTime", description = "Creates SupplyTime")
	public SupplyTime createSupplyTime(
			@HeaderParam("authenticationKey") String authenticationKey,
			CreateSupplyTime creationContainer,
			@Context SecurityContext securityContext) {
		service.validate(creationContainer, securityContext);
		return service.createSupplyTime(creationContainer, securityContext);
	}



	@POST
	@Produces("application/json")
	@Operation(summary = "getAllSupplyTimes", description = "Lists all SupplyTimes Filtered")
	@Path("getAllSupplyTimes")
	public PaginationResponse<SupplyTime> getAllSupplyTimes(
			@HeaderParam("authenticationKey") String authenticationKey,
			SupplyTimeFiltering filtering,
			@Context SecurityContext securityContext) {
		service.validate(filtering,securityContext);
		return service.getAllSupplyTimes(filtering, securityContext);
	}

	@POST
	@Produces("application/json")
	@Path("/updateSupplyTime")
	@Operation(summary = "updateSupplyTime", description = "Updates SupplyTime")
	public SupplyTime updateSupplyTime(
			@HeaderParam("authenticationKey") String authenticationKey,
			UpdateSupplyTime updateContainer,
			@Context SecurityContext securityContext) {
		SupplyTime SupplyTime = service.getByIdOrNull(updateContainer.getId(), SupplyTime.class, null, securityContext);
		if (SupplyTime == null) {
			throw new BadRequestException("no SupplyTime with id " + updateContainer.getId());
		}
		updateContainer.setSupplyTime(SupplyTime);
		service.validate(updateContainer,securityContext);
		return service.updateSupplyTime(updateContainer, securityContext);
	}



}