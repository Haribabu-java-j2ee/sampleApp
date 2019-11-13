package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.constants.Constants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Number to be used in accounting for containing currency. Follows the delegate pattern -- wraps BigDecimal. Holds precision.
 * 
 * @author Sirius Computer Solutions -- Tom Kofford, Mike Prinster
 */
@SuppressWarnings("rawtypes")
public class AssessmentNumber implements Serializable, Comparable {
    private static final long serialVersionUID = -6760525019415162815L;

    /** Rounding mode to round towards positive infinity. */
    public static final int ROUND_CEILING = BigDecimal.ROUND_CEILING;

    /** Rounding mode to round towards zero. */
    public static final int ROUND_DOWN = BigDecimal.ROUND_DOWN;

    /** Rounding mode to round towards negative infinity. */
    public static final int ROUND_FLOOR = BigDecimal.ROUND_FLOOR;

    /** Rounding mode to round towards "nearest neighbor" unless both neighbors are equidistant, in which case round down. */
    public static final int ROUND_HALF_DOWN = BigDecimal.ROUND_HALF_DOWN;

    /**
     * Rounding mode to round towards the "nearest neighbor" unless both neighbors are equidistant, in which case, round towards the even neighbor.
     */
    public static final int ROUND_HALF_EVEN = BigDecimal.ROUND_HALF_EVEN;

    /** Rounding mode to round towards "nearest neighbor" unless both neighbors are equidistant, in which case round up. */
    public static final int ROUND_HALF_UP = BigDecimal.ROUND_HALF_UP;

    /** Rounding mode to assert that the requested operation has an exact result, hence no rounding is necessary. */
    public static final int ROUND_UNNECESSARY = BigDecimal.ROUND_UNNECESSARY;

    /** Rounding mode to round away from zero. */
    public static final int ROUND_UP = BigDecimal.ROUND_UP;

    /** Constant to represent zero. */
    public static final AssessmentNumber ZERO = new AssessmentNumber(0);

    /** Holds the internal BigDecimal */
    private BigDecimal bigDecimal;

    /**
     * Make the default constructor private
     * 
     * @param val
     */
    @SuppressWarnings("unused")
    private AssessmentNumber() {
    }

    /**
     * Constructor takes a BigDecimal.
     * 
     * @param val
     */
    public AssessmentNumber(BigDecimal val) {
        setBigDecimal(val);
    }

    /**
     * @see java.math.BigDecimal(String)
     */
    public AssessmentNumber(String val) {
        // Using val.replaceAll to strip any commas that might get passed in
        setBigDecimal(new BigDecimal(val.replaceAll(Constants.COMMA, Constants.BLANK_SPACE)));
    }

    /**
     * @see java.math.BigDecimal(double)
     */
    public AssessmentNumber(double val) {
        setBigDecimal(BigDecimal.valueOf(val));
    }

    /**
     * @see java.math.BigDecimal(BigInteger)
     */
    public AssessmentNumber(BigInteger val) {
        setBigDecimal(new BigDecimal(val));
    }

    /**
     * @see java.math.BigDecimal#add(String)
     */
    public AssessmentNumber add(AssessmentNumber val) {
        return new AssessmentNumber(getBigDecimal().add(val.getBigDecimal()));
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
    public AssessmentNumber negate() {
        return new AssessmentNumber(getBigDecimal().negate());
    }

    /**
     * @see java.math.BigDecimal#subtract()
     */
    public AssessmentNumber subtract(AssessmentNumber val) {
        return new AssessmentNumber(getBigDecimal().subtract(val.getBigDecimal()));
    }

    /**
     * Used to set the member variable bigDecimal. Sets scale and rounding everytime. Should always be used to set bigDecimal.
     * 
     * @param bigDecimal
     *            The new bigDecimal to use.
     */
    private void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Returns the bigDecimal object represented by this.
     * 
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
     * Returns true if the value of this is zero, false if not.
     * 
     * @return ture if the value of this is zero, false if not.
     */
    public boolean isZero() {
        return bigDecimal.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * Returns true if this is less than <code>value</code>, otherwise, false
     * 
     * @param value
     *            the value by which this will be compared.
     * @return true if this is less than <code>value</code>, otherwise, false
     */
    public boolean isLessThan(AssessmentNumber value) {
        return bigDecimal.compareTo(value.getBigDecimal()) < 0;
    }

    /**
     * Returns true if this is less than or equal to <code>value</code>, otherwise, false
     * 
     * @param value
     *            the value by which this will be compared.
     * @return true if this is less than or equal to <code>value</code>, otherwise, false
     */
    public boolean isLessThanOrEqualTo(AssessmentNumber value) {
        return bigDecimal.compareTo(value.getBigDecimal()) <= 0;
    }

    /**
     * Returns true if this is greater than <code>value</code>, otherwise, false
     * 
     * @param value
     *            the value by which this will be compared.
     * @return true if this is greater than <code>value</code>, otherwise, false
     */
    public boolean isGreaterThan(AssessmentNumber value) {
        return bigDecimal.compareTo(value.getBigDecimal()) > 0;
    }

    /**
     * Returns true if this is greater than or equal to <code>value</code>, otherwise, false
     * 
     * @param value
     *            the value by which this will be compared.
     * @return true if this is greater than or equal to <code>value</code>, otherwise, false
     */
    public boolean isGreaterThanOrEqualTo(AssessmentNumber value) {
        return bigDecimal.compareTo(value.getBigDecimal()) >= 0;
    }

    /**
     * @see java.math.BigDecimal#intValue()
     * @return The int value of this.
     */
    public int intValue() {
        return bigDecimal.intValue();
    }

    /**
     * Returns an AssessmentNumber whose value is (this * val), and whose scale is (this.scale() + val.scale()).
     * 
     * @see java.math.BigDecimal#multiply(java.math.BigDecimal)
     * @param val
     *            The value to be multiplied by this AssessmentNumber.
     * @return An AssessmentNumber whose value is (this * val).
     */
    public AssessmentNumber multiply(AssessmentNumber val) {
        return new AssessmentNumber(bigDecimal.multiply(val.getBigDecimal()));
    }

    /**
     * Returns the maximum of this BigDecimal and val.
     * 
     * @param val
     *            The value with which the maximum is to be computed.
     * @return The maximum of this BigDecimal and val.
     */
    public AssessmentNumber max(AssessmentNumber val) {
        return new AssessmentNumber(bigDecimal.max(val.getBigDecimal()));
    }

    /**
     * Returns the minimum of this BigDecimal and val.
     * 
     * @param val
     *            The value with which the minimum is to be computed.
     * @return The minimum of this BigDecimal and val.
     */
    public AssessmentNumber min(AssessmentNumber val) {
        return new AssessmentNumber(bigDecimal.min(val.getBigDecimal()));
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AssessmentNumber)) {
            return false;
        }
        AssessmentNumber an = (AssessmentNumber) obj;
        return bigDecimal.equals(an.getBigDecimal());
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return bigDecimal.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.valueOf(bigDecimal.doubleValue());
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Object o) {
        AssessmentNumber an = (AssessmentNumber) o;
        return bigDecimal.compareTo(an.getBigDecimal());
    }

    /**
     * Returns the absolute values of this AssessmentNumber.
     * 
     * @see java.math.BigDecimal#abs()
     */
    public AssessmentNumber abs() {
        return new AssessmentNumber(bigDecimal.abs());
    }
}
