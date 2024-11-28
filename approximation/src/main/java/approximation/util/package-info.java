/**
 * Provides utility interfaces and classes for high-precision mathematical approximations.
 * <p>
 * This package defines abstractions and implementations for approximating mathematical constants
 * and functions, such as the Euler number, exponential functions, and logarithms, using configurable precision.
 * The utilities are designed to integrate seamlessly with the {@link approximation.ApproximatorFactory} framework.
 * <p>
 * Key components:
 * <ul>
 *   <li>{@link approximation.util.ApproximationUtil} - Defines utility methods for high-precision approximations.</li>
 *   <li>{@link approximation.util.BigApproximationUtil} - A concrete implementation of {@code ApproximationUtil} for {@link java.math.BigDecimal}.</li>
 * </ul>
 * Example usage:
 * <pre>
 *     ApproximationUtil<BigDecimal> util = ApproximationUtil.getBigInstance();
 *     MathContext mathContext = new MathContext(10, RoundingMode.HALF_UP);
 *
 *     // Approximate the Euler number
 *     BigDecimal e = util.eulerNumber(mathContext);
 *
 *     // Approximate the exponential function
 *     BigDecimal exp = util.exponential(new BigDecimal("2"), mathContext);
 *
 *     // Approximate the natural logarithm
 *     BigDecimal ln = util.naturalLogarithm(new BigDecimal("10"), mathContext);
 * </pre>
 */
package approximation.util;