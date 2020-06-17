package org.nathan.pahl.holiday;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nathan.pahl.holiday.impl.MemorialDayCheck;

public class TestMemorialDayCheck {

	private MemorialDayCheck memorialDayCheck;
	
	@BeforeEach
	public void beforeEach() {
		memorialDayCheck = new MemorialDayCheck();
	}
	
	@Test
	public void testIsHoliday_withNonMemorialDay() {
		LocalDate date = LocalDate.parse("2020-06-15");
		boolean result = memorialDayCheck.isHoliday(date);
		assertFalse(result);
	}
	
	@Test
	public void testIsHoliday_withMemorialDay() {
		LocalDate date = LocalDate.parse("2020-09-07");
		boolean result = memorialDayCheck.isHoliday(date);
		assertTrue(result);
	}
	
}
