package com.andrelleitao.app.core.search.request;

import java.util.List;

import com.andrelleitao.app.core.search.Filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RequestFilter {
	private List<Filter> filters;
}
