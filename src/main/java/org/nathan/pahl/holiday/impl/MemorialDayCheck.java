package org.nathan.pahl.holiday.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

import org.nathan.pahl.holiday.IHolidayCheck;
import org.springframework.stereotype.Component;

@Component
public class MemorialDayCheck implements IHolidayCheck {

	@Override
	public boolean isHoliday(LocalDate date) {
		if(date.getMonth().equals(Month.SEPTEMBER)) {
			LocalDate firstMonday = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth()).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
			return date.equals(firstMonday);
		}
		return false;
	}

}
