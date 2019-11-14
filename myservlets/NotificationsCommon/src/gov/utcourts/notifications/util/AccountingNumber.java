package gov.utcourts.notifications.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Number to be used in accounting for containing currency.  Follows the delegate pattern -- wraps BigDecimal.
 * Holds precision.
 */
public class AccountingNumber implements Serializable, Comparable {
	public static final long serialVersionUID = 545454125;
	
	/**	Rounding mode to round towards positive infinity. */
	public static final int ROUND_CEILING		= BigDecimal.ROUND_CEILING;
	
	/**	Rounding mode to round towards zero. */
	public static final int ROUND_DOWN			= BigDecimal.ROUND_DOWN;
	
	/**	Rounding mode to round towards negative infinity. */
	public static final int ROUND_FLOOR			= BigDecimal.ROUND_FLOOR;
	
	/**	Rounding mode to round towards "nearest neighbor" unless both neighbors are equidistant, in which case round down. */
	public static final int ROUND_HALF_DOWN		= BigDecimal.ROUND_HALF_DOWN;
	
	/**	Rounding mode to round towards the "nearest neighbor" unless both neighbors are equidistant, in which case, 
	 * round towards the even neighbor. */
	public static final int ROUND_HALF_EVEN		= BigDecimal.ROUND_HALF_EVEN;
	
	/**	Rounding mode to round towards "nearest neighbor" unless both neighbors are equidistant, in which case round up. */
	public static final int ROUND_HALF_UP		= BigDecimal.ROUND_HALF_UP;
	
	/**	Rounding mode to assert that the requested operation has an exact result, hence no rounding is necessary. */
	public static final int ROUND_UNNECESSARY	= BigDecimal.ROUND_UNNECESSARY;
	
	/**	Rounding mode to round away from zero. */
	public static final int ROUND_UP			= BigDecimal.ROUND_UP;
	
	/** Constant to represent zero. */
	public static final AccountingNumber ZERO = new AccountingNumber(0);
	
	/** Holds the internal BigDecimal */
	private BigDecimal bigDecimal;
	
	/**
	 * Make the default constructor private
	 * @param val
	 */
	private AccountingNumber() {
	}
	
	/**
	 * Constructor takes a BigDecimal.
	 * @param val
	 */
	public AccountingNumber(BigDecimal val) {
		setBigDecimal(val);
	}
	
	/**
	 * @see java.math.BigDecimal(String)
	 */
	public AccountingNumber(String val) {
		// Using val.replaceAll to strip any commas that might get passed in
		setBigDecimal(new BigDecimal(val.replaceAll(",", "")));
	}

	/**
	 * @see java.math.BigDecimal(double)
	 */
	public AccountingNumber(double val) {
		setBigDecimal(new BigDecimal(val) );
	}

	/**
	 * @see java.math.BigDecimal(BigInteger)
	 */
	public AccountingNumber(BigInteger val) {
		setBigDecimal(new BigDecimal(val));
	}

	/**
	 * @see java.math.BigDecimal#add(String)
	 */
	public AccountingNumber add(AccountingNumber val) {
		return new AccountingNumber(getBigDecimal().add(val.getBigDecimal()));
	}

	/**
	 * @see java.math.BigDecimal#doubleValue()
	 */
	public double doubleValue() {
		return getBigDecimal().doubleValue();
	}

	/**
	 * @see java.math.BigDecimal#negate()
	 */
	public AccountingNumber negate() {
		return new AccountingNumber(getBigDecimal().negate());
	}

	/**
	 * @see java.math.BigDecimal#subtract()
	 */
	public AccountingNumber subtract(AccountingNumber val) {
		return new AccountingNumber(getBigDecimal().subtract( val.getBigDecimal() ));
	}
	
	/**
	 * Used to set the member variable bigDecimal.  Sets scale and rounding everytime.  Should always be used
	 * to set bigDecimal.
	 * @param bigDecimal The new bigDecimal to use.
	 */
	private void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Returns the bigDecimal object represented by this.
	 * @return the bigDecimal object represented by this.
	 */
	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}

	/**
	 * @see java.math.BigDecimal#signum()
	 */
	public int signum() {
		return bigDecimal.signum();
	}

	/**
	 * Returns an AccountingNumber whose value is (this / val), and whose scale is this.scale(). 
	 * @param val The value by which this AccountingNumber is to be divided.
	 * @param roundingMode The rounding mode to apply.
	 * @return An AccountingNumber whose value is (this / val).
	 * @see    #ROUND_UP
	 * @see    #ROUND_DOWN
	 * @see    #ROUND_CEILING
	 * @see    #ROUND_FLOOR
	 * @see    #ROUND_HALF_UP
	 * @see    #ROUND_HALF_DOWN
	 * @see    #ROUND_HALF_EVEN
	 * @see    #ROUND_UNNECESSARY
	 * @see java.math.BigDecimal#divide(java.math.BigDecimal, int)
	 */
	public AccountingNumber divide(AccountingNumber val, int roundingMode) {
		return(new AccountingNumber(bigDecimal.divide(val.getBigDecimal(), roundingMode)));
	}

	/**
	 * Returns an AccountingNumber whose value is (this / val), and whose scale is as specified.
	 * @param val The value by which this AccountingNumber is to be divided.
	 * @param scale The scale of the AccountingNumber quotient to be returned.
	 * @param roundingMode The rounding mode to apply.
	 * @return An AccountingNumber whose value is (this / val).
	 * @see    #ROUND_UP
	 * @see    #ROUND_DOWN
	 * @see    #ROUND_CEILING
	 * @see    #ROUND_FLOOR
	 * @see    #ROUND_HALF_UP
	 * @see    #ROUND_HALF_DOWN
	 * @see    #ROUND_HALF_EVEN
	 * @see    #ROUND_UNNECESSARY
	 * @see java.math.BigDecimal#divide(java.math.BigDecimal, int, int)
	 */
	public AccountingNumber divide(AccountingNumber val, int scale, int roundingMode) {
		return(new AccountingNumber(bigDecimal.divide(val.getBigDecimal(), scale, roundingMode)));
	}
	
	/**
	 * Returns true if the value of this is zero, false if not.
	 * @return ture if the value of this is zero, false if not.
	 */
	public boolean isZero() {
		return bigDecimal.doubleValue() == 0;
	}
	/**
	 * Returns true if this is less than <code>value</code>, otherwise, false
	 * @param value the value by which this will be compared.
	 * @return true if this is less than <code>value</code>, otherwise, false
	 */
	public boolean isLessThan(AccountingNumber value) {
		return bigDecimal.compareTo(value.getBigDecimal()) < 0;
	}
	/**
	 * Returns true if this is less than or equal to <code>value</code>, otherwise, false
	 * @param value the value by which this will be compared.
	 * @return true if this is less than or equal to <code>value</code>, otherwise, false
	 */
	public boolean isLessThanOrEqualTo(AccountingNumber value) {
		return bigDecimal.compareTo(value.getBigDecimal()) <= 0;
	}
	/**
	 * Returns true if this is greater than <code>value</code>, otherwise, false
	 * @param value the value by which this will be compared.
	 * @return true if this is greater than <code>value</code>, otherwise, false
	 */
	public boolean isGreaterThan(AccountingNumber value) {
		return bigDecimal.compareTo(value.getBigDecimal()) > 0;
	}
	/**
	 * Returns true if this is greater than or equal to <code>value</code>, otherwise, false
	 * @param value the value by which this will be compared.
	 * @return true if this is greater than or equal to <code>value</code>, otherwise, false
	 */
	public boolean isGreaterThanOrEqualTo(AccountingNumber value) {
		return bigDecimal.compareTo(value.getBigDecimal()) >= 0;
	}
	/**
	 * @see java.math.BigDecimal#intValue()
	 * @return The int value of this.
	 */
	public int intValue() {
		return(bigDecimal.intValue());
	}

	/**
	 * Returns an AccountingNumber whose value is (this * val), and whose scale is (this.scale() + val.scale()).
	 * @see java.math.BigDecimal#multiply(java.math.BigDecimal)
	 * @param val The value to be multiplied by this AccountingNumber.
	 * @return An AccountingNumber whose value is (this * val).
	 */
	public AccountingNumber multiply(AccountingNumber val) {
		return(new AccountingNumber(bigDecimal.multiply(val.getBigDecimal())));
	}

	/**
	 * Returns the maximum of this BigDecimal and val.
	 * @param val The value with which the maximum is to be computed.
	 * @return The maximum of this BigDecimal and val.
	 */
	public AccountingNumber max(AccountingNumber val) {
		return(new AccountingNumber(bigDecimal.max(val.getBigDecimal())));
	}

	/**
	 * Returns the minimum of this BigDecimal and val.
	 * @param val The value with which the minimum is to be computed.
	 * @return The minimum of this BigDecimal and val.
	 */
	public AccountingNumber min(AccountingNumber val) {
		return(new AccountingNumber(bigDecimal.min(val.getBigDecimal())));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (! (obj instanceof AccountingNumber)) {
			return(false);
		}
		AccountingNumber an = (AccountingNumber)obj;
		return(bigDecimal.equals(an.getBigDecimal()));
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return(bigDecimal.hashCode());
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return(bigDecimal.toString());
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		AccountingNumber an = (AccountingNumber)o;
		return(bigDecimal.compareTo(an.getBigDecimal()));
	}

	/**
	 * Returns the absolute values of this AccountingNumber.
	 * @see java.math.BigDecimal#abs()
	 */
	public AccountingNumber abs() {
		return(new AccountingNumber(bigDecimal.abs()));
	}

}
