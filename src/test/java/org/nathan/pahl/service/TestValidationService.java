package org.nathan.pahl.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nathan.pahl.exception.InvalidDiscountException;
import org.nathan.pahl.exception.InvalidRentalDaysException;

public class TestValidationService {

	private ValidationService validationService;
	
	@BeforeEach
	public void beforeEach() {
		validationService = new ValidationService();
	}
	
	@Test
	public void testValidateDiscount_withValidDiscount() {
		int discount = 50;
		assertDoesNotThrow(() -> validationService.validateDiscount(discount));
	}
	
	@Test
	public void testValidateDiscount_withInvalidDiscount() {
		int discount = -50;
		assertThrows(InvalidDiscountException.class, () -> validationService.validateDiscount(discount));
	}
	
	@Test
	public void testValidateRentalDays_withValidRentalDays() {
		int rentalDays = 5;
		assertDoesNotThrow(() -> validationService.validateRentalDays(rentalDays));
	}
	
	@Test
	public void testValidateRentalDays_withInvalidRentalDays() {
		int rentalDays = 0;
		assertThrows(InvalidRentalDaysException.class, () -> validationService.validateRentalDays(rentalDays));
	}
	
}
