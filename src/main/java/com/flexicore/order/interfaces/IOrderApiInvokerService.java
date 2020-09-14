package com.flexicore.order.interfaces;

import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.Order;
import com.flexicore.order.model.OrderApiConfig;
import com.flexicore.order.request.*;
import com.flexicore.security.SecurityContext;

import java.util.List;

public interface IOrderApiInvokerService extends ServicePlugin {
	void validate(CreateOrderApiConfig createOrderApiConfig,
			SecurityContext securityContext);
	OrderApiConfig createOrderApiConfig(
			CreateOrderApiConfig createOrderApiConfig,
			SecurityContext securityContext);
	void validate(UpdateOrderApiConfig updateOrderApiConfig,
			SecurityContext securityContext);
	OrderApiConfig updateOrderApiConfig(
			UpdateOrderApiConfig updateOrderApiConfig,
			SecurityContext securityContext);
	void validate(OrderApiConfigFiltering filtering,
			SecurityContext securityContext);
	PaginationResponse<OrderApiConfig> getOrderApiConfigs(
			OrderApiConfigFiltering filtering, SecurityContext securityContext);
	void validate(SendOrder sendOrder, SecurityContext securityContext);
	Order sendOrder(SendOrder SendOrder, SecurityContext securityContext);
	IOrderApiService getOrderApiConfigImplementor(OrderApiConfig orderApiConfig);

}
