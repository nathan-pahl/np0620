package org.nathan.pahl.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception used to describe an invalid rental days amount.
 */
public class InvalidRentalDaysException extends RuntimeException {
	
	private static final long serialVersionUID = 3222636308813313074L;
	private static final Logger logger = LoggerFactory.getLogger(InvalidRentalDaysException.class);

	public InvalidRentalDaysException(String message) {
		super(message);
    	logger.error(message);
	}
	
}
