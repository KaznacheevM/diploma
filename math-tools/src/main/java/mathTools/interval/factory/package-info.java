/**
 * Provides factories for creating mathematical intervals with different types and bounds.
 *
 * <p>The main components of this package include:
 * <ul>
 *     <li>{@link mathTools.interval.factory.IntervalFactory}: A factory interface
 *     for creating intervals of various types, such as closed, open, right-open, and left-open.</li>
 *     <li>{@link mathTools.validation.interval.factory.IntervalValidatorFactory}:
 *     Extends the functionality of interval factories by providing interval-based validators.</li>
 * </ul>
 *
 * <p>For predefined intervals (e.g., positive, non-negative, negative), see
 * {@link mathTools.interval.commons.CommonIntervalFactory}.
 *
 * <p>Example usage:
 * <pre>{@code
 * IntervalFactory factory = IntervalFactory.getDefaultInstance();
 * Interval<BigDecimal> openInterval = factory.createOpen(BigDecimal.ZERO, BigDecimal.TEN);
 *
 * boolean isWithin = openInterval.isInInterval(BigDecimal.valueOf(5)); // true
 * }</pre>
 *
 * <p>This package enables developers to define and construct flexible interval types,
 * enhancing their applicability in mathematical computations and validations.
 *
 * @see xyz.derivora.mathTools.interval
 * @see xyz.derivora.mathTools.interval.commons
 */
package mathTools.interval.factory;