/**
 * Contains classes and interfaces for creating predefined intervals with common bounds.
 *
 * <p>The main components of this package include:
 * <ul>
 *     <li>{@link mathTools.interval.commons.CommonIntervalFactory}: A factory interface for creating common intervals.</li>
 *     <li>{@link mathTools.interval.commons.BigCommonIntervalFactory}: A specific implementation
 *         of {@link mathTools.interval.commons.CommonIntervalFactory}
 *     for intervals with {@link java.math.BigDecimal} bounds.</li>
 * </ul>
 *
 * <p>These factories simplify the process of working with frequently used interval types, such as positive,
 * negative, non-positive, and non-negative intervals.
 */
package mathTools.interval.commons;