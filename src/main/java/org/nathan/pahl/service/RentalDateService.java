package org.nathan.pahl.service;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

/**
 * Service used to help determine the due date, charging date, and total days between.
 */
@Service
public class RentalDateService {

	public RentalDateService() {}
	
	/**
	 * Calculate the due date.
	 * 
	 * @param checkoutDate the date to start from
	 * @param rentalDays the number of days to rent
	 * @return a due date in the form of {@link LocalDate}
	 */
	public LocalDate calculateDueDate(LocalDate checkoutDate, long rentalDays) {
		return checkoutDate.plusDays(rentalDays);
	}
	
	/**
	 * Calculate the start charging date. Typically 1 day after checkout date.
	 * 
	 * @param checkoutDate the date to start from
	 * @return the checkoutDate plus one day
	 */
	public LocalDate calculateStartChargingDate(LocalDate checkoutDate) {
		return this.calculateStartChargingDate(checkoutDate, 1); // Add one day because we start charging the date AFTER checkout
	}
	
	/**
	 * Calculate the start charging date. Typically 1 day after checkout date.
	 * 
	 * @param checkoutDate the date to start from
	 * @param dayOffset the number of days to offset the start charge date
	 * @return the checkoutDate plus the dayOffset amount
	 */
	public LocalDate calculateStartChargingDate(LocalDate checkoutDate, long dayOffset) {
		return checkoutDate.plusDays(dayOffset);
	}

	/**
	 * Get the {@link LocalDate}s between begin and end.
	 * 
	 * @param begin the {@link LocalDate} to begin with
	 * @param end the {@link LocalDate} to end with
	 * @return a {@link Stream} of {@link LocalDate}s between the two provided {@link LocalDate}s
	 */
	public Stream<LocalDate> getDatesUntil(LocalDate begin, LocalDate end) {
		return begin.datesUntil(end);
	}
	
}
