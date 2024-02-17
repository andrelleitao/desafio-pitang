package com.andrelleitao.app.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.andrelleitao.app.core.search.SearchCriteria;
import com.andrelleitao.app.core.search.enums.SearchOperation;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;

@Getter
public class BaseSpecificationImpl<PK, T extends BaseEntity<PK>> implements BaseSpecification<PK, T> {
	private static final long serialVersionUID = 1L;

	private List<SearchCriteria> list;

	public BaseSpecificationImpl() {
		this.list = new ArrayList<>();
	}

	@Override
	public void add(SearchCriteria criteria) {
		list.add(criteria);
	}
	
	
	public void clear() {
		list.clear();
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = getPredicates(root, query, builder);
		
		return builder.and(predicates.toArray(new Predicate[0]));
	}

	@SuppressWarnings("unchecked")	
	public List<Predicate> getPredicates(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		// Cria uma nova lista de predicados do Criteria.
		List<Predicate> predicates = new ArrayList<>();

		// Adiciona os predicados baseando-se nos critérios fornecidos.		
		Path<?> path = null;

		for (SearchCriteria criteria : list) {
			// Caso seja informado na key o atributo de uma entidade de
			// relacionamento, será separado o atributo da key e em seguida
			// realizado o join para levar em consideração o atributo da entidade
			// de relacionamento.
			if (criteria.getKey() != null) {
				path = getPath(criteria, root);

				if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {					
					if (path.getJavaType() == LocalDate.class) {
						predicates.add(builder.greaterThan((Path<LocalDate>) path,
								LocalDate.parse(criteria.getValue().toString())));
					} else if (path.getJavaType() == LocalDateTime.class) {
						predicates.add(builder.greaterThan((Path<LocalDateTime>) path,
								LocalDateTime.parse(criteria.getValue().toString())));
					} else if(root.get(criteria.getKey()).getJavaType() == Integer.class) {
						predicates.add(builder.greaterThan((Path<Integer>) path,
								Integer.valueOf(criteria.getValue().toString())));
					}
				} else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
					if (path.getJavaType() == LocalDate.class) {
						predicates.add(builder.lessThan((Path<LocalDate>) path,
								LocalDate.parse(criteria.getValue().toString())));
					} else if (path.getJavaType() == LocalDateTime.class) {
						predicates.add(builder.lessThan((Path<LocalDateTime>) path,
								LocalDateTime.parse(criteria.getValue().toString())));
					} else if(root.get(criteria.getKey()).getJavaType() == Integer.class) {
						predicates.add(builder.lessThan((Path<Integer>) path,
								Integer.valueOf(criteria.getValue().toString())));
					}
				} else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
					if (path.getJavaType() == LocalDate.class) {
						predicates.add(builder.greaterThanOrEqualTo((Path<LocalDate>) path,
								LocalDate.parse(criteria.getValue().toString())));
					} else if (root.get(criteria.getKey()).getJavaType() == LocalDateTime.class) {
						predicates.add(builder.greaterThanOrEqualTo((Path<LocalDateTime>) path,
								LocalDateTime.parse(criteria.getValue().toString())));
					} else if(root.get(criteria.getKey()).getJavaType() == Integer.class) {
						predicates.add(builder.greaterThanOrEqualTo((Path<Integer>) path,
								Integer.valueOf(criteria.getValue().toString())));
					}
				} else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
					if (path.getJavaType() == LocalDate.class) {
						predicates.add(builder.lessThanOrEqualTo((Path<LocalDate>) path,
								LocalDate.parse(criteria.getValue().toString())));
					} else if (path.getJavaType() == LocalDateTime.class) {
						predicates.add(builder.lessThanOrEqualTo((Path<LocalDateTime>) path,
								LocalDateTime.parse(criteria.getValue().toString())));
					} else if(root.get(criteria.getKey()).getJavaType() == Integer.class) {
						predicates.add(builder.lessThanOrEqualTo((Path<Integer>) path,
								Integer.valueOf(criteria.getValue().toString())));
					}
				} else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
					predicates.add(builder.notEqual(path, criteria.getValue()));
				} else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
					if (path.getJavaType() == LocalDate.class) {
						predicates.add(builder.equal(path, LocalDate.parse(criteria.getValue().toString())));
					} else {
						predicates.add(builder.equal(path, criteria.getValue()));
					}					
				} else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
					predicates.add(builder.like(builder.lower((Path<String>) path),
							"%" + criteria.getValue().toString().toLowerCase() + "%"));
				} else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
					predicates.add(builder.like(builder.lower((Path<String>) path),
							criteria.getValue().toString().toLowerCase() + "%"));
				} else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
					predicates.add(builder.like(builder.lower((Path<String>) path),
							"%" + criteria.getValue().toString().toLowerCase()));
				} else if (criteria.getOperation().equals(SearchOperation.IN)) {
					List<?> in = null;

					if (path.getJavaType() == Long.class) {
						// Cria uma lista de longs com a lista de integer.
						// Essa crítica é necessária pois ao receber filtros
						// numéricos do frontend a aplicação está entendendo
						// que é um Integer e não Long. Esse código permite que,
						// caso seja uma lista de inteiro, haja conversão de uma lista para Long.
						if (criteria.getValue() instanceof List) {
							if (((List<?>) criteria.getValue()).size() > 0
									&& (((List<?>) criteria.getValue()).get(0) instanceof Integer)) {
								List<Integer> ints = (List<Integer>) criteria.getValue();
								criteria.setValue(ints.stream().map(x -> Long.valueOf(x)).collect(Collectors.toList()));
							}
						}

						in = ((List<Long>) criteria.getValue()).stream().map(x -> x).collect(Collectors.toList());
					} else if (path.getJavaType() == String.class) {
						in = ((List<String>) criteria.getValue()).stream().map(x -> x).collect(Collectors.toList());
					} else if (path.getJavaType() == Integer.class) {
						in = ((List<Integer>) criteria.getValue()).stream().map(x -> x).collect(Collectors.toList());
					}

					predicates.add(path.in(in));
				} else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
					List<?> in = null;

					if (path.getJavaType() == Long.class) {
						in = ((List<Long>) criteria.getValue()).stream().map(x -> x).collect(Collectors.toList());
					}

					predicates.add(path.in(in).not());
				} else if (criteria.getOperation().equals(SearchOperation.IS_NULL)) {
					predicates.add(builder.isNull(path));
				}
			}
		}

		return predicates;
	}
	
	public Path<?> getPath(SearchCriteria criteria, Root<T> root) {
		String[] attributes = null;
		Path<?> path = null;
		
		if (criteria.getKey().indexOf(".") > 0) {
			attributes = criteria.getKey().split("\\.");

			for (String attr : attributes) {
				if (path == null) {
					path = root.get(attr);

					// Tratativa para atributo que é uma lista e
					// portanto necessita realizar um JOIN.
					if (path.getJavaType().equals(Set.class)) {
						path = root.join(attr);
					}
				} else {
					path = path.get(attr);
				}
			}
		} else {
			path = root.get(criteria.getKey());
		}
		
		return path;
	}
}
