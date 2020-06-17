package org.nathan.pahl.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDayOfWeekService {

	private DayOfWeekService dayOfWeekService;
	
	@BeforeEach
	public void beforeEach() {
		dayOfWeekService = new DayOfWeekService();
	}
	
	@Test
	public void testIsWeekday_withNonWeekday() {
		LocalDate date = LocalDate.parse("2020-06-20");
		boolean result = dayOfWeekService.isWeekday(date);
		assertFalse(result);
	}
	
	@Test
	public void testIsWeekday_withWeekday() {
		LocalDate date = LocalDate.parse("2020-06-15");
		boolean result = dayOfWeekService.isWeekday(date);
		assertTrue(result);
	}
	
	@Test
	public void testIsWeekend_withNonWeekend() {
		LocalDate date = LocalDate.parse("2020-06-15");
		boolean result = dayOfWeekService.isWeekend(date);
		assertFalse(result);
	}
	
	@Test
	public void testIsWeekend_withWeekend() {
		LocalDate date = LocalDate.parse("2020-06-20");
		boolean result = dayOfWeekService.isWeekend(date);
		assertTrue(result);
	}
	
}
