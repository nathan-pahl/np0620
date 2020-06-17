package org.nathan.pahl.holiday;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nathan.pahl.holiday.impl.IndependenceDayCheck;

public class TestIndependenceDayCheck {
	
	private IndependenceDayCheck independenceDayCheck;

	@BeforeEach
	public void beforeEach() {
		independenceDayCheck = new IndependenceDayCheck();
	}
	
	@Test
	public void testIsIndependenceDay_withNonIndependenceDay() {
		LocalDate date = LocalDate.parse("2020-06-15");
		boolean result = independenceDayCheck.isHoliday(date);
		assertFalse(result);
	}
	
	@Test
	public void testIsIndependenceDay_withIndependenceDay() {
		LocalDate date = LocalDate.parse("2019-07-04");
		boolean result = independenceDayCheck.isHoliday(date);
		assertTrue(result);
	}
	
	@Test
	public void testIsIndependenceDay_withIndependenceDayOnASaturday() {
		LocalDate date = LocalDate.parse("2020-07-03");
		boolean result = independenceDayCheck.isHoliday(date);
		assertTrue(result);
	}
	
	@Test
	public void testIsIndependenceDay_withIndependenceDayOnASunday() {
		LocalDate date = LocalDate.parse("2021-07-05");
		boolean result = independenceDayCheck.isHoliday(date);
		assertTrue(result);
	}
	
}
