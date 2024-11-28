/**
 * Provides core interfaces and classes for representing and manipulating mathematical intervals.
 *
 * <p>The main components of this package include:
 * <ul>
 *     <li>{@link mathTools.interval.Interval}: Represents an interval defined by its bounds
 *     and type (e.g., open, closed, unbounded).</li>
 *     <li>{@link mathTools.interval.IntervalType}: Enumerates types of intervals,
 *     such as closed, open, right-open, and left-open.</li>
 *     <li>Bound-related utilities, which are located in the {@code xyz.derivora.mathTools.interval.bound} package.</li>
 * </ul>
 *
 * <p>This package focuses on the foundational structures required for interval arithmetic
 * and validation. For interval factories and predefined intervals, see the {@code factory} and
 * {@code factory} subpackages.
 *
 * <p>Example usage:
 * <pre>{@code
 * Interval<BigDecimal> closedInterval = IntervalFactory.getDefaultInstance()
 *     .createClosed(BigDecimal.ZERO, BigDecimal.TEN);
 *
 * boolean isWithin = closedInterval.isInInterval(BigDecimal.valueOf(5)); // true
 * }</pre>
 *
 * @see xyz.derivora.mathTools.interval.bound
 * @see xyz.derivora.mathTools.interval.factory
 * @see xyz.derivora.mathTools.interval.commons
 */
package mathTools.interval;