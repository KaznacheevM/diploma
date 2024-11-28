package mathTools.interval.factory;

import mathTools.interval.Interval;
import mathTools.interval.IntervalType;

/**
 * A factory interface for creating intervals of various types.
 *
 * <p>This interface provides methods to create different types of intervals, such as closed,
 * open, half-open, and unbounded intervals. Each method accepts the necessary parameters
 * (e.g., bounds) to construct the interval. The factory abstracts the creation logic,
 * ensuring consistency across the application.
 *
 * <p>Usage example:
 * <pre>{@code
 * IntervalFactory factory = IntervalFactory.getDefaultInstance();
 * Interval<Integer> closedInterval = factory.createClosed(1, 10);
 * Interval<Double> unboundedInterval = factory.createUnbounded();
 * }</pre>
 *
 * @see Interval
 * @see IntervalType
 */
public interface IntervalFactory {

    /**
     * Creates a closed interval that includes both the lower and upper bounds.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a closed interval.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    <T extends Number & Comparable<T>> Interval<T> createClosed(T lowerBoundValue, T upperBoundValue);

    /**
     * Creates an open interval that excludes both the lower and upper bounds.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return an open interval.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    <T extends Number & Comparable<T>> Interval<T> createOpen(T lowerBoundValue, T upperBoundValue);

    /**
     * Creates a right-open interval that includes the lower bound but excludes the upper bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a right-open interval.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    <T extends Number & Comparable<T>> Interval<T> createRightOpen(T lowerBoundValue, T upperBoundValue);

    /**
     * Creates a left-open interval that excludes the lower bound but includes the upper bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a left-open interval.
     * @throws NullPointerException if any parameter is {@code null}.
     */
    <T extends Number & Comparable<T>> Interval<T> createLeftOpen(T lowerBoundValue, T upperBoundValue);

    /**
     * Creates a right-unbounded open interval with a finite lower bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a right-unbounded open interval.
     * @throws NullPointerException if {@code lowerBoundValue} is {@code null}.
     */
    <T extends Number & Comparable<T>> Interval<T> createRightUnboundedOpen(T lowerBoundValue);

    /**
     * Creates a left-unbounded open interval with a finite upper bound.
     *
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a left-unbounded open interval.
     * @throws NullPointerException if {@code upperBoundValue} is {@code null}.
     */
    <T extends Number & Comparable<T>> Interval<T> createLeftUnboundedOpen(T upperBoundValue);

    /**
     * Creates a right-unbounded closed interval with a finite lower bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a right-unbounded closed interval.
     * @throws NullPointerException if {@code lowerBoundValue} is {@code null}.
     */
    <T extends Number & Comparable<T>> Interval<T> createRightUnboundedClosed(T lowerBoundValue);

    /**
     * Creates a left-unbounded closed interval with a finite upper bound.
     *
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a left-unbounded closed interval.
     * @throws NullPointerException if {@code upperBoundValue} is {@code null}.
     */
    <T extends Number & Comparable<T>> Interval<T> createLeftUnboundedClosed(T upperBoundValue);

    /**
     * Creates an unbounded interval with no lower or upper bounds.
     *
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return an unbounded interval.
     */
    <T extends Number & Comparable<T>> Interval<T> createUnbounded();

    /**
     * Returns the default implementation of the {@link IntervalFactory}.
     *
     * <p>The default implementation provides standard behavior for creating intervals.
     *
     * @return the default interval factory.
     */
    static IntervalFactory getDefaultInstance() {
        return new DefaultIntervalFactory();
    }
}
