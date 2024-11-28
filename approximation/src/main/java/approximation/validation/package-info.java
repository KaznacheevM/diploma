/**
 * Provides factories for creating validators for mathematical function arguments.
 * <p>
 * This package defines interfaces and implementations for generating {@link toolbox.validation.Validator}
 * instances to validate arguments of various mathematical functions, such as logarithms and exponentials.
 * Validators ensure that arguments fall within valid ranges and satisfy specific constraints.
 * <p>
 * Key components:
 * <ul>
 *   <li>{@link approximation.validation.ArgValidatorFactory} - An interface for defining factories that create validators.</li>
 *   <li>{@link approximation.validation.BigArgValidatorFactory} - A high-precision implementation of {@code ArgValidatorFactory} using {@link java.math.BigDecimal}.</li>
 * </ul>
 * Example validations:
 * <ul>
 *   <li>Logarithmic base: (0, 1) U (1, +\infty), ensuring the base is positive and not equal to 1.</li>
 *   <li>Logarithmic argument:
 *     <ul>
 *       <li>Non-optimized: (0, +\infty)</li>
 *       <li>Optimized: A narrower range, e.g., [0.52, 1.92], for specific use cases.</li>
 *     </ul>
 *   </li>
 *   <li>Exponential argument: (-∞, +∞), allowing all real numbers.</li>
 * </ul>
 */
package approximation.validation;