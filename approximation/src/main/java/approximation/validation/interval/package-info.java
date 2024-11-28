/**
 * Provides factories for creating argument intervals used in mathematical approximations.
 * <p>
 * This package defines interfaces and classes for generating intervals that validate
 * the inputs of mathematical functions, such as logarithms and exponential.
 * It supports both general-purpose intervals and optimized intervals for specific use cases.
 * <p>
 * Key components:
 * <ul>
 *   <li>{@link approximation.validation.interval.ArgIntervalFactory} - An interface for defining argument interval factories.</li>
 *   <li>{@link approximation.validation.interval.BigIntervalFactory} - A high-precision implementation of {@code ArgIntervalFactory} using {@link java.math.BigDecimal}.</li>
 * </ul>
 * Example intervals:
 * <ul>
 *   <li>Logarithmic arguments:
 *     <ul>
 *       <li>Non-optimized: [0; +\infty)</li>
 *       <li>Optimized: [0.52; 1.92]</li>
 *     </ul>
 *   </li>
 *   <li>Exponential arguments: (-∞; +∞)</li>
 * </ul>
 */
package approximation.validation.interval;