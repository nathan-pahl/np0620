package org.nathan.pahl.factory;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.nathan.pahl.domain.RentalAgreement;
import org.springframework.stereotype.Component;

/**
 * Factory used to create instances of {@link RentalAgreement}.
 */
@Component
public class RentalAgreementFactory {
	
	/**
	 * Create an instance of {@link RentalAgreement} with the provided arguments.
	 */
	public RentalAgreement create(String toolCode, String toolType, String toolBrand, int rentalDays, LocalDate checkoutDate,
			LocalDate dueDate, BigDecimal dailyRentalCharge, long chargeDays, BigDecimal preDiscountCharge,
			int discountPercent, BigDecimal discountAmount, BigDecimal finalCharge) {
		return new RentalAgreement(toolCode, toolType, toolBrand, rentalDays, checkoutDate,
			dueDate, dailyRentalCharge, chargeDays, preDiscountCharge,
			discountPercent, discountAmount, finalCharge);
	}

}
