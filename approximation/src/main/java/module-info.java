/**
 * The `approximation` module provides high-precision mathematical approximations for various functions and constants.
 * <p>
 * This module contains interfaces and implementations for approximating mathematical constants such as the Euler number
 * (\(e\)) and functions such as logarithms (natural, common, and arbitrary bases) and exponential functions.
 * It is designed to handle computations with arbitrary precision using {@link java.math.BigDecimal}.
 * <p>
 * Key features include:
 * <ul>
 *   <li>Factories for creating approximators for specific functions ({@link approximation.factory.ApproximatorFactory}).</li>
 *   <li>Utility classes for simplifying approximations ({@link approximation.util.ApproximationUtil}).</li>
 *   <li>Support for series-based approximations and optimization techniques.</li>
 * </ul>
 * Example usage:
 * <pre>
 *     // Create a factory for BigDecimal approximations
 *     ApproximationUtil<BigDecimal> util = ApproximationUtil.getBigInstance();
 *
 *     MathContext mathContext = new MathContext(10, RoundingMode.HALF_UP);
 *
 *     // Approximate the natural logarithm of a number
 *     BigDecimal ln = util.naturalLogarithm(new BigDecimal("2.718"), mathContext);
 *
 *     // Approximate the Euler number
 *     BigDecimal eulerNumber = util.eulerNumber(mathContext);
 *
 *     // Approximate the exponential function
 *     BigDecimal exp = util.exponential(new BigDecimal("1.5"), mathContext);
 * </pre>
 */
module approximation {
    requires math.tools;
    requires toolbox;
}