package org.nathan.pahl.service;

import org.nathan.pahl.exception.InvalidDiscountException;
import org.nathan.pahl.exception.InvalidRentalDaysException;
import org.springframework.stereotype.Service;

/**
 * Service used to validate discounts and rental days.
 */
@Service
public class ValidationService {
    
	/**
	 * Validate the provided discount.
	 * 
	 * @param discount the discount amount to validate
	 */
    public void validateDiscount(int discount) {
        if(discount < 0 || discount > 100) {
        	String message = buildMessage("We're sorry, we are unable to process your request due to the provided discount amount. Discounts must be in the range of 0-100.");
            throw new InvalidDiscountException(message);
        }
    }

    /**
	 * Validate the provided rentalDays.
	 * 
	 * @param rentalDays the rental days amount to validate
	 */
    public void validateRentalDays(int rentalDays) {
        if(rentalDays < 1) {
        	String message = "We're sorry, we are unable to process your request due to the provided rental day amount. Rental days must be at least 1.";
            throw new InvalidRentalDaysException(message);
        }
    }
    
    /**
     * Build the message to instantiate the exception with.
     * 
     * @param errorMessage the error message
     * @return a String containing the error message
     */
    private String buildMessage(String errorMessage) {
    	StringBuilder builder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");
        return builder.append(errorMessage).append(lineSeparator).toString();
    }

}