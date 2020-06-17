package org.nathan.pahl.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * Service used to help determine if a {@link LocalDate} is on a weekday or weekend.
 */
@Service
public class DayOfWeekService {

	private final Set<DayOfWeek> weekday = EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
	private final Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
	
	/**
	 * Check if the provided {@link LocalDate} is on a weekday.
	 * 
	 * @param date the {@link LocalDate} to check
	 * @return true if it is a weekday, else false
	 */
	public boolean isWeekday(LocalDate date) {
		return weekday.contains(date.getDayOfWeek());
	}
	
	/**
	 * Check if the provided {@link LocalDate} is on a weekend.
	 * 
	 * @param date the {@link LocalDate} to check
	 * @return true if it is a weekend, else false
	 */
	public boolean isWeekend(LocalDate date) {
		return weekend.contains(date.getDayOfWeek());
	}
}

