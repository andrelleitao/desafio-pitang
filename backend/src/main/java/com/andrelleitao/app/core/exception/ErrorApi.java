package com.andrelleitao.app.core.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorApi {
	private Integer errorCode;
    private String message;
 
    public ErrorApi(HttpStatus status, String message) {
        super();
        this.errorCode = status.value();
        this.message = message;
    }
}
