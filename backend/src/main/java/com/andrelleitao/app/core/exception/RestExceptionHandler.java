package com.andrelleitao.app.core.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Trata o retorno do CustomMessageException para o cliente.
	 */
	@ExceptionHandler({ CustomMessageException.class })
	protected ResponseEntity<ErrorApi> handleCustomMessageException(final CustomMessageException ex,
			final WebRequest request) {
		
		final ErrorApi apiError = new ErrorApi(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		
	}
	
	/**
	 * Trata o retorno para campos que não passaram na validação.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		final ErrorApi apiError = new ErrorApi(HttpStatus.BAD_REQUEST, "Invalid fields");
		return ResponseEntity.status(status).body(apiError);
		
	}
	
}
