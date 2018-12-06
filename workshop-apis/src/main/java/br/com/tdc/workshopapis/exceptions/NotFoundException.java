package br.com.tdc.workshopapis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
