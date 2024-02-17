package com.andrelleitao.app.core.search;

import com.andrelleitao.app.core.search.enums.SearchOperation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
	private String field;
	private Object value;
	private SearchOperation operation;
	
	public Filter(String field, SearchOperation operation) {
		this.field = field;
		this.operation = operation;
	}
}