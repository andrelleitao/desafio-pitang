package com.andrelleitao.app.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.andrelleitao.app.constants.Common;
import com.andrelleitao.app.core.exception.CustomMessageException;
import com.andrelleitao.app.core.search.Filter;
import com.andrelleitao.app.core.search.Order;
import com.andrelleitao.app.core.search.SearchCriteria;
import com.andrelleitao.app.core.search.enums.SearchOperation;

/**
 * Estrutura base para criação de novas classes.
 * 
 */
public abstract class BaseServiceImpl<PK, T extends BaseEntity<PK>, 
	SP extends BaseSpecification<PK, T>, R extends BaseRepository<PK, T>>
		implements BaseService<PK, T, SP, R> {
	
	@Autowired
	private R repository;
	
	@Autowired
	private SP spec;

	@Transactional(rollbackFor = {Exception.class})
	@Override
	public T save(T entity) throws CustomMessageException {
		return getRepository().save(entity);
	}

	@Override
	public Page<T> pageable(List<Filter> filters, Integer page, Integer size, List<Order> orders) {
		page = (page == null) ? 0 : page;
		size = (size == null) ? Common.PAGE_SIZE : size;

		spec = getSpecification();

		filters.stream().forEach(filter -> {
			spec.add(new SearchCriteria(filter.getField(), filter.getOperation(), filter.getValue()));
		});

		List<org.springframework.data.domain.Sort.Order> filterOrders = new ArrayList<org.springframework.data.domain.Sort.Order>();
		if (orders != null) {
			orders.stream().forEach(order -> {
				filterOrders
						.add(new org.springframework.data.domain.Sort.Order(order.getDirection(), order.getField()));
			});
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by(filterOrders));

		return repository.findAll(spec, pageable);
	}

	@Override
	public List<T> findByFilters(List<Filter> filters) {
		spec = getSpecification();

		filters.stream().forEach(filter -> {
			spec.add(new SearchCriteria(filter.getField(), filter.getOperation(), filter.getValue()));
		});

		return this.findAll(spec);
	}

	@Override
	public List<T> findAll(SP spec) {
		return getRepository().findAll(spec);
	}

	@Override
	public Optional<T> findById(PK id) {
		SP spec = getSpecification();
		spec.add(new SearchCriteria("id", SearchOperation.EQUAL, id));
		
		List<T> entities = this.findAll(spec);
		return entities.stream().findFirst();
	}

	@Override
	public R getRepository() {
		return repository;
	}
	
	@Override
	public SP getSpecification() {
		spec.clear();
		return spec;
	}

	@Override
	public void deleteById(PK id) throws CustomMessageException {
		try {
			getRepository().deleteById(id);
		} catch (DataIntegrityViolationException e) {
			Throwable t = e.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}
			if (t instanceof ConstraintViolationException) {
				throw new CustomMessageException(t.getMessage());
			}
		}
	}
	
	@Override
	public void deleteByIds(List<PK> ids) throws CustomMessageException {
		for(PK id : ids) {
			this.deleteById(id);
		}
	}
	
	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}
	
}
