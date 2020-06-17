package org.nathan.pahl.service;

import java.math.BigDecimal;

import org.nathan.pahl.factory.BigDecimalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service used to help create the charges and percentages in {@link BigDecimal} format.
 */
@Service
public class RentalChargeService {
	
	private BigDecimalFactory bigDecimalFactory;
	
	@Autowired
	public RentalChargeService(BigDecimalFactory bigDecimalFactory) {
		this.bigDecimalFactory = bigDecimalFactory;
	}
	
	/**
	 * Get the daily charge as a {@link BigDecimal}.
	 * 
	 * @param dailyRentalCharge the current daily charge
	 * @return a {@link BigDecimal} form of dailyRentalCharge
	 */
	public BigDecimal getDailyCharge(double dailyRentalCharge) {
		return bigDecimalFactory.create(dailyRentalCharge);
	}
	
	/**
	 * Get the pre-discount charge as a {@link BigDecimal}.
	 * This is chargeableDays x dailyCharge.
	 * 
	 * @param chargeableDays the number of chargeable days
	 * @param dailyCharge the current daily charge
	 * @return a {@link BigDecimal} form of preDiscountCharge
	 */
	public BigDecimal getPreDiscountCharge(long chargeableDays, BigDecimal dailyCharge) {
		return bigDecimalFactory.create(bigDecimalFactory.create(chargeableDays).multiply(dailyCharge));
	}
	
	/**
	 * Get the discount percentage as a {@link BigDecimal}.
	 * This multiplies the current discount by 0.01 to get a percentage.
	 * 
	 * @param discount the current discount amound
	 * @return a {@link BigDecimal} form of discountPercentage
	 */
	public BigDecimal getDiscountPercentage(int discount) {
		return bigDecimalFactory.create(discount).multiply(bigDecimalFactory.create("0.01"));
	}
	
	/**
	 * Calculate the discount amount as a {@link BigDecimal}.
	 * This is preDiscountCharge x discountPercentage
	 * 
	 * @param preDiscountCharge the amount of charges before discount
	 * @param discountPercentage the discount percentage
	 * @return a {@link BigDecimal} form of discountAmount
	 */
	public BigDecimal calculateDiscountAmount(BigDecimal preDiscountCharge, BigDecimal discountPercentage) {
		return bigDecimalFactory.create(preDiscountCharge.multiply(discountPercentage));
	}
	
	/**
	 * Calculate the final amount as a {@link BigDecimal}.
	 * This is preDiscountCharge - discountAmount.
	 * 
	 * @param preDiscountCharge the amount of charges before discount
	 * @param discountAmount the discount amount
	 * @return a {@link BigDecimal} form of finalAmount
	 */
	public BigDecimal calculateFinalAmount(BigDecimal preDiscountCharge, BigDecimal discountAmount) {
		return bigDecimalFactory.create(preDiscountCharge.subtract(discountAmount));
	}

}
