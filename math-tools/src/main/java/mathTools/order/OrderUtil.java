package mathTools.order;

import mathTools.bigMath.BigMath;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;


/**
 * Provides utility methods for computing the numerical order of values.
 *
 * <p>The "order" refers to the magnitude or scale of a number, often represented as the
 * exponent in scientific notation. This class includes methods for computing the exact
 * order and for overestimating the order for {@link BigDecimal}, {@link BigInteger}, and
 * {@code double} values.
 *
 * <p>The class is not instantiable and is intended to be used statically.
 *
 * <p>Example usage:
 * <pre>{@code
 * int order = OrderUtil.orderOf(new BigDecimal("12345")); // Returns 4
 * int overestimatedOrder = OrderUtil.overestimateOrderOf(123.45); // Returns 3
 * }</pre>
 */
public class OrderUtil {

    /**
     * Private constructor to prevent instantiation.
     */
    private OrderUtil() {
    }

    /**
     * Computes the order of the given {@link BigDecimal}.
     *
     * <p>The order is calculated as:
     * {@code precision - scale - 1}.
     * For example, the order of {@code 123.45} is {@code 2} (scientific notation: {@code 1.2345 * 10^2}).
     *
     * @param number the {@link BigDecimal} value, must not be {@code null}.
     * @return the order of the number.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static int orderOf(BigDecimal number) {
        Objects.requireNonNull(number, "The number is null");

        if (number.compareTo(BigDecimal.ZERO) == 0) {
            return Integer.MIN_VALUE;
        }

        return number.precision() - number.scale() - 1;
    }

    /**
     * Computes the order of the given {@link BigInteger}.
     *
     * <p>The order is defined as {@code precision - 1}, where {@code precision} is the number of digits
     * in the absolute value of the number. For example, the order of {@code 12345} is {@code 4}.
     *
     * @param number the {@link BigInteger} value, must not be {@code null}.
     * @return the order of the number.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static int orderOf(BigInteger number) {
        Objects.requireNonNull(number, "The number is null");

        if (number.equals(BigInteger.ZERO)) {
            return Integer.MIN_VALUE;
        }

        return BigMath.precision(number) - 1;
    }

    /**
     * Computes the order of the given {@code double} value.
     *
     * <p>The method internally converts the {@code double} to a {@link BigDecimal} and then
     * computes the order.
     *
     * @param number the {@code double} value.
     * @return the order of the number.
     * @throws ArithmeticException if {@code number} is zero, as the order is undefined.
     */
    public static int orderOf(double number) {
        BigDecimal bigDecimalValue = BigDecimal.valueOf(number);
        return orderOf(bigDecimalValue);
    }

    /**
     * Overestimates the order of the given {@link BigDecimal}.
     *
     * <p>This method considers cases where the number is close to a power of 10 and returns
     * {@code order + 1} if necessary. For example, for {@code 1.0} or {@code 10.0}, the method
     * returns {@code 0} and {@code 1}, respectively.
     *
     * @param number the {@link BigDecimal} value, must not be {@code null}.
     * @return the overestimated order of the number.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static int overestimateOrderOf(BigDecimal number) {
        Objects.requireNonNull(number, "The number is null");

        int order = orderOf(number);
        BigDecimal significand = number.movePointLeft(order);

        if (significand.abs().compareTo(BigDecimal.ONE) == 0) {
            return order;
        }

        return order + 1;
    }

    /**
     * Overestimates the order of the given {@link BigInteger}.
     *
     * <p>This method returns {@code order + 1} for numbers close to a power of 10. For example,
     * for {@code 10} or {@code 100}, it returns {@code 2} and {@code 3}, respectively.
     *
     * @param number the {@link BigInteger} value, must not be {@code null}.
     * @return the overestimated order of the number.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    public static int overestimateOrderOf(BigInteger number) {
        Objects.requireNonNull(number, "The number is null");

        String stringAbsValue = number.abs().toString();
        int order = orderOf(number);

        if (stringAbsValue.matches("-?10*")) {
            return order;
        }

        return order + 1;
    }

    /**
     * Overestimates the order of the given {@code double} value.
     *
     * <p>The method internally converts the {@code double} to a {@link BigDecimal} and then
     * computes the overestimated order.
     *
     * @param number the {@code double} value.
     * @return the overestimated order of the number.
     */
    public static int overestimateOrderOf(double number) {
        BigDecimal bigDecimalValue = BigDecimal.valueOf(number);
        return overestimateOrderOf(bigDecimalValue);
    }
}
