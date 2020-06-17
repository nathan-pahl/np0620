package org.nathan.pahl.service;

import java.time.LocalDate;
import java.util.List;

import org.nathan.pahl.holiday.IHolidayCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service used to help determine if a {@link LocalDate} is on a holiday.
 */
@Service
public class HolidayService {
	
	private List<IHolidayCheck> holidayChecks;
	
	@Autowired
	public HolidayService(List<IHolidayCheck> holidayChecks) {
		this.holidayChecks = holidayChecks;
	}
	
	/**
	 * Run through the list of implemented {@link IHolidayCheck} components
	 * to help determine if the provided {@link LocalDate} is a holiday.
	 * 
	 * @param date the {@link LocalDate} to check
	 * @return true if the {@link LocalDate} is a holiday, else false
	 */
	public boolean isHoliday(LocalDate date) {
		return this.holidayChecks.stream().anyMatch(holidayCheck -> holidayCheck.isHoliday(date));
	}
	
}
