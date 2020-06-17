package org.nathan.pahl.service;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service used to determine the amount of days to charge for tool rental.
 */
@Service
public class ChargeableDaysService {
	
	private DayOfWeekService dayofWeekService;
	private HolidayService holidayService;

	@Autowired
	public ChargeableDaysService(DayOfWeekService dayofWeekService, HolidayService holidayService) {
		this.dayofWeekService = dayofWeekService;
		this.holidayService = holidayService;
	}
	
	/**
	 * Calculate the number of days to charge for tool rental.
	 * 
	 * @param dates a {@link Stream} of dates to filter
	 * @param weekdayCharge the flag to determine if we charge on a weekday
	 * @param weekendCharge the flag to determine if we charge on a weekend
	 * @param holidayCharge the flag to determine if we charge on a holiday
	 * @return the count of chargeable days
	 */
	public long calculateChargeableDays(Stream<LocalDate> dates, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
		Stream<LocalDate> chargedDays = dates.filter(date -> {
			return isChargeableDay(date, weekdayCharge, weekendCharge, holidayCharge);
		});
		return chargedDays.count();
	}
	
	/**
	 * Determine if the provided {@link LocalDate} is a chargeable day.
	 * 
	 * @param date the {@link LocalDate} to check against
	 * @param weekdayCharge the flag to determine if we charge on a weekday
	 * @param weekendCharge the flag to determine if we charge on a weekend
	 * @param holidayCharge the flag to determine if we charge on a holiday
	 * @return true if the provided {@link LocalDate} is a chargeable day, else false
	 */
	public boolean isChargeableDay(LocalDate date, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
		boolean isHoliday = this.holidayService.isHoliday(date);
		if(holidayCharge && isHoliday) {
			return true;
		} else if(weekendCharge && !isHoliday && this.dayofWeekService.isWeekend(date)) {
			return true;
		} else if(weekdayCharge && !isHoliday && this.dayofWeekService.isWeekday(date)) {
			return true;
		}
		return false;
	}
	
}
