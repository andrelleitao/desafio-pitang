package com.andrelleitao.app.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.andrelleitao.app.core.exception.CustomMessageException;
import com.andrelleitao.app.core.search.request.RequestFilter;
import com.andrelleitao.app.core.search.request.RequestPageFilter;

public interface BaseController<PK, T extends BaseEntity<PK>, 
	S extends BaseService<PK, T, SP, R>, SP extends BaseSpecificationImpl<PK, T>, R extends BaseRepository<PK, T>, DTO> {
	
	ResponseEntity<DTO> save(T entity) throws CustomMessageException;

	ResponseEntity<Void> update(DTO resource, PK id) throws CustomMessageException;

	ResponseEntity<Void> delete(PK id) throws CustomMessageException;

	ResponseEntity<DTO> findById(PK id) throws CustomMessageException;

	List<DTO> findAll() throws CustomMessageException;

	S getService();

	T fromResource(DTO resource, T entity);

	DTO toResource(T entity);

	Page<DTO> pageable(RequestPageFilter request) throws CustomMessageException;

	List<DTO> findByFilters(RequestFilter request) throws CustomMessageException;
	
}
