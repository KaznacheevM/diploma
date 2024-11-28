package mathTools.validation.interval.commons;

import mathTools.interval.commons.CommonIntervalFactory;
import mathTools.interval.Interval;
import mathTools.validation.interval.IntervalValidator;
import toolbox.validation.Validator;

import java.util.Objects;

/**
 * A factory for creating common {@link Validator} instances by delegating interval creation
 * to a {@link CommonIntervalFactory}.
 *
 * <p>This implementation uses a {@link CommonIntervalFactory} to create predefined intervals
 * such as positive, non-negative, negative, or non-positive. These intervals are then wrapped
 * in {@link IntervalValidator} instances, which ensure that values lie within the specified ranges.
 *
 * <p>Example usage:
 * <pre>{@code
 * CommonIntervalFactory<BigDecimal> intervalFactory = CommonIntervalFactory.getBigInstance();
 * CommonValidatorFactory<BigDecimal> validatorFactory = new DelegatingCommonValidatorFactory<>(intervalFactory);
 * Validator<BigDecimal> positiveValidator = validatorFactory.createPositive();
 * positiveValidator.validate(BigDecimal.valueOf(10)); // Passes validation
 * positiveValidator.validate(BigDecimal.valueOf(-10)); // Throws ValidationException
 * }</pre>
 *
 * @param <T> the type of the numbers being validated, must extend {@link Number} and implement {@link Comparable}.
 * @see Validator
 * @see CommonIntervalFactory
 * @see IntervalValidator
 */
public class DelegatingCommonValidatorFactory<T extends Number & Comparable<T>> implements CommonValidatorFactory<T> {

    /**
     * The interval factory used to create predefined intervals for validation.
     */
    protected final CommonIntervalFactory<T> intervalFactory;

    /**
     * Constructs a new factory that delegates interval creation to the specified {@link CommonIntervalFactory}.
     *
     * @param intervalFactory the interval factory to use, must not be {@code null}.
     * @throws NullPointerException if {@code intervalFactory} is {@code null}.
     */
    public DelegatingCommonValidatorFactory(CommonIntervalFactory<T> intervalFactory) {
        Objects.requireNonNull(intervalFactory, "Interval factory cannot be null");
        this.intervalFactory = intervalFactory;
    }

    /**
     * Creates a validator that checks whether a value is positive.
     *
     * @return a validator for positive values.
     */
    @Override
    public Validator<T> createPositive() {
        Interval<T> interval = intervalFactory.createPositive();
        return new IntervalValidator<>(interval);
    }

    /**
     * Creates a validator that checks whether a value is non-negative.
     *
     * @return a validator for non-negative values.
     */
    @Override
    public Validator<T> createNonNegative() {
        Interval<T> interval = intervalFactory.createNonNegative();
        return new IntervalValidator<>(interval);
    }

    /**
     * Creates a validator that checks whether a value is negative.
     *
     * @return a validator for negative values.
     */
    @Override
    public Validator<T> createNegative() {
        Interval<T> interval = intervalFactory.createNegative();
        return new IntervalValidator<>(interval);
    }

    /**
     * Creates a validator that checks whether a value is non-positive.
     *
     * @return a validator for non-positive values.
     */
    @Override
    public Validator<T> createNonPositive() {
        Interval<T> interval = intervalFactory.createNonPositive();
        return new IntervalValidator<>(interval);
    }
}
