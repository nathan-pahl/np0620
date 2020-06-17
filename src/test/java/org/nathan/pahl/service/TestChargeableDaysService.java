package org.nathan.pahl.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestChargeableDaysService {

	private ChargeableDaysService chargeableDaysService;
	
	@Mock
	private DayOfWeekService dayofWeekService;
	
	@Mock
	private HolidayService holidayService;
	
	@BeforeEach
	public void beforeEach() {
		chargeableDaysService = new ChargeableDaysService(dayofWeekService, holidayService);
	}
	
	@Test
	public void testCalculateChargeableDays_withNoDates() {
		Stream<LocalDate> dates = Stream.of();
		
		long result = chargeableDaysService.calculateChargeableDays(dates, true, true, true);

		assertEquals(0, result);
	}
	
	@Test
	public void testCalculateChargeableDays_withSomeDates() {
		Stream<LocalDate> dates = Stream.of(LocalDate.parse("2020-06-16"), LocalDate.parse("2020-06-16"));
		
		when(dayofWeekService.isWeekday(any(LocalDate.class))).thenReturn(true);
		
		long result = chargeableDaysService.calculateChargeableDays(dates, true, true, true);

		assertEquals(2, result);
	}
	
	@Test
	public void testIsChargeableDay_withWeekdayChargeAndNoHoliday() {
		LocalDate date = LocalDate.parse("2020-06-16");
		boolean weekdayCharge = true;
		boolean weekendCharge = false;
		boolean holidayCharge = false;
		
		when(dayofWeekService.isWeekday(date)).thenReturn(weekdayCharge);

		boolean result = chargeableDaysService.isChargeableDay(date, weekdayCharge, weekendCharge, holidayCharge);
		
		assertTrue(result);
	}
	
	@Test
	public void testIsChargeableDay_withWeekdayChargeAndHoliday() {
		LocalDate date = LocalDate.parse("2020-06-16");
		boolean weekdayCharge = true;
		boolean weekendCharge = false;
		boolean holidayCharge = false;
		
		when(holidayService.isHoliday(date)).thenReturn(true);

		boolean result = chargeableDaysService.isChargeableDay(date, weekdayCharge, weekendCharge, holidayCharge);
		
		assertFalse(result);
	}

	@Test
	public void testIsChargeableDay_withWeekendChargeAndNoHoliday() {
		LocalDate date = LocalDate.parse("2020-06-16");
		boolean weekdayCharge = false;
		boolean weekendCharge = true;
		boolean holidayCharge = false;
		
		when(dayofWeekService.isWeekend(date)).thenReturn(true);

		boolean result = chargeableDaysService.isChargeableDay(date, weekdayCharge, weekendCharge, holidayCharge);
		
		assertTrue(result);
	}
	
	@Test
	public void testIsChargeableDay_withWeekendChargeAndHoliday() {
		LocalDate date = LocalDate.parse("2020-06-16");
		boolean weekdayCharge = false;
		boolean weekendCharge = true;
		boolean holidayCharge = false;
		
		when(holidayService.isHoliday(date)).thenReturn(true);

		boolean result = chargeableDaysService.isChargeableDay(date, weekdayCharge, weekendCharge, holidayCharge);
		
		assertFalse(result);
	}
	
	@Test
	public void testIsChargeableDay_withHolidayChargeAndNoHoliday() {
		LocalDate date = LocalDate.parse("2020-06-16");
		boolean weekdayCharge = false;
		boolean weekendCharge = false;
		boolean holidayCharge = true;
		
		when(holidayService.isHoliday(date)).thenReturn(false);

		boolean result = chargeableDaysService.isChargeableDay(date, weekdayCharge, weekendCharge, holidayCharge);
		
		assertFalse(result);
	}
	
	@Test
	public void testIsChargeableDay_withHolidayChargeAndHoliday() {
		LocalDate date = LocalDate.parse("2020-06-16");
		boolean weekdayCharge = false;
		boolean weekendCharge = false;
		boolean holidayCharge = true;
		
		when(holidayService.isHoliday(date)).thenReturn(true);

		boolean result = chargeableDaysService.isChargeableDay(date, weekdayCharge, weekendCharge, holidayCharge);
		
		assertTrue(result);
	}
	
}
