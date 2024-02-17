package com.andrelleitao.app.core;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.andrelleitao.app.core.exception.CustomMessageException;
import com.andrelleitao.app.core.search.request.RequestFilter;
import com.andrelleitao.app.core.search.request.RequestPageFilter;

import jakarta.validation.Valid;

public abstract class BaseControllerImpl<PK, T extends BaseEntity<PK>, S extends BaseService<PK, T, SP, R>, SP extends BaseSpecificationImpl<PK, T>, R extends BaseRepository<PK, T>, DTO extends BaseEntityResource<PK>>
		implements BaseController<PK, T, S, SP, R, DTO> {
	
	@Autowired
	private S service;

	@PostMapping
	@Override
	public ResponseEntity<DTO> save(@Valid @RequestBody T entity) throws CustomMessageException {
		return ResponseEntity.status(HttpStatus.CREATED).body(toResource(service.save(entity)));
	}

	@PatchMapping("/{id}")
	@Override
	public ResponseEntity<Void> update(@RequestBody DTO resource, @PathVariable("id") PK id)
			throws CustomMessageException {
		Optional<T> entityOptional = service.findById(id);

		if (!entityOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		// Converte o DTO(Resource) para T
		resource.setId(id);
		T entity = fromResource(resource, entityOptional.get());

		service.save(entity);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> delete(@PathVariable("id") PK id) throws CustomMessageException {
		Optional<T> entityOptional = service.findById(id);

		if (!entityOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		try {
			service.getRepository().deleteById(id);
		} catch (DataIntegrityViolationException e) {
			Throwable t = e.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}
			if (t instanceof ConstraintViolationException) {
				t = t.getCause();
			}
		}

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<DTO> findById(@PathVariable("id") PK id) throws CustomMessageException {
		Optional<T> entityOptional = service.findById(id);

		if (!entityOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(toResource(entityOptional.get()));
	}

	@GetMapping
	@Override
	@Transactional(readOnly = true)
	public List<DTO> findAll() throws CustomMessageException {
		List<T> entities = service.findAll();
		return entities.stream().map(this::toResource).collect(Collectors.toList());
	}

	@PostMapping("/pageable")
	@Override
	@Transactional(readOnly = true)
	public Page<DTO> pageable(@RequestBody RequestPageFilter request) throws CustomMessageException {
		Page<DTO> entities = service
				.pageable(request.getFilters(), request.getPage(), request.getSize(), request.getOrders())
				.map(this::toResource);
		return entities;
	}

	@PostMapping("/findByFilters")
	@Override
	@Transactional(readOnly = true)
	public List<DTO> findByFilters(@RequestBody RequestFilter request) throws CustomMessageException {
		List<T> entities = service.findByFilters(request.getFilters());
		return entities.stream().map(this::toResource).collect(Collectors.toList());
	}

	@Override
	public S getService() {
		return service;
	}

	public abstract T fromResource(DTO resource, T entity);

	public abstract DTO toResource(T entity);

	public T fromBaseResource(DTO resource, T entity) {
		if (resource.getId() != null) {
			entity.setId(resource.getId());
		}
		return entity;
	}
	
}
