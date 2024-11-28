package mathTools.validation.interval.factory;

import mathTools.interval.factory.IntervalFactory;
import mathTools.interval.Interval;
import mathTools.validation.interval.IntervalValidator;

import java.util.Objects;

/**
 * A factory for creating {@link IntervalValidator} instances by delegating interval creation
 * to an underlying {@link IntervalFactory}.
 *
 * <p>This implementation uses an {@link IntervalFactory} to create intervals of various types
 * and wraps them in {@link IntervalValidator} instances. It provides a standard mechanism
 * for constructing validators that check whether values lie within specific intervals.
 *
 * <p>Example usage:
 * <pre>{@code
 * IntervalValidatorFactory factory = DelegatingIntervalValidatorFactory.getDefaultInstance();
 * IntervalValidator<Integer> validator = factory.createClosed(0, 10);
 * validator.validate(5); // Passes validation
 * validator.validate(15); // Throws ValidationException
 * }</pre>
 *
 * @see IntervalValidator
 * @see IntervalFactory
 */
public class DelegatingIntervalValidatorFactory implements IntervalValidatorFactory {

    /**
     * The interval factory used to create intervals.
     */
    protected final IntervalFactory intervalFactory;

    /**
     * Constructs a new factory that delegates interval creation to the specified {@link IntervalFactory}.
     *
     * @param intervalFactory the interval factory to use, must not be {@code null}.
     * @throws NullPointerException if {@code intervalFactory} is {@code null}.
     */
    public DelegatingIntervalValidatorFactory(IntervalFactory intervalFactory) {
        Objects.requireNonNull(intervalFactory, "Interval factory cannot be null");
        this.intervalFactory = intervalFactory;
    }
    
    @Override
    public <T extends Number & Comparable<T>> IntervalValidator<T> createClosed(T lowerBoundValue, T upperBoundValue) {
        Interval<T> interval = intervalFactory.createClosed(lowerBoundValue, upperBoundValue);
        return new IntervalValidator<>(interval);
    }

    @Override
    public <T extends Number & Comparable<T>> IntervalValidator<T> createOpen(T lowerBoundValue, T upperBoundValue) {
        Interval<T> interval = intervalFactory.createOpen(lowerBoundValue, upperBoundValue);
        return new IntervalValidator<>(interval);
    }

    @Override
    public <T extends Number & Comparable<T>> IntervalValidator<T> createRightOpen(T lowerBoundValue, T upperBoundValue) {
        Interval<T> interval = intervalFactory.createRightOpen(lowerBoundValue, upperBoundValue);
        return new IntervalValidator<>(interval);
    }

    @Override
    public <T extends Number & Comparable<T>> IntervalValidator<T> createLeftOpen(T lowerBoundValue, T upperBoundValue) {
        Interval<T> interval = intervalFactory.createLeftOpen(lowerBoundValue, upperBoundValue);
        return new IntervalValidator<>(interval);
    }

    @Override
    public <T extends Number & Comparable<T>> IntervalValidator<T> createRightUnboundedOpen(T lowerBoundValue) {
        Interval<T> interval = intervalFactory.createRightUnboundedOpen(lowerBoundValue);
        return new IntervalValidator<>(interval);
    }

    @Override
    public <T extends Number & Comparable<T>> IntervalValidator<T> createLeftUnboundedOpen(T upperBoundValue) {
        Interval<T> interval = intervalFactory.createLeftUnboundedOpen(upperBoundValue);
        return new IntervalValidator<>(interval);
    }

    @Override
    public <T extends Number & Comparable<T>> IntervalValidator<T> createRightUnboundedClosed(T lowerBoundValue) {
        Interval<T> interval = intervalFactory.createRightUnboundedClosed(lowerBoundValue);
        return new IntervalValidator<>(interval);
    }

    @Override
    public <T extends Number & Comparable<T>> IntervalValidator<T> createLeftUnboundedClosed(T upperBoundValue) {
        Interval<T> interval = intervalFactory.createLeftUnboundedClosed(upperBoundValue);
        return new IntervalValidator<>(interval);
    }

    @Override
    public <T extends Number & Comparable<T>> IntervalValidator<T> createUnbounded() {
        Interval<T> interval = intervalFactory.createUnbounded();
        return new IntervalValidator<>(interval);
    }
}
