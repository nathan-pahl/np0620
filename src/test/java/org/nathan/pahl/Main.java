package org.nathan.pahl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nathan.pahl.config.AppConfig;
import org.nathan.pahl.domain.RentalAgreement;
import org.nathan.pahl.exception.InvalidDiscountException;
import org.nathan.pahl.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
public class Main {
	
	@Autowired
	private CheckoutService checkoutService;
	
	@Test
	public void test1() {
		System.out.println("### Test 1 ###");
		assertThrows(InvalidDiscountException.class, () -> checkoutService.checkout("JAKR", LocalDate.of(2015, 9, 3), 5, 101));
	}
	
	@Test
	public void test2() {
		System.out.println("### Test 2 ###");
		RentalAgreement rentalAgreement = checkoutService.checkout("LADW", LocalDate.of(2020, 7, 2), 3, 10);
		rentalAgreement.print();
		
		assertEquals("LADW", rentalAgreement.getToolCode());
		assertEquals("Ladder", rentalAgreement.getToolType());
		assertEquals("Werner", rentalAgreement.getToolBrand());
		assertEquals(3, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.parse("2020-07-02"), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.parse("2020-07-05"), rentalAgreement.getDueDate());
		assertEquals(new BigDecimal("1.99"), rentalAgreement.getDailyRentalCharge());
		assertEquals(1, rentalAgreement.getChargeDays());
		assertEquals(new BigDecimal("1.99"), rentalAgreement.getPreDiscountCharge());
		assertEquals(10, rentalAgreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.20"), rentalAgreement.getDiscountAmount());
		assertEquals(new BigDecimal("1.79"), rentalAgreement.getFinalCharge());
	}
	
	@Test
	public void test3() {
		System.out.println("### Test 3 ###");
		RentalAgreement rentalAgreement = checkoutService.checkout("CHNS", LocalDate.of(2015, 7, 2), 5, 25);
		rentalAgreement.print();
		
		assertEquals("CHNS", rentalAgreement.getToolCode());
		assertEquals("Chainsaw", rentalAgreement.getToolType());
		assertEquals("Stihl", rentalAgreement.getToolBrand());
		assertEquals(5, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.parse("2015-07-02"), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.parse("2015-07-07"), rentalAgreement.getDueDate());
		assertEquals(new BigDecimal("1.49"), rentalAgreement.getDailyRentalCharge());
		assertEquals(2, rentalAgreement.getChargeDays());
		assertEquals(new BigDecimal("2.98"), rentalAgreement.getPreDiscountCharge());
		assertEquals(25, rentalAgreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.75"), rentalAgreement.getDiscountAmount());
		assertEquals(new BigDecimal("2.23"), rentalAgreement.getFinalCharge());
	}
	
	@Test
	public void test4() {
		System.out.println("### Test 4 ###");
		RentalAgreement rentalAgreement = checkoutService.checkout("JAKD", LocalDate.of(2015, 9, 3), 6, 0);
		rentalAgreement.print();
		
		assertEquals("JAKD", rentalAgreement.getToolCode());
		assertEquals("Jackhammer", rentalAgreement.getToolType());
		assertEquals("DeWalt", rentalAgreement.getToolBrand());
		assertEquals(6, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.parse("2015-09-03"), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.parse("2015-09-09"), rentalAgreement.getDueDate());
		assertEquals(new BigDecimal("2.99"), rentalAgreement.getDailyRentalCharge());
		assertEquals(2, rentalAgreement.getChargeDays());
		assertEquals(new BigDecimal("5.98"), rentalAgreement.getPreDiscountCharge());
		assertEquals(0, rentalAgreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.00"), rentalAgreement.getDiscountAmount());
		assertEquals(new BigDecimal("5.98"), rentalAgreement.getFinalCharge());
	}
	
	@Test
	public void test5() {
		System.out.println("### Test 5 ###");
		RentalAgreement rentalAgreement = checkoutService.checkout("JAKR", LocalDate.of(2015, 7, 2), 9, 0);
		rentalAgreement.print();
		
		assertEquals("JAKR", rentalAgreement.getToolCode());
		assertEquals("Jackhammer", rentalAgreement.getToolType());
		assertEquals("Ridgid", rentalAgreement.getToolBrand());
		assertEquals(9, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.parse("2015-07-02"), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.parse("2015-07-11"), rentalAgreement.getDueDate());
		assertEquals(new BigDecimal("2.99"), rentalAgreement.getDailyRentalCharge());
		assertEquals(5, rentalAgreement.getChargeDays());
		assertEquals(new BigDecimal("14.95"), rentalAgreement.getPreDiscountCharge());
		assertEquals(0, rentalAgreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.00"), rentalAgreement.getDiscountAmount());
		assertEquals(new BigDecimal("14.95"), rentalAgreement.getFinalCharge());
	}
	
	@Test
	public void test6() {
		System.out.println("### Test 6 ###");
		RentalAgreement rentalAgreement = checkoutService.checkout("JAKR", LocalDate.of(2020, 7, 2), 4, 50);
		rentalAgreement.print();
		
		assertEquals("JAKR", rentalAgreement.getToolCode());
		assertEquals("Jackhammer", rentalAgreement.getToolType());
		assertEquals("Ridgid", rentalAgreement.getToolBrand());
		assertEquals(4, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.parse("2020-07-02"), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.parse("2020-07-06"), rentalAgreement.getDueDate());
		assertEquals(new BigDecimal("2.99"), rentalAgreement.getDailyRentalCharge());
		assertEquals(0, rentalAgreement.getChargeDays());
		assertEquals(new BigDecimal("0.00"), rentalAgreement.getPreDiscountCharge());
		assertEquals(50, rentalAgreement.getDiscountPercent());
		assertEquals(new BigDecimal("0.00"), rentalAgreement.getDiscountAmount());
		assertEquals(new BigDecimal("0.00"), rentalAgreement.getFinalCharge());
	}

}
