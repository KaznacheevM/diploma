/**
 * Provides utilities for normalizing numerical values to meet specific requirements or
 * simplify their representation.
 *
 * <p>This package contains tools and abstractions for adjusting numerical values based
 * on accuracy, rounding rules, or other criteria. The utilities in this package are
 * designed for use in mathematical computations, data standardization, and precision
 * handling.
 *
 * <p>Key components:
 * <ul>
 *     <li>{@link mathTools.normalization.Normalizer}:
 *     Defines a general interface for normalizing numerical values according to specified
 *     parameters, such as accuracy and rounding mode.</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>{@code
 * Normalizer<BigDecimal> normalizer = ...;
 * BigDecimal normalizedValue = normalizer.normalize(new BigDecimal("123.456"), 2, RoundingMode.HALF_UP);
 * }</pre>
 *
 * <p>The package is useful in scenarios requiring consistent numerical value adjustments
 * and precision control across various domains, such as scientific computations and financial applications.
 */
package mathTools.normalization;