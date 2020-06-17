package org.nathan.pahl.factory;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

/**
 * Factory used for simplifying the creation and scaling of a {@link BigDecimal}.
 */
@Component
public class BigDecimalFactory {
	
	private final int SCALE = 2;
	private final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
	
	/**
	 * Set the scale of the given {@link BigDecimal}.
	 * 
	 * @param value the {@link BigDecimal} to update
	 * @return a {@link BigDecimal} with an updated scale
	 */
	public BigDecimal create(BigDecimal value) {
		return value.setScale(SCALE, ROUNDING_MODE);
	}

	/**
	 * Create a {@link BigDecimal} using the provided String.
	 * 
	 * @param value the String used to construct a {@link BigDecimal}
	 * @return a {@link BigDecimal}
	 */
	public BigDecimal create(String value) {
		return create(new BigDecimal(value));
	}
	
	/**
	 * Create a {@link BigDecimal} using the provided int.
	 * 
	 * @param value the int used to construct a {@link BigDecimal}
	 * @return a {@link BigDecimal}
	 */
	public BigDecimal create(int value) {
		return create(Integer.toString(value));
	}
	
	/**
	 * Create a {@link BigDecimal} using the provided long.
	 * 
	 * @param value the long used to construct a {@link BigDecimal}
	 * @return a {@link BigDecimal}
	 */
	public BigDecimal create(long value) {
		return create(Long.toString(value));
	}
	
	/**
	 * Create a {@link BigDecimal} using the provided double.
	 * 
	 * @param value the double used to construct a {@link BigDecimal}
	 * @return a {@link BigDecimal}
	 */
	public BigDecimal create(double value) {
		return create(Double.toString(value));
	}
	
}
