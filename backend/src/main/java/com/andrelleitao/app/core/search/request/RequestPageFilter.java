package com.andrelleitao.app.core.search.request;

import java.util.ArrayList;
import java.util.List;

import com.andrelleitao.app.core.search.Filter;
import com.andrelleitao.app.core.search.Order;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestPageFilter {
	private List<Filter> filters;
	private Integer page;
	private Integer size;
	private List<Order> orders;

	public List<Filter> getFilters() {
		if (filters == null) {
			filters = new ArrayList<Filter>();
		}

		return filters;
	}
}
