package org.nathan.pahl.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception used to describe an invalid discount amount.
 */
public class InvalidDiscountException extends RuntimeException {
	
	private static final long serialVersionUID = -3922542023910086177L;
	private static final Logger logger = LoggerFactory.getLogger(InvalidDiscountException.class);

	public InvalidDiscountException(String message) {
		super(message);
    	logger.error(message);
	}
	
}
