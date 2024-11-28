package mathTools.interval.commons;

import mathTools.interval.Interval;
import mathTools.interval.factory.IntervalFactory;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A factory for creating factory predefined intervals for {@link BigDecimal}.
 *
 * <p>This class provides implementations for creating frequently used intervals
 * (e.g., positive, non-negative, negative, and non-positive) specifically for
 * {@link BigDecimal} values. It uses the default {@link IntervalFactory} instance
 * for constructing the intervals.
 *
 * <p>All methods return intervals that adhere to the standard mathematical definitions:
 * <ul>
 *     <li><b>Positive:</b> Values strictly greater than zero.</li>
 *     <li><b>Non-negative:</b> Values greater than or equal to zero.</li>
 *     <li><b>Negative:</b> Values strictly less than zero.</li>
 *     <li><b>Non-positive:</b> Values less than or equal to zero.</li>
 * </ul>
 *
 * <p>Usage example:
 * <pre>{@code
 * CommonIntervalFactory<BigDecimal> factory = CommonIntervalFactory.getBigInstance();
 * Interval<BigDecimal> positiveInterval = factory.createPositive();
 * Interval<BigDecimal> nonNegativeInterval = factory.createNonNegative();
 * }</pre>
 *
 * @see CommonIntervalFactory
 * @see Interval
 * @see IntervalFactory
 */
class BigCommonIntervalFactory implements CommonIntervalFactory<BigDecimal> {

    /**
     * The interval factory used for constructing the intervals.
     */
    private final IntervalFactory intervalFactory;

    /**
     * Constructs a new {@link BigCommonIntervalFactory} with the specified {@link IntervalFactory}.
     *
     * <p>This constructor allows the factory to utilize a custom implementation of {@link IntervalFactory}
     * for creating predefined intervals, such as positive, non-negative, negative, and non-positive intervals.
     * By injecting a specific {@link IntervalFactory}, the behavior of interval creation can be tailored to
     * different requirements or configurations.
     *
     * @param intervalFactory the factory to use for creating intervals, must not be {@code null}.
     * @throws NullPointerException if {@code intervalFactory} is {@code null}.
     */
    public BigCommonIntervalFactory(IntervalFactory intervalFactory) {
        Objects.requireNonNull(intervalFactory, "Interval factory cannot be null");
        this.intervalFactory = intervalFactory;
    }

    /**
     * Creates an interval representing all positive {@link BigDecimal} values.
     *
     * <p>The interval includes all values strictly greater than zero.
     *
     * @return an interval of all positive {@link BigDecimal} values.
     */
    @Override
    public Interval<BigDecimal> createPositive() {
        return intervalFactory.createRightUnboundedOpen(BigDecimal.ZERO);
    }

    /**
     * Creates an interval representing all non-negative {@link BigDecimal} values.
     *
     * <p>The interval includes zero and all values greater than zero.
     *
     * @return an interval of all non-negative {@link BigDecimal} values.
     */
    @Override
    public Interval<BigDecimal> createNonNegative() {
        return intervalFactory.createRightUnboundedClosed(BigDecimal.ZERO);
    }

    /**
     * Creates an interval representing all negative {@link BigDecimal} values.
     *
     * <p>The interval includes all values strictly less than zero.
     *
     * @return an interval of all negative {@link BigDecimal} values.
     */
    @Override
    public Interval<BigDecimal> createNegative() {
        return intervalFactory.createLeftUnboundedOpen(BigDecimal.ZERO);
    }

    /**
     * Creates an interval representing all non-positive {@link BigDecimal} values.
     *
     * <p>The interval includes zero and all values less than zero.
     *
     * @return an interval of all non-positive {@link BigDecimal} values.
     */
    @Override
    public Interval<BigDecimal> createNonPositive() {
        return intervalFactory.createLeftUnboundedClosed(BigDecimal.ZERO);
    }
}
