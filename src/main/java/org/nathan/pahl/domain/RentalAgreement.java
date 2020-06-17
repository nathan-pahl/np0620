package org.nathan.pahl.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that contains all the finalized information regarding renting out a tool.
 */
public class RentalAgreement {
	
	private String toolCode;
	private String toolType;
	private String toolBrand;
	private int rentalDays;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BigDecimal dailyRentalCharge;
	private long chargeDays;
	private BigDecimal preDiscountCharge;
	private int discountPercent;
	private BigDecimal discountAmount;
	private BigDecimal finalCharge;
	
	private final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
	private final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yy");
    private final NumberFormat DEFAULT_CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();
	
    private String lineSeparator = DEFAULT_LINE_SEPARATOR;
	private DateTimeFormatter dateFormat = DEFAULT_DATE_FORMAT;
    private NumberFormat currenyFormat = DEFAULT_CURRENCY_FORMAT;
    
    public RentalAgreement() {}

	public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays, LocalDate checkoutDate,
			LocalDate dueDate, BigDecimal dailyRentalCharge, long chargeDays, BigDecimal preDiscountCharge,
			int discountPercent, BigDecimal discountAmount, BigDecimal finalCharge) {
		this.toolCode = toolCode;
		this.toolType = toolType;
		this.toolBrand = toolBrand;
		this.rentalDays = rentalDays;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.dailyRentalCharge = dailyRentalCharge;
		this.chargeDays = chargeDays;
		this.preDiscountCharge = preDiscountCharge;
		this.discountPercent = discountPercent;
		this.discountAmount = discountAmount;
		this.finalCharge = finalCharge;
	}
	
	/**
	 * Print out a nice looking receipt with all the pertinent information.
	 */
	public void print() {
		System.out.println("********Begin Receipt**********");
		System.out.println(toString());
		System.out.println("*********End Receipt***********");
	}
	
	/**
	 * Return a String representation of this {@link RentalAgreement}.
	 * 
	 * @return a String representation of {@link RentalAgreement}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
        builder.append("Tool code: ").append(toolCode).append(lineSeparator)
            .append("Tool type: ").append(toolType).append(lineSeparator)
            .append("Tool brand: ").append(toolBrand).append(lineSeparator)
            .append("Rental days: ").append(rentalDays).append(lineSeparator)
            .append("Check out date: ").append(dateFormat.format(checkoutDate)).append(lineSeparator)
            .append("Due date: ").append(dateFormat.format(dueDate)).append(lineSeparator)
            .append("Daily rental charge: ").append(currenyFormat.format(dailyRentalCharge)).append(lineSeparator)
            .append("Charge days: ").append(chargeDays).append(lineSeparator)
            .append("Pre-discount charge: ").append(currenyFormat.format(preDiscountCharge)).append(lineSeparator)
            .append("Discount percent: ").append(discountPercent).append('%').append(lineSeparator)
            .append("Discount amount: ").append(currenyFormat.format(discountAmount)).append(lineSeparator)
            .append("Final charge: ").append(currenyFormat.format(finalCharge));
        return builder.toString();
	}

	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public String getToolType() {
		return toolType;
	}

	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

	public String getToolBrand() {
		return toolBrand;
	}

	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}

	public long getChargeDays() {
		return chargeDays;
	}

	public void setChargeDays(long chargeDays) {
		this.chargeDays = chargeDays;
	}

	public BigDecimal getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getFinalCharge() {
		return finalCharge;
	}

	public void setFinalCharge(BigDecimal finalCharge) {
		this.finalCharge = finalCharge;
	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

	public DateTimeFormatter getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateTimeFormatter dateFormat) {
		this.dateFormat = dateFormat;
	}

	public NumberFormat getCurrenyFormat() {
		return currenyFormat;
	}

	public void setCurrenyFormat(NumberFormat currenyFormat) {
		this.currenyFormat = currenyFormat;
	}
	
}
