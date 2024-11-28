package mathTools.validation.interval.commons;

import mathTools.interval.commons.CommonIntervalFactory;
import toolbox.validation.Validator;

import java.math.BigDecimal;

/**
 * A factory interface for creating common validators for numerical ranges.
 *
 * <p>This interface provides methods to create validators that check whether a given value
 * belongs to predefined numerical intervals, such as positive, non-negative, negative, or
 * non-positive ranges. The validators rely on the underlying intervals created by a
 * {@link CommonIntervalFactory}.
 *
 * <p>Example usage:
 * <pre>{@code
 * CommonValidatorFactory<BigDecimal> factory = CommonValidatorFactory.getBigInstance();
 * Validator<BigDecimal> positiveValidator = factory.createPositive();
 * positiveValidator.validate(BigDecimal.valueOf(5));  // Passes validation
 * positiveValidator.validate(BigDecimal.valueOf(-5)); // Throws ValidationException
 * }</pre>
 *
 * @param <T> the type of the numbers being validated, must extend {@link Number} and implement {@link Comparable}.
 * @see Validator
 * @see CommonIntervalFactory
 */
public interface CommonValidatorFactory<T extends Number & Comparable<T>> {

    /**
     * Creates a validator that checks whether a value is positive.
     *
     * @return a validator for positive values.
     */
    Validator<T> createPositive();

    /**
     * Creates a validator that checks whether a value is non-negative.
     *
     * @return a validator for non-negative values.
     */
    Validator<T> createNonNegative();

    /**
     * Creates a validator that checks whether a value is negative.
     *
     * @return a validator for negative values.
     */
    Validator<T> createNegative();

    /**
     * Creates a validator that checks whether a value is non-positive.
     *
     * @return a validator for non-positive values.
     */
    Validator<T> createNonPositive();

    /**
     * Returns a {@link CommonValidatorFactory} instance for {@link BigDecimal}.
     *
     * <p>The instance uses a {@link CommonIntervalFactory} to create the intervals
     * required for validation.
     *
     * @return a {@link CommonValidatorFactory} for {@link BigDecimal}.
     */
    static CommonValidatorFactory<BigDecimal> getBigInstance() {
        CommonIntervalFactory<BigDecimal> commonIntervalFactory = CommonIntervalFactory.getBigInstance();
        return new DelegatingCommonValidatorFactory<>(commonIntervalFactory);
    }
}
