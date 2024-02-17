package com.andrelleitao.app.core;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.andrelleitao.app.core.exception.CustomMessageException;
import com.andrelleitao.app.core.search.Filter;
import com.andrelleitao.app.core.search.Order;

public interface BaseService<PK, T extends BaseEntity<PK>, 
	SP extends BaseSpecification<PK, T>, R extends BaseRepository<PK, T>> {
	
	T save(T entity) throws CustomMessageException;

	Page<T> pageable(List<Filter> filters, Integer page, Integer size, List<Order> orders);

	List<T> findByFilters(List<Filter> filters);

	R getRepository();

	SP getSpecification();

	Optional<T> findById(PK id);

	List<T> findAll();

	List<T> findAll(SP spec);

	void deleteById(PK id) throws CustomMessageException;

	void deleteByIds(List<PK> ids) throws CustomMessageException;
	
}
