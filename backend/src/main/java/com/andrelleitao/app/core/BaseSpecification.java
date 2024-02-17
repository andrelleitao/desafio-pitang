package com.andrelleitao.app.core;

import org.springframework.data.jpa.domain.Specification;

import com.andrelleitao.app.core.search.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface BaseSpecification<PK, T extends BaseEntity<PK>> extends Specification<T> {
	
	Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder);
	void add(SearchCriteria criteria);
	void clear();
	
}
