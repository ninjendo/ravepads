package com.rave.pads.exception;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;

public class MissingDataException extends HttpStatusException {

	private static final long serialVersionUID = -3543157183551872526L;

	public MissingDataException(String message) {
		super(HttpStatus.UNPROCESSABLE_ENTITY, message);
	}
}
