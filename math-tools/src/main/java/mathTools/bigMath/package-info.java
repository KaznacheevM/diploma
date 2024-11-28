/**
 * Provides tools and utilities for performing mathematical operations with
 * {@link java.math.BigDecimal} and {@link java.math.BigInteger}.
 *
 * <p>This package includes classes for general-purpose mathematical computations,
 * as well as specialized transformations involving large numerical values.
 *
 * <p>Key components:
 * <ul>
 *     <li>{@link mathTools.bigMath.BigMath}:
 *     Contains utility methods for working with {@link java.math.BigDecimal} and {@link java.math.BigInteger},
 *     such as precision calculations, factorial computation, and power functions.</li>
 *     <li>{@link mathTools.bigMath.BigTransformations}:
 *     Provides methods for performing specific mathematical transformations, such as
 *     computing symmetric ratios.</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>{@code
 * // Using BigMath
 * boolean isZero = BigMath.isZero(new BigDecimal("0"));
 * BigInteger factorial = BigMath.factorial(5);
 *
 * // Using BigTransformations
 * BigDecimal ratio = BigTransformations.symmetricRatio(new BigDecimal("2"), 10, RoundingMode.HALF_UP);
 * }</pre>
 *
 * <p>The package is designed for applications requiring precise and efficient computations
 * with arbitrary-precision numbers, such as scientific modeling or financial analysis.
 */
package mathTools.bigMath;