/**
 * Provides high-precision implementations of logarithmic approximations for common and natural logarithms.
 * <p>
 * This package contains classes that extend the functionality of {@link approximation.Approximator}
 * to support logarithmic computations with arbitrary precision using {@link java.math.BigDecimal}.
 * It includes support for base-10 logarithms, natural logarithms, and logarithms of arbitrary bases.
 * <p>
 * Key components:
 * <ul>
 *   <li>{@link approximation.commons.big.NaturalLogarithm} - Computes the natural logarithm (\(\ln(x)\)) with support for scaling and normalization.</li>
 *   <li>{@link approximation.commons.big.Logarithm} - Computes logarithms for arbitrary bases (\(\log_b(x)\)).</li>
 *   <li>{@link approximation.commons.big.CommonLogarithm} - Computes the common logarithm (\(\log_{10}(x)\)) with optimized base-10 calculations.</li>
 * </ul>
 * Example usage:
 * <pre>
 *     ApproximatorFactory<BigDecimal> factory = ...;
 *     ArgValidatorFactory<BigDecimal> validatorFactory = ...;
 *
 *     // Compute the natural logarithm
 *     NaturalLogarithm ln = new NaturalLogarithm(argIntervalFactory, validatorFactory, factory, new BigDecimal("2.718"));
 *     BigDecimal lnResult = ln.approximate(10, RoundingMode.HALF_UP);
 *
 *     // Compute the common logarithm
 *     CommonLogarithm log10 = new CommonLogarithm(factory, validatorFactory, new BigDecimal("100"));
 *     BigDecimal log10Result = log10.approximate(10, RoundingMode.HALF_UP);
 *
 *     // Compute a logarithm with an arbitrary base
 *     Logarithm logBase3 = new Logarithm(factory, validatorFactory, new BigDecimal("3"), new BigDecimal("81"));
 *     BigDecimal logBase3Result = logBase3.approximate(10, RoundingMode.HALF_UP);
 * </pre>
 */
package approximation.commons.big;