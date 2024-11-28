package approximation.validation.interval;

import mathTools.interval.Interval;
import mathTools.interval.factory.IntervalFactory;

import java.math.BigDecimal;

/**
 * A factory interface for creating argument intervals used in mathematical approximations.
 * <p>
 * This interface provides methods for generating intervals that validate arguments for
 * specific mathematical functions, such as logarithms and exponentials.
 *
 * @param <T> the type of the number that the intervals support. Must extend {@link Number} and {@link Comparable}.
 */
public interface ArgIntervalFactory<T extends Number & Comparable<T>> {

    /**
     * Creates an interval for validating arguments of the logarithmic function.
     * <p>
     * The interval is defined as:
     * <ul>
     *   <li>Non-optimized: (0; +\infty)</li>
     *   <li>Optimized: A narrower interval, e.g., [0.52; 1.92], to improve performance for specific cases.</li>
     * </ul>
     *
     * @param optimized whether the interval should be optimized for specific use cases.
     * @return an {@link Interval} instance representing the valid range of logarithmic arguments.
     */
    Interval<T> logarithmArgInterval(boolean optimized);

    /**
     * Creates an interval for validating arguments of the exponential function.
     * <p>
     * The interval is defined as:
     * <pre>
     *     (-∞; +∞)
     * </pre>
     * indicating that the exponential function is defined for all real numbers.
     *
     * @return an {@link Interval} instance representing the valid range of exponential arguments.
     */
    Interval<T> exponentialInterval();

    /**
     * Returns the default implementation of {@code ArgIntervalFactory} for {@link BigDecimal}.
     * <p>
     * The default implementation uses the {@link IntervalFactory} to construct intervals.
     *
     * @return a default {@link ArgIntervalFactory} instance for {@link BigDecimal}.
     */
    static ArgIntervalFactory<BigDecimal> getBigInstance() {
        IntervalFactory intervalFactory = IntervalFactory.getDefaultInstance();
        return new BigIntervalFactory(intervalFactory);
    }
}
