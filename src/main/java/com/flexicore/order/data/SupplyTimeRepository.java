package com.flexicore.order.data;

import com.flexicore.annotations.plugins.PluginInfo;
import com.flexicore.interfaces.AbstractRepositoryPlugin;
import com.flexicore.model.QueryInformationHolder;
import com.flexicore.order.interfaces.ISupplyTimeRepository;
import com.flexicore.order.model.SupplyTime;
import com.flexicore.order.request.SupplyTimeFiltering;
import com.flexicore.security.SecurityContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.pf4j.Extension;
import org.springframework.stereotype.Component;

@PluginInfo(version = 1)
@Extension
@Component
public class SupplyTimeRepository extends AbstractRepositoryPlugin implements ISupplyTimeRepository {

	public List<SupplyTime> listAllSupplyTimes(
			SupplyTimeFiltering supplyTimeFiltering,
			SecurityContext securityContext) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SupplyTime> q = cb.createQuery(SupplyTime.class);
		Root<SupplyTime> r = q.from(SupplyTime.class);
		List<Predicate> preds = new ArrayList<>();
		ISupplyTimeRepository.addSupplyTimePredicates(preds, cb, r, supplyTimeFiltering);
		QueryInformationHolder<SupplyTime> queryInformationHolder = new QueryInformationHolder<>(supplyTimeFiltering, SupplyTime.class, securityContext);
		return getAllFiltered(queryInformationHolder, preds, cb, q, r);
	}

	public long countAllSupplyTimes(SupplyTimeFiltering supplyTimeFiltering,
			SecurityContext securityContext) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<SupplyTime> r = q.from(SupplyTime.class);
		List<Predicate> preds = new ArrayList<>();
		ISupplyTimeRepository.addSupplyTimePredicates(preds, cb, r, supplyTimeFiltering);
		QueryInformationHolder<SupplyTime> queryInformationHolder = new QueryInformationHolder<>(supplyTimeFiltering, SupplyTime.class, securityContext);
		return countAllFiltered(queryInformationHolder, preds, cb, q, r);
	}
}
