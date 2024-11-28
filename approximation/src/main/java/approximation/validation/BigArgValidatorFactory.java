package approximation.validation;

import approximation.validation.interval.ArgIntervalFactory;
import mathTools.interval.Interval;
import mathTools.interval.commons.CommonIntervalFactory;
import mathTools.validation.interval.IntervalValidator;
import toolbox.validation.ValidationException;
import toolbox.validation.Validator;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A high-precision implementation of {@link ArgValidatorFactory} for creating validators using {@link BigDecimal}.
 * <p>
 * This class provides validators for validating arguments of mathematical functions such as logarithms and exponentials.
 * It uses a combination of {@link ArgIntervalFactory} and {@link CommonIntervalFactory} to construct validators
 * based on predefined intervals.
 */
class BigArgValidatorFactory implements ArgValidatorFactory<BigDecimal> {

    /**
     * The factory for creating argument intervals.
     */
    protected final ArgIntervalFactory<BigDecimal> argIntervalFactory;

    /**
     * The factory for creating common predefined intervals for {@link BigDecimal}.
     */
    protected final CommonIntervalFactory<BigDecimal> commonIntervalFactory;

    /**
     * Constructs a {@code BigArgValidatorFactory} with the specified interval factories.
     *
     * @param argIntervalFactory    the {@link ArgIntervalFactory} for creating argument intervals.
     * @param commonIntervalFactory the {@link CommonIntervalFactory} for creating common predefined intervals.
     * @throws NullPointerException if any of the provided factories are {@code null}.
     */
    public BigArgValidatorFactory(ArgIntervalFactory<BigDecimal> argIntervalFactory, CommonIntervalFactory<BigDecimal> commonIntervalFactory) {
        Objects.requireNonNull(argIntervalFactory, "Argument interval factory cannot be null");
        Objects.requireNonNull(argIntervalFactory, "Common interval factory cannot be null");
        this.argIntervalFactory = argIntervalFactory;
        this.commonIntervalFactory = commonIntervalFactory;
    }

    /**
     * Creates a validator for validating the base of a logarithmic function.
     * <p>
     * The valid range is defined as:
     * <pre>
     *     (0, 1) ∪ (1, +∞)
     * </pre>
     * ensuring that the base is positive and not equal to 1.
     *
     * @return a {@link Validator} instance for validating logarithmic bases.
     */
    @Override
    public Validator<BigDecimal> logarithmBaseValidator() {
        Interval<BigDecimal> interval = commonIntervalFactory.createPositive();
        Validator<BigDecimal> intervalValidator = new IntervalValidator<>(interval);

        Validator<BigDecimal> notOne = arg -> {
            if (arg.compareTo(BigDecimal.ONE) == 0) {
                throw new ValidationException("Logarithm base cannot equals one");
            }
        };

        return Validator.combine(BigDecimal.class, intervalValidator, notOne);
    }

    /**
     * Creates a validator for validating the argument of a logarithmic function.
     * <p>
     * The valid range is defined as:
     * <ul>
     *   <li>Non-optimized: (0, +\infty)</li>
     *   <li>Optimized: A narrower interval for improved performance in specific cases.</li>
     * </ul>
     *
     * @param optimized whether the validator should use an optimized range.
     * @return a {@link Validator} instance for validating logarithmic arguments.
     */
    @Override
    public Validator<BigDecimal> logarithmArgValidator(boolean optimized) {
        Interval<BigDecimal> interval = argIntervalFactory.logarithmArgInterval(optimized);
        return new IntervalValidator<>(interval);
    }

    /**
     * Creates a validator for validating the argument of an exponential function.
     * <p>
     * The valid range is defined as:
     * <pre>
     *     (-∞, +∞)
     * </pre>
     * indicating that the exponential function is defined for all real numbers.
     *
     * @return a {@link Validator} instance for validating exponential arguments.
     */
    @Override
    public Validator<BigDecimal> exponentialValidator() {
        Interval<BigDecimal> interval = argIntervalFactory.exponentialInterval();
        return new IntervalValidator<>(interval);
    }
}
