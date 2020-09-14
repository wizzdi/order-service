package com.flexicore.order.interfaces;

import com.flexicore.interfaces.PluginRepository;
import com.flexicore.order.model.SupplyTime;
import com.flexicore.order.request.SupplyTimeFiltering;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface ISupplyTimeRepository extends PluginRepository {

	static <T extends SupplyTime> void addSupplyTimePredicates(
			List<Predicate> preds, CriteriaBuilder cb, Root<T> r,
			SupplyTimeFiltering orderFiltering) {

	}
}
