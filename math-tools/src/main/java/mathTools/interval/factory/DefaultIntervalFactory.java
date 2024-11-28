package mathTools.interval.factory;

import mathTools.interval.bound.Bound;
import mathTools.interval.DefaultInterval;
import mathTools.interval.Interval;
import mathTools.interval.IntervalType;
import mathTools.interval.bound.factory.BoundFactory;

/**
 * Default implementation of the {@link IntervalFactory} interface.
 *
 * <p>This factory creates intervals using a default {@link BoundFactory} instance for generating
 * bounds. It supports all types of intervals defined by {@link IntervalType}, ensuring that
 * the bounds and interval types are validated and consistent.
 *
 * <p>Usage example:
 * <pre>{@code
 * IntervalFactory factory = IntervalFactory.getDefaultInstance();
 * Interval<Integer> closedInterval = factory.createClosed(1, 10);
 * Interval<Double> unboundedInterval = factory.createUnbounded();
 * }</pre>
 *
 * <p>Note: This class has package-private visibility and is not intended to be used
 * directly outside its package.
 */
class DefaultIntervalFactory implements IntervalFactory {

    /**
     * The bound factory used for creating bounds for intervals.
     */
    protected static final BoundFactory BOUND_FACTORY = BoundFactory.getDefaultInstance();

    /**
     * Creates a closed interval that includes both the lower and upper bounds.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a closed interval.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    @Override
    public <T extends Number & Comparable<T>> Interval<T> createClosed(T lowerBoundValue, T upperBoundValue) {
        Bound<T> lowerBound = BOUND_FACTORY.createFinitBound(lowerBoundValue);
        Bound<T> upperBound = BOUND_FACTORY.createFinitBound(upperBoundValue);

        return new DefaultInterval<>(IntervalType.CLOSED, lowerBound, upperBound);
    }

    /**
     * Creates an open interval that excludes both the lower and upper bounds.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return an open interval.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    @Override
    public <T extends Number & Comparable<T>> Interval<T> createOpen(T lowerBoundValue, T upperBoundValue) {
        Bound<T> lowerBound = BOUND_FACTORY.createFinitBound(lowerBoundValue);
        Bound<T> upperBound = BOUND_FACTORY.createFinitBound(upperBoundValue);

        return new DefaultInterval<>(IntervalType.OPEN, lowerBound, upperBound);
    }

    /**
     * Creates a right-open interval that includes the lower bound but excludes the upper bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a right-open interval.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    @Override
    public <T extends Number & Comparable<T>> Interval<T> createRightOpen(T lowerBoundValue, T upperBoundValue) {
        Bound<T> lowerBound = BOUND_FACTORY.createFinitBound(lowerBoundValue);
        Bound<T> upperBound = BOUND_FACTORY.createFinitBound(upperBoundValue);

        return new DefaultInterval<>(IntervalType.RIGHT_OPEN, lowerBound, upperBound);
    }

    /**
     * Creates a left-open interval that excludes the lower bound but includes the upper bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a left-open interval.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    @Override
    public <T extends Number & Comparable<T>> Interval<T> createLeftOpen(T lowerBoundValue, T upperBoundValue) {
        Bound<T> lowerBound = BOUND_FACTORY.createFinitBound(lowerBoundValue);
        Bound<T> upperBound = BOUND_FACTORY.createFinitBound(upperBoundValue);

        return new DefaultInterval<>(IntervalType.LEFT_OPEN, lowerBound, upperBound);
    }

    /**
     * Creates a right-unbounded open interval with a finite lower bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a right-unbounded open interval.
     * @throws NullPointerException if {@code lowerBoundValue} is {@code null}.
     */
    @Override
    public <T extends Number & Comparable<T>> Interval<T> createRightUnboundedOpen(T lowerBoundValue) {
        Bound<T> lowerBound = BOUND_FACTORY.createFinitBound(lowerBoundValue);
        Bound<T> upperBound = BOUND_FACTORY.createPositiveInfinity();

        return new DefaultInterval<>(IntervalType.RIGHT_UNBOUNDED_OPEN, lowerBound, upperBound);
    }

    /**
     * Creates a left-unbounded open interval with a finite upper bound.
     *
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a left-unbounded open interval.
     * @throws NullPointerException if {@code upperBoundValue} is {@code null}.
     */
    @Override
    public <T extends Number & Comparable<T>> Interval<T> createLeftUnboundedOpen(T upperBoundValue) {
        Bound<T> lowerBound = BOUND_FACTORY.createNegativeInfinity();
        Bound<T> upperBound = BOUND_FACTORY.createFinitBound(upperBoundValue);

        return new DefaultInterval<>(IntervalType.LEFT_UNBOUNDED_OPEN, lowerBound, upperBound);
    }

    /**
     * Creates a right-unbounded closed interval with a finite lower bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a right-unbounded closed interval.
     * @throws NullPointerException if {@code lowerBoundValue} is {@code null}.
     */
    @Override
    public <T extends Number & Comparable<T>> Interval<T> createRightUnboundedClosed(T lowerBoundValue) {
        Bound<T> lowerBound = BOUND_FACTORY.createFinitBound(lowerBoundValue);
        Bound<T> upperBound = BOUND_FACTORY.createPositiveInfinity();

        return new DefaultInterval<>(IntervalType.RIGHT_UNBOUNDED_CLOSED, lowerBound, upperBound);
    }

    /**
     * Creates a left-unbounded closed interval with a finite upper bound.
     *
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a left-unbounded closed interval.
     * @throws NullPointerException if {@code upperBoundValue} is {@code null}.
     */
    @Override
    public <T extends Number & Comparable<T>> Interval<T> createLeftUnboundedClosed(T upperBoundValue) {
        Bound<T> lowerBound = BOUND_FACTORY.createNegativeInfinity();
        Bound<T> upperBound = BOUND_FACTORY.createFinitBound(upperBoundValue);

        return new DefaultInterval<>(IntervalType.LEFT_UNBOUNDED_CLOSED, lowerBound, upperBound);
    }

    /**
     * Creates an unbounded interval with no lower or upper bounds.
     *
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return an unbounded interval.
     */
    @Override
    public <T extends Number & Comparable<T>> Interval<T> createUnbounded() {
        Bound<T> lowerBound = BOUND_FACTORY.createNegativeInfinity();
        Bound<T> upperBound = BOUND_FACTORY.createPositiveInfinity();
        return new DefaultInterval<>(IntervalType.UNBOUNDED, lowerBound, upperBound);
    }
}
