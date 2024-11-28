package approximation.validation;


import approximation.validation.interval.ArgIntervalFactory;
import mathTools.interval.commons.CommonIntervalFactory;
import toolbox.validation.Validator;

import java.math.BigDecimal;

/**
 * A factory interface for creating validators for mathematical function arguments.
 * <p>
 * This interface provides methods for generating {@link Validator} instances to validate
 * the inputs of functions such as logarithms and exponentials. Validators are designed to ensure
 * that arguments fall within the valid ranges for their respective functions.
 *
 * @param <T> the type of the number that the validators support. Must extend {@link Number} and {@link Comparable}.
 */
public interface ArgValidatorFactory<T extends Number & Comparable<T>> {

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
    Validator<T> logarithmBaseValidator();

    /**
     * Creates a validator for validating the argument of a logarithmic function.
     * <p>
     * The valid range is defined as:
     * <ul>
     *   <li>Non-optimized: (0, +\infty)</li>
     *   <li>Optimized: A narrower interval, typically used to enhance performance in specific use cases.</li>
     * </ul>
     *
     * @param optimized whether the validator should use an optimized range.
     * @return a {@link Validator} instance for validating logarithmic arguments.
     */
    Validator<T> logarithmArgValidator(boolean optimized);

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
    Validator<T> exponentialValidator();

    /**
     * Returns the default implementation of {@code ArgValidatorFactory} for {@link BigDecimal}.
     * <p>
     * The default implementation uses {@link ArgIntervalFactory} and {@link CommonIntervalFactory}
     * to construct validators with predefined intervals.
     *
     * @return a default {@link ArgValidatorFactory} instance for {@link BigDecimal}.
     */
    static ArgValidatorFactory<BigDecimal> getBigInstance() {
        ArgIntervalFactory<BigDecimal> argIntervalFactory = ArgIntervalFactory.getBigInstance();
        CommonIntervalFactory<BigDecimal> commonIntervalFactory = CommonIntervalFactory.getBigInstance();
        return new BigArgValidatorFactory(argIntervalFactory, commonIntervalFactory);
    }
}