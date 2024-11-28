package mathTools.validation.interval.factory;

import mathTools.interval.factory.IntervalFactory;
import mathTools.validation.interval.IntervalValidator;

/**
 * A factory interface for creating {@link IntervalValidator} instances.
 *
 * <p>This interface provides methods to create validators for various types of intervals.
 * Validators are used to ensure that input values lie within specific intervals.
 *
 * <p>The factory relies on an {@link IntervalFactory} to create intervals, which are then
 * wrapped in {@link IntervalValidator} instances.
 *
 * <p>Example usage:
 * <pre>{@code
 * IntervalValidatorFactory factory = IntervalValidatorFactory.getDefaultInstance();
 * IntervalValidator<Integer> validator = factory.createClosed(0, 10);
 * validator.validate(5); // Passes validation
 * validator.validate(15); // Throws ValidationException
 * }</pre>
 *
 * @see IntervalValidator
 * @see IntervalFactory
 */
public interface IntervalValidatorFactory {

    /**
     * Creates a validator for a closed interval, which includes both the lower and upper bounds.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a validator for a closed interval.
     */
    <T extends Number & Comparable<T>> IntervalValidator<T> createClosed(T lowerBoundValue, T upperBoundValue);

    /**
     * Creates a validator for an open interval, which excludes both the lower and upper bounds.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a validator for an open interval.
     */
    <T extends Number & Comparable<T>> IntervalValidator<T> createOpen(T lowerBoundValue, T upperBoundValue);

    /**
     * Creates a validator for a right-open interval, which includes the lower bound but excludes the upper bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a validator for a right-open interval.
     */
    <T extends Number & Comparable<T>> IntervalValidator<T> createRightOpen(T lowerBoundValue, T upperBoundValue);

    /**
     * Creates a validator for a left-open interval, which excludes the lower bound but includes the upper bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a validator for a left-open interval.
     */
    <T extends Number & Comparable<T>> IntervalValidator<T> createLeftOpen(T lowerBoundValue, T upperBoundValue);

    /**
     * Creates a validator for a right-unbounded open interval with a finite lower bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a validator for a right-unbounded open interval.
     */
    <T extends Number & Comparable<T>> IntervalValidator<T> createRightUnboundedOpen(T lowerBoundValue);

    /**
     * Creates a validator for a left-unbounded open interval with a finite upper bound.
     *
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a validator for a left-unbounded open interval.
     */
    <T extends Number & Comparable<T>> IntervalValidator<T> createLeftUnboundedOpen(T upperBoundValue);

    /**
     * Creates a validator for a right-unbounded closed interval with a finite lower bound.
     *
     * @param lowerBoundValue the value of the lower bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a validator for a right-unbounded closed interval.
     */
    <T extends Number & Comparable<T>> IntervalValidator<T> createRightUnboundedClosed(T lowerBoundValue);

    /**
     * Creates a validator for a left-unbounded closed interval with a finite upper bound.
     *
     * @param upperBoundValue the value of the upper bound, must not be {@code null}.
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a validator for a left-unbounded closed interval.
     */
    <T extends Number & Comparable<T>> IntervalValidator<T> createLeftUnboundedClosed(T upperBoundValue);

    /**
     * Creates a validator for an unbounded interval.
     *
     * @param <T> the type of the interval's bound values, must extend {@link Number} and implement {@link Comparable}.
     * @return a validator for an unbounded interval.
     */
    <T extends Number & Comparable<T>> IntervalValidator<T> createUnbounded();

    /**
     * Returns the default implementation of the {@link IntervalValidatorFactory}.
     *
     * <p>The default implementation uses the default {@link IntervalFactory} to create intervals.
     *
     * @return the default validator factory.
     */
    static IntervalValidatorFactory getDefaultInstance() {
        IntervalFactory intervalFactory = IntervalFactory.getDefaultInstance();
        return new DelegatingIntervalValidatorFactory(intervalFactory);
    }
}
