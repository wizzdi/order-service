package com.flexicore.order.interfaces;

import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.interfaces.ServicePlugin;
import com.flexicore.order.model.SupplyTime;
import com.flexicore.order.request.CreateSupplyTime;
import com.flexicore.order.request.SupplyTimeFiltering;
import com.flexicore.order.request.UpdateSupplyTime;
import com.flexicore.security.SecurityContext;

import java.util.List;

public interface ISupplyTimeService extends ServicePlugin {


    PaginationResponse<SupplyTime> getAllSupplyTimes(SupplyTimeFiltering supplyTimeFiltering, SecurityContext securityContext);

    List<SupplyTime> listAllSupplyTimes(SupplyTimeFiltering supplyTimeFiltering, SecurityContext securityContext);

    void validate(SupplyTimeFiltering supplyTimeFiltering, SecurityContext securityContext);

    void validate(CreateSupplyTime createSupplyTime, SecurityContext securityContext);

    SupplyTime createSupplyTime(CreateSupplyTime createSupplyTime, SecurityContext securityContext);

    SupplyTime updateSupplyTime(UpdateSupplyTime updateSupplyTime, SecurityContext securityContext);

    SupplyTime createSupplyTimeNoMerge(CreateSupplyTime createSupplyTime, SecurityContext securityContext);

    boolean updateSupplyTimeNoMerge(SupplyTime supplyTime, CreateSupplyTime createSupplyTime);
}
