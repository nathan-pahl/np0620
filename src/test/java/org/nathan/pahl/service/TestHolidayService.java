package org.nathan.pahl.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nathan.pahl.holiday.IHolidayCheck;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestHolidayService {
	
	private HolidayService holidayService;
	
	private List<IHolidayCheck> holidayChecks;
	
	@Mock
	private IHolidayCheck holidayCheck;
	
	@BeforeEach
	public void beforeEach() {
		holidayChecks = new ArrayList<>();
		holidayChecks.add(holidayCheck);
		holidayService = new HolidayService(holidayChecks);
	}
	
	@Test
	public void testIsHoliday_withEmptyHolidayChecks() {
		LocalDate date = LocalDate.parse("2020-06-16");
		holidayChecks = new ArrayList<>();
		
		boolean result = holidayService.isHoliday(date);
		
		assertFalse(result);
	}
	
	@Test
	public void testIsHoliday_withNoMatching() {
		LocalDate date = LocalDate.parse("2020-06-16");
		when(holidayCheck.isHoliday(date)).thenReturn(false);
		
		boolean result = holidayService.isHoliday(date);
		
		assertFalse(result);
	}
	
	@Test
	public void testIsHoliday_withOneMatching() {
		LocalDate date = LocalDate.parse("2020-06-16");
		when(holidayCheck.isHoliday(date)).thenReturn(true);
		
		boolean result = holidayService.isHoliday(date);
		
		assertTrue(result);
	}

}
