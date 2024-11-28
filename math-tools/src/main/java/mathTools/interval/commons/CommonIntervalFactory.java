package mathTools.interval.commons;

import mathTools.interval.Interval;
import mathTools.interval.factory.IntervalFactory;

import java.math.BigDecimal;

/**
 * A factory interface for creating factory predefined intervals.
 *
 * <p>This interface provides methods for creating frequently used intervals,
 * such as positive, non-negative, negative, and non-positive intervals. It abstracts
 * the creation logic, ensuring consistency across different number types.
 *
 * <p>Each method returns an {@link Interval} instance that satisfies the specific
 * conditions of the requested type (e.g., positive or negative values).
 *
 * <p>Usage example:
 * <pre>{@code
 * CommonIntervalFactory<BigDecimal> factory = CommonIntervalFactory.getBigInstance();
 * Interval<BigDecimal> positiveInterval = factory.createPositive();
 * Interval<BigDecimal> nonNegativeInterval = factory.createNonNegative();
 * }</pre>
 *
 * @param <T> the type of the numerical values in the intervals, must extend {@link Number}
 *            and implement {@link Comparable}.
 * @see Interval
 */
public interface CommonIntervalFactory<T extends Number & Comparable<T>> {

    /**
     * Creates an interval representing all positive values.
     *
     * <p>The interval typically includes all values greater than zero.
     *
     * @return an interval of all positive values.
     */
    Interval<T> createPositive();

    /**
     * Creates an interval representing all non-negative values.
     *
     * <p>The interval typically includes zero and all values greater than zero.
     *
     * @return an interval of all non-negative values.
     */
    Interval<T> createNonNegative();

    /**
     * Creates an interval representing all negative values.
     *
     * <p>The interval typically includes all values less than zero.
     *
     * @return an interval of all negative values.
     */
    Interval<T> createNegative();

    /**
     * Creates an interval representing all non-positive values.
     *
     * <p>The interval typically includes zero and all values less than zero.
     *
     * @return an interval of all non-positive values.
     */
    Interval<T> createNonPositive();

    /**
     * Returns the default factory instance for {@link BigDecimal}-based intervals.
     *
     * <p>The returned factory provides standard behavior for creating intervals with
     * {@link BigDecimal} values.
     *
     * @return a factory instance for {@link BigDecimal}-based intervals.
     */
    static CommonIntervalFactory<BigDecimal> getBigInstance() {
        IntervalFactory intervalFactory = IntervalFactory.getDefaultInstance();
        return new BigCommonIntervalFactory(intervalFactory);
    }
}
