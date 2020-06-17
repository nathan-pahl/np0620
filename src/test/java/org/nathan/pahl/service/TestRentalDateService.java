package org.nathan.pahl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRentalDateService {

	private RentalDateService rentalDateService;
	
	@BeforeEach
	public void beforeEach() {
		rentalDateService = new RentalDateService();
	}
	
	@Test
	public void testCalculateDueDate_withNoRentalDays() {
		LocalDate date = LocalDate.parse("2020-06-15");
		LocalDate expected = date;
		LocalDate result = rentalDateService.calculateDueDate(date, 0);
		assertEquals(expected, result);
	}
	
	@Test
	public void testCalculateDueDate_withRentalDays() {
		LocalDate date = LocalDate.parse("2020-06-15");
		LocalDate expected = LocalDate.parse("2020-06-20");
		LocalDate result = rentalDateService.calculateDueDate(date, 5);
		assertEquals(expected, result);
	}
	
	@Test
	public void testStartChargingDate() {
		LocalDate date = LocalDate.parse("2020-06-15");
		LocalDate expected = LocalDate.parse("2020-06-16");
		LocalDate result = rentalDateService.calculateStartChargingDate(date);
		assertEquals(expected, result);
	}
	
}
