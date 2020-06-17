package org.nathan.pahl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nathan.pahl.dao.ToolDao;
import org.nathan.pahl.domain.RentalAgreement;
import org.nathan.pahl.exception.InvalidRentalDaysException;
import org.nathan.pahl.factory.RentalAgreementFactory;
import org.nathan.pahl.model.Tool;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestCheckoutService {

	private CheckoutService checkoutService;
	
	@Mock
	private ValidationService validationService;
	
	@Mock
	private ToolDao toolDao;
	
	@Mock
	private ChargeableDaysService chargeableDaysService;
	
	@Mock
	private RentalAgreementFactory rentalAgreementFactory;
	
	@Mock
	private RentalChargeService rentalChargeService;
	
	@Mock
	private RentalDateService rentalDateService;
	
	@BeforeEach
	public void beforeEach() {
		checkoutService = new CheckoutService(validationService, toolDao, chargeableDaysService, rentalAgreementFactory, rentalChargeService, rentalDateService);
	}
	
	@Test
	public void testCheckout() {
		String toolCode = "JAKD";
		LocalDate checkoutDate = LocalDate.parse("2020-06-16");
		int rentalDays = 5;
		int discount = 25;
		
		Tool tool = new Tool();
		when(toolDao.findToolByToolCode(toolCode)).thenReturn(tool);
		
		LocalDate dueDate = LocalDate.parse("2020-06-21");
		when(rentalDateService.calculateDueDate(checkoutDate, rentalDays)).thenReturn(dueDate);
		
		LocalDate startChargingDate = LocalDate.parse("2020-06-17");
		when(rentalDateService.calculateStartChargingDate(checkoutDate)).thenReturn(startChargingDate);
		
		Stream<LocalDate> rentalDates = Stream.of();
		when(rentalDateService.getDatesUntil(startChargingDate, dueDate)).thenReturn(rentalDates);
		
		long chargeableDays = 3;
		when(chargeableDaysService.calculateChargeableDays(rentalDates, tool.isWeekdayCharge(), tool.isWeekendCharge(), tool.isHolidayCharge())).thenReturn(chargeableDays);
		
		BigDecimal dailyCharge = new BigDecimal(1);
		when(rentalChargeService.getDailyCharge(tool.getDailyCharge())).thenReturn(dailyCharge);
		
		BigDecimal preDiscountCharge = new BigDecimal(1);
		when(rentalChargeService.getPreDiscountCharge(chargeableDays, dailyCharge)).thenReturn(preDiscountCharge);
		
		BigDecimal discountPercentage = new BigDecimal(1);
		when(rentalChargeService.getDiscountPercentage(discount)).thenReturn(discountPercentage);
		
		BigDecimal discountAmount = new BigDecimal(1);
		when(rentalChargeService.calculateDiscountAmount(preDiscountCharge, discountPercentage)).thenReturn(discountAmount);
		
		BigDecimal finalCharge = new BigDecimal(1);
		when(rentalChargeService.calculateFinalAmount(preDiscountCharge, discountAmount)).thenReturn(finalCharge);
		
		RentalAgreement expected = new RentalAgreement();
		when(rentalAgreementFactory.create(toolCode, tool.getToolType(), tool.getBrandName(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeableDays, preDiscountCharge, discount, discountAmount, finalCharge)).thenReturn(expected);
		
		RentalAgreement result = checkoutService.checkout(toolCode, checkoutDate, rentalDays, discount);
		assertEquals(expected, result);
	}
	
}
