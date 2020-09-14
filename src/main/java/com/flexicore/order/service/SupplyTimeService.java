package com.flexicore.order.service;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.data.jsoncontainers.PaginationResponse;
import com.flexicore.model.Baseclass;
import com.flexicore.order.data.SupplyTimeRepository;
import com.flexicore.order.interfaces.ISupplyTimeService;
import com.flexicore.order.model.SupplyTime;
import com.flexicore.order.request.CreateSupplyTime;
import com.flexicore.order.request.SupplyTimeFiltering;
import com.flexicore.order.request.UpdateSupplyTime;
import com.flexicore.organization.model.Organization;
import com.flexicore.organization.model.Supplier;
import com.flexicore.security.SecurityContext;

import javax.ws.rs.BadRequestException;
import java.util.*;
import java.util.stream.Collectors;
import org.pf4j.Extension;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@PluginInfo(version = 1)
@Extension
@Component
public class SupplyTimeService implements ISupplyTimeService {

	@PluginInfo(version = 1)
	@Autowired
	private SupplyTimeRepository supplyTimeRepository;

	@Override
	public PaginationResponse<SupplyTime> getAllSupplyTimes(
			SupplyTimeFiltering supplyTimeFiltering,
			SecurityContext securityContext) {
		List<SupplyTime> list = listAllSupplyTimes(supplyTimeFiltering,
				securityContext);
		long count = supplyTimeRepository.countAllSupplyTimes(
				supplyTimeFiltering, securityContext);
		return new PaginationResponse<>(list, supplyTimeFiltering, count);
	}

	@Override
	public List<SupplyTime> listAllSupplyTimes(
			SupplyTimeFiltering supplyTimeFiltering,
			SecurityContext securityContext) {
		return supplyTimeRepository.listAllSupplyTimes(supplyTimeFiltering,
				securityContext);
	}

	@Override
	public void validate(SupplyTimeFiltering supplyTimeFiltering,
			SecurityContext securityContext) {

	}

	@Override
	public void validate(CreateSupplyTime createSupplyTime,
			SecurityContext securityContext) {

	}

	@Override
	public SupplyTime createSupplyTime(CreateSupplyTime createSupplyTime,
			SecurityContext securityContext) {
		SupplyTime supplyTime = createSupplyTimeNoMerge(createSupplyTime,
				securityContext);
		supplyTimeRepository.merge(supplyTime);
		return supplyTime;
	}
	@Override
	public SupplyTime updateSupplyTime(UpdateSupplyTime updateSupplyTime,
			SecurityContext securityContext) {
		SupplyTime supplyTime = updateSupplyTime.getSupplyTime();
		if (updateSupplyTimeNoMerge(supplyTime, updateSupplyTime)) {
			supplyTimeRepository.merge(supplyTime);
		}
		return supplyTime;
	}

	@Override
	public SupplyTime createSupplyTimeNoMerge(
			CreateSupplyTime createSupplyTime, SecurityContext securityContext) {
		SupplyTime supplyTime = new SupplyTime(
				createSupplyTime.getName(), securityContext);
		updateSupplyTimeNoMerge(supplyTime, createSupplyTime);
		return supplyTime;
	}

	@Override
	public boolean updateSupplyTimeNoMerge(SupplyTime supplyTime,
			CreateSupplyTime createSupplyTime) {
		boolean update = false;
		if (createSupplyTime.getName() != null
				&& !createSupplyTime.getName().equals(supplyTime.getName())) {
			supplyTime.setName(createSupplyTime.getName());
			update = true;
		}
		if (createSupplyTime.getDayOfTheWeek() != null
				&& createSupplyTime.getDayOfTheWeek() != supplyTime
						.getDayOfTheWeek()) {
			supplyTime.setDayOfTheWeek(createSupplyTime.getDayOfTheWeek());
			update = true;
		}
		if (createSupplyTime.getHour() != null
				&& createSupplyTime.getHour() != supplyTime.getHour()) {
			supplyTime.setHour(createSupplyTime.getHour());
			update = true;
		}

		if (createSupplyTime.getMinute() != null
				&& createSupplyTime.getMinute() != supplyTime.getMinute()) {
			supplyTime.setMinute(createSupplyTime.getMinute());
			update = true;
		}

		if (createSupplyTime.getSecond() != null
				&& createSupplyTime.getSecond() != supplyTime.getSecond()) {
			supplyTime.setSecond(createSupplyTime.getSecond());
			update = true;
		}

		return update;
	}

	public <T extends Baseclass> List<T> listByIds(Class<T> c, Set<String> ids,
			SecurityContext securityContext) {
		return supplyTimeRepository.listByIds(c, ids, securityContext);
	}

	public <T extends Baseclass> T getByIdOrNull(String id, Class<T> c,
			List<String> batchString, SecurityContext securityContext) {
		return supplyTimeRepository.getByIdOrNull(id, c, batchString,
				securityContext);
	}
}
