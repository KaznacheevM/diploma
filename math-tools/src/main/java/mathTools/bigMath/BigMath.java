package mathTools.bigMath;

import mathTools.order.OrderUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Provides utility methods for performing mathematical operations with {@link BigDecimal}
 * and {@link BigInteger}.
 *
 * <p>This class includes methods for number classification (e.g., checking if a number is zero or one),
 * precision and significand extraction, factorial computation, power calculations, and more.
 * These methods are designed to handle large numbers safely and efficiently.
 *
 * <p>The class is not instantiable and is intended to be used statically.
 */
public final class BigMath {

    /**
     * Private constructor to prevent instantiation.
     */
    private BigMath() {
    }

    /**
     * Checks if the given {@link BigDecimal} is equal to zero.
     *
     * @param number the number to check, must not be {@code null}.
     * @return {@code true} if the number is zero, {@code false} otherwise.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static boolean isZero(BigDecimal number) {
        Objects.requireNonNull(number, "Number cannot be null");
        return number.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * Checks if the given {@link BigInteger} is equal to zero.
     *
     * @param number the number to check, must not be {@code null}.
     * @return {@code true} if the number is zero, {@code false} otherwise.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static boolean isZero(BigInteger number) {
        Objects.requireNonNull(number, "Number cannot be null");
        return number.equals(BigInteger.ZERO);
    }

    /**
     * Checks if the given {@link BigDecimal} is equal to one.
     *
     * @param number the number to check, must not be {@code null}.
     * @return {@code true} if the number is one, {@code false} otherwise.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static boolean isOne(BigDecimal number) {
        Objects.requireNonNull(number, "Number cannot be null");
        return number.compareTo(BigDecimal.ONE) == 0;
    }

    /**
     * Checks if the given {@link BigInteger} is equal to one.
     *
     * @param number the number to check, must not be {@code null}.
     * @return {@code true} if the number is one, {@code false} otherwise.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static boolean isOne(BigInteger number) {
        Objects.requireNonNull(number, "Number cannot be null");
        return number.equals(BigInteger.ONE);
    }

    /**
     * Checks if the given {@link BigDecimal} is equal to negative one.
     *
     * @param number the number to check, must not be {@code null}.
     * @return {@code true} if the number is negative one, {@code false} otherwise.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static boolean isNegativeOne(BigDecimal number) {
        Objects.requireNonNull(number, "Number cannot be null");
        BigDecimal negativeOne = BigDecimal.ONE.negate();
        return number.compareTo(negativeOne) == 0;
    }

    /**
     * Checks if the given {@link BigInteger} is equal to negative one.
     *
     * @param number the number to check, must not be {@code null}.
     * @return {@code true} if the number is negative one, {@code false} otherwise.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static boolean isNegativeOne(BigInteger number) {
        Objects.requireNonNull(number, "Number cannot be null");
        BigInteger negativeOne = BigInteger.ONE.negate();
        return number.equals(negativeOne);
    }

    /**
     * Computes the precision of the given {@link BigInteger}.
     *
     * <p>The precision is defined as the number of digits in the absolute value of the number.
     *
     * @param number the number whose precision is to be calculated, must not be {@code null}.
     * @return the precision (number of digits) of the number.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static int precision(BigInteger number) {
        Objects.requireNonNull(number, "Number cannot be null");
        String stringAbsValue = number.abs().toString();
        return stringAbsValue.length();
    }

    /**
     * Extracts the significand of the given {@link BigDecimal}.
     *
     * <p>The significand is computed by shifting the decimal point of the number
     * to the left by its order of magnitude.
     *
     * @param number the number whose significand is to be extracted, must not be {@code null}.
     * @return the significand of the number.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static BigDecimal significand(BigDecimal number) {
        Objects.requireNonNull(number, "Number cannot be null");

        if (isZero(number)) {
            return BigDecimal.ZERO;
        }

        int order = OrderUtil.orderOf(number);
        return number.movePointLeft(order);
    }

    /**
     * Extracts the significand of the given {@link BigInteger}.
     *
     * <p>The significand is computed by shifting the decimal point of the number
     * to the left by its order of magnitude.
     *
     * @param number the number whose significand is to be extracted, must not be {@code null}.
     * @return the significand of the number.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static BigDecimal significand(BigInteger number) {
        return significand(new BigDecimal(number));
    }

    /**
     * Checks whether the given {@link BigDecimal} can be exactly represented as an {@code int}.
     *
     * <p>The method first removes any trailing zeros from the number and checks if its scale
     * is greater than zero. If the scale is greater than zero, the number cannot be represented
     * as an {@code int} since it has a fractional part.
     *
     * <p>If the scale is zero, the method compares the number to the range of representable
     * {@code int} values, {@link Integer#MIN_VALUE} and {@link Integer#MAX_VALUE}.
     *
     * @param number the number to check, must not be {@code null}.
     * @return {@code true} if the number can be exactly represented as an {@code int},
     *         {@code false} otherwise.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static boolean isIntRepresentable(BigDecimal number) {
        Objects.requireNonNull(number, "Number cannot be null");

        if (number.stripTrailingZeros().scale() > 0) {
            return false;
        }

        BigDecimal minValue = new BigDecimal(Integer.MIN_VALUE);
        BigDecimal maxValue = new BigDecimal(Integer.MAX_VALUE);

        return number.compareTo(minValue) >= 0 & number.compareTo(maxValue) <= 0;
    }

    /**
     * Computes the factorial of the given {@link BigInteger}.
     *
     * @param number the number whose factorial is to be calculated, must be non-negative.
     * @return the factorial of the number.
     * @throws NullPointerException if {@code number} is {@code null}.
     * @throws ArithmeticException  if {@code number} is negative.
     */
    public static BigInteger factorial(BigInteger number) {
        Objects.requireNonNull(number, "Number cannot be null");

        if (number.compareTo(BigInteger.ZERO) < 0) {
            throw new ArithmeticException("The factorial can only be calculated for non-negative numbers.");
        }

        BigInteger factorial = BigInteger.ONE;

        BigInteger multiplicand = BigInteger.TWO;
        while (multiplicand.compareTo(number) <= 0) {
            factorial = factorial.multiply(multiplicand);
            multiplicand = multiplicand.add(BigInteger.ONE);
        }

        return factorial;
    }

    /**
     * Computes the factorial of the given long number.
     *
     * @param number the number whose factorial is to be calculated, must be non-negative.
     * @return the factorial of the number.
     * @throws NullPointerException if {@code number} is {@code null}.
     * @throws ArithmeticException  if {@code number} is negative.
     */
    public static BigInteger factorial(long number) {
        BigInteger bigNumber = BigInteger.valueOf(number);
        return factorial(bigNumber);
    }

    /**
     * Computes the power of a {@link BigDecimal} base raised to a non-negative integer exponent.
     *
     * @param base     the base value, must not be {@code null}.
     * @param exponent the exponent, must be non-negative.
     * @return the result of {@code base} raised to the power of {@code exponent}.
     * @throws NullPointerException     if {@code base} is {@code null}.
     * @throws IllegalArgumentException if {@code exponent} is negative.
     */
    public static BigDecimal pow(BigDecimal base, int exponent) {
        Objects.requireNonNull(base, "Base cannot be null");

        if (exponent < 0) {
            throw new IllegalArgumentException("Accuracy of computations is undefined");
        }

        BigDecimal result = BigDecimal.ONE;
        while (exponent != 0) {
            if (exponent % 2 != 0) {
                result = result.multiply(base);
            }

            exponent >>= 1;

            base = base.multiply(base);
        }

        return result;
    }
}
