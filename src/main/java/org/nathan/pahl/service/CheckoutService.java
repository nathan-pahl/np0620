package org.nathan.pahl.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.nathan.pahl.dao.ToolDao;
import org.nathan.pahl.domain.RentalAgreement;
import org.nathan.pahl.factory.RentalAgreementFactory;
import org.nathan.pahl.model.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service used to collect the information from the different services and create a {@link RentalAgreement}.
 */
@Service
public class CheckoutService {
	
	private ValidationService validationService;
	private ToolDao toolDao;
	private ChargeableDaysService chargeableDaysService;
	private RentalAgreementFactory rentalAgreementFactory;
	private RentalChargeService rentalChargeService;
	private RentalDateService rentalDateService;
	
	@Autowired
	public CheckoutService(ValidationService validationService, ToolDao toolDao,
			ChargeableDaysService chargeableDaysService, RentalAgreementFactory rentalAgreementFactory,
			RentalChargeService rentalChargeService, RentalDateService rentalDateService) {
		this.validationService = validationService;
		this.toolDao = toolDao;
		this.chargeableDaysService = chargeableDaysService;
		this.rentalAgreementFactory = rentalAgreementFactory;
		this.rentalChargeService = rentalChargeService;
		this.rentalDateService = rentalDateService;
	}
	
	/**
	 * Checkout a {@link Tool} and receive the {@link RentalAgreement}.
	 * 
	 * @param toolCode the ID of the {@link Tool} to be rented
	 * @param checkoutDate the {@link LocalDate} to checkout the tool
	 * @param rentalDays the number of days to rent a tool
	 * @param discount the discount amount
	 * @return a populated {@link RentalAgreement}
	 */
	public RentalAgreement checkout(String toolCode, LocalDate checkoutDate, int rentalDays, int discount) {
		this.validationService.validateRentalDays(rentalDays);
		this.validationService.validateDiscount(discount);
		
		Tool tool = this.toolDao.findToolByToolCode(toolCode);
		
		LocalDate dueDate = this.rentalDateService.calculateDueDate(checkoutDate, rentalDays);
		LocalDate startChargingDate = this.rentalDateService.calculateStartChargingDate(checkoutDate);
		Stream<LocalDate> rentalDates = this.rentalDateService.getDatesUntil(startChargingDate, dueDate);
		long chargeableDays = this.chargeableDaysService.calculateChargeableDays(rentalDates, tool.isWeekdayCharge(), tool.isWeekendCharge(), tool.isHolidayCharge());
		
		BigDecimal dailyCharge = this.rentalChargeService.getDailyCharge(tool.getDailyCharge());
		BigDecimal preDiscountCharge = this.rentalChargeService.getPreDiscountCharge(chargeableDays, dailyCharge);
		BigDecimal discountPercentage = this.rentalChargeService.getDiscountPercentage(discount);
		BigDecimal discountAmount = this.rentalChargeService.calculateDiscountAmount(preDiscountCharge, discountPercentage);
		BigDecimal finalCharge = this.rentalChargeService.calculateFinalAmount(preDiscountCharge, discountAmount);
		
		return this.rentalAgreementFactory.create(toolCode, tool.getToolType(), tool.getBrandName(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeableDays, preDiscountCharge, discount, discountAmount, finalCharge);
	}

}
