/**
 * Provides a comprehensive set of tools for advanced mathematical computations.
 *
 * <p>The {@code mathTools} package is the core library for performing precise
 * and scalable numerical operations. It offers utilities for handling large numbers,
 * calculating numerical orders, managing accuracy, and more. This package is designed
 * for use in scientific, financial, and engineering applications where numerical precision
 * and efficiency are critical.
 *
 * <p>Key subpackages:
 * <ul>
 *     <li>{@link xyz.derivora.mathTools.accuracy}:
 *     Contains classes and utilities for managing numerical accuracy, including
 *     transformations between scales, precision, and positional accuracy.</li>
 *     <li>{@link xyz.derivora.mathTools.bigMath}:
 *     Focuses on operations with {@link java.math.BigDecimal} and {@link java.math.BigInteger},
 *     including mathematical transformations, factorials, and precision calculations.</li>
 *     <li>{@link xyz.derivora.mathTools.indexMapping}:
 *     Provides tools for transforming indices, such as mapping indices to new values
 *     or shifting them to match specific patterns.</li>
 *     <li>{@link xyz.derivora.mathTools.signMapping}:
 *     Offers utilities for handling signs in computations, including alternating and fixed signs.</li>
 *     <li>{@link xyz.derivora.mathTools.order}:
 *     Facilitates the computation of numerical orders, such as magnitude and scale,
 *     for various numerical types.</li>
 *     <li>{@link xyz.derivora.mathTools.normalization}:
 *     Includes interfaces and implementations for normalizing numerical values,
 *     ensuring consistent scales or ranges.</li>
 *     <li>{@link xyz.derivora.mathTools.validation}:
 *     Provides tools for validating numerical values against intervals or other constraints.</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>{@code
 * // Using mathTools for precise computations
 * BigDecimal number = new BigDecimal("123.456");
 *
 * // Compute order
 * int order = order.mathTools.OrderUtil.orderOf(number);
 *
 * // Normalize value
 * Normalizer<BigDecimal> normalizer = ...;
 * BigDecimal normalized = normalizer.normalize(number, 10, RoundingMode.HALF_UP);
 *
 * // Validate number within an interval
 * IntervalValidator<BigDecimal> validator = ...;
 * validator.validate(number);
 * }</pre>
 *
 * <p>The package is modular, allowing developers to use only the components
 * they need while maintaining high performance and precision.
 */
package mathTools;