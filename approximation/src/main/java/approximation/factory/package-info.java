/**
 * Provides factories for creating high-precision {@link approximation.Approximator} instances.
 * <p>
 * This package contains implementations of {@link approximation.factory.ApproximatorFactory} for generating
 * approximators of various mathematical functions, including logarithms, exponential functions, and the Euler number.
 * <p>
 * Key components:
 * <ul>
 *   <li>{@link approximation.factory.ApproximatorFactory} - Defines the contract for factories creating mathematical approximators.</li>
 *   <li>{@link approximation.factory.BigApproximatorFactory} - A high-precision implementation for generating approximators using {@link java.math.BigDecimal}.</li>
 * </ul>
 * Example usage:
 * <pre>
 *     ApproximatorFactory<BigDecimal> factory = ApproximatorFactory.getBigInstance();
 *
 *     // Create an approximator for the natural logarithm
 *     Approximator<BigDecimal> lnApproximator = factory.naturalLogarithm(false, new BigDecimal("2.718"));
 *     BigDecimal lnResult = lnApproximator.approximate(10, RoundingMode.HALF_UP);
 *
 *     // Create an approximator for the common logarithm
 *     Approximator<BigDecimal> log10Approximator = factory.commonLogarithm(new BigDecimal("100"));
 *     BigDecimal log10Result = log10Approximator.approximate(10, RoundingMode.HALF_UP);
 *
 *     // Create an approximator for the Euler number
 *     Approximator<BigDecimal> eulerApproximator = factory.eulerNumber();
 *     BigDecimal eulerResult = eulerApproximator.approximate(10, RoundingMode.HALF_UP);
 * </pre>
 */
package approximation.factory;