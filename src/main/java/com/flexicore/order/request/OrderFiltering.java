package com.flexicore.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.model.FilteringInformationHolder;
import com.flexicore.organization.model.Organization;
import com.flexicore.organization.model.Supplier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderFiltering extends FilteringInformationHolder {

	private Set<String> externalIds;
	private Set<String> supplierIds = new HashSet<>();
	@JsonIgnore
	private List<Supplier> suppliers;
	private Set<String> consumingOrganizationIds = new HashSet<>();
	@JsonIgnore
	private List<Organization> consumingOrganizations;

	public Set<String> getExternalIds() {
		return externalIds;
	}

	public <T extends OrderFiltering> T setExternalIds(Set<String> externalIds) {
		this.externalIds = externalIds;
		return (T) this;
	}

	public Set<String> getSupplierIds() {
		return supplierIds;
	}

	public <T extends OrderFiltering> T setSupplierIds(Set<String> supplierIds) {
		this.supplierIds = supplierIds;
		return (T) this;
	}

	@JsonIgnore
	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public <T extends OrderFiltering> T setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
		return (T) this;
	}

	public Set<String> getConsumingOrganizationIds() {
		return consumingOrganizationIds;
	}

	public <T extends OrderFiltering> T setConsumingOrganizationIds(
			Set<String> consumingOrganizationIds) {
		this.consumingOrganizationIds = consumingOrganizationIds;
		return (T) this;
	}

	@JsonIgnore
	public List<Organization> getConsumingOrganizations() {
		return consumingOrganizations;
	}

	public <T extends OrderFiltering> T setConsumingOrganizations(
			List<Organization> consumingOrganizations) {
		this.consumingOrganizations = consumingOrganizations;
		return (T) this;
	}
}
