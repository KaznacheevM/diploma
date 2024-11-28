/**
 * Provides tools for calculating and handling the numerical order of values.
 *
 * <p>The "order" typically refers to the magnitude or scale of a number, such as the
 * exponent in scientific notation. This package includes interfaces and utility classes
 * for computing the order of numerical values in various formats, such as {@link java.math.BigDecimal},
 * {@link java.math.BigInteger}, and {@code double}.
 *
 * <p>Key components:
 * <ul>
 *     <li>{@link mathTools.order.OrderComputer}:
 *     A functional interface for defining custom logic to compute the order of a value.</li>
 *     <li>{@link mathTools.order.OrderProvider}:
 *     Provides access to an {@link mathTools.order.OrderComputer} for computing orders in modular systems.</li>
 *     <li>{@link mathTools.order.OrderUtil}:
 *     A utility class with static methods for computing the order and overestimated order of numerical values.</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>{@code
 * // Using OrderUtil
 * int order = OrderUtil.orderOf(new BigDecimal("12345")); // Returns 4
 * int overestimatedOrder = OrderUtil.overestimateOrderOf(123.45); // Returns 3
 *
 * // Using OrderProvider
 * OrderProvider provider = () -> () -> {
 *     BigDecimal number = new BigDecimal("67890");
 *     return OrderUtil.orderOf(number);
 * };
 * int computedOrder = provider.getOrderComputer().compute(); // Returns 4
 * }</pre>
 *
 * <p>This package is useful for numerical computations where the order of a number is
 * critical, such as in scientific calculations, logarithmic operations, or precision handling.
 */
package mathTools.order;