package com.andrelleitao.app.core.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomMessageException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private List<String> errors;
	
	public CustomMessageException(String msg) {
		super(msg);
	}
	
	public CustomMessageException(String msg, List<String> errors) {
		super(msg);
		this.errors = errors;
	}
}
