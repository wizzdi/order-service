package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.order.model.SupplyTime;

public class UpdateSupplyTime extends CreateSupplyTime {

	private String id;
	@JsonIgnore
	private SupplyTime supplyTime;

	public String getId() {
		return id;
	}

	public <T extends UpdateSupplyTime> T setId(String id) {
		this.id = id;
		return (T) this;
	}

	@JsonIgnore
	public SupplyTime getSupplyTime() {
		return supplyTime;
	}

	public <T extends UpdateSupplyTime> T setSupplyTime(SupplyTime supplyTime) {
		this.supplyTime = supplyTime;
		return (T) this;
	}
}
