package org.nathan.pahl.holiday.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import org.nathan.pahl.holiday.IHolidayCheck;
import org.springframework.stereotype.Component;

@Component
public class IndependenceDayCheck implements IHolidayCheck {

	@Override
	public boolean isHoliday(LocalDate date) {
		LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
		if(independenceDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			independenceDay = independenceDay.minusDays(1);
		} else if(independenceDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			independenceDay = independenceDay.plusDays(1);
		}
		return independenceDay.equals(date);
	}
	
}
