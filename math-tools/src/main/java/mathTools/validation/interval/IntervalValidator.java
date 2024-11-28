package mathTools.validation.interval;

import mathTools.interval.Interval;
import toolbox.validation.ValidationException;
import toolbox.validation.Validator;

import java.util.Objects;

/**
 * Validates whether a given argument lies within a specified interval.
 *
 * @param <T> the type of the argument and the interval bounds, must extend {@link Number} and implement {@link Comparable}.
 */
public class IntervalValidator<T extends Number & Comparable<T>> implements Validator<T> {

    /**
     * The interval against which arguments are validated.
     */
    protected final Interval<T> argInterval;

    /**
     * Creates a new validator with the specified interval.
     *
     * @param argInterval the interval for validation, must not be {@code null}.
     * @throws NullPointerException if {@code argInterval} is {@code null}.
     */
    public IntervalValidator(Interval<T> argInterval) {
        Objects.requireNonNull(argInterval, "Interval cannot be null");
        this.argInterval = argInterval;
    }

    /**
     * Validates that the given argument lies within the interval.
     *
     * @param arg the argument to validate, must not be {@code null}.
     * @throws NullPointerException if {@code arg} is {@code null}.
     * @throws ValidationException if {@code arg} is not within the interval.
     */
    @Override
    public void validate(T arg) {
        if (!argInterval.isInInterval(arg)) {
            throw new ValidationException("Argument " + arg + " is not within interval " + argInterval);
        }
    }
}
