package toolbox.validation;

import java.util.Arrays;
import java.util.Objects;

/**
 * A functional interface for validating objects of a specific type.
 * <p>
 * The {@code Validator} interface provides a contract for implementing validation logic
 * for objects of type {@code T}. It also includes static factory methods for creating
 * common validators and combining multiple validators into a single one.
 * </p>
 *
 * <h2>Core Features</h2>
 * <ul>
 *     <li>Custom validation logic for objects of type {@code T}.</li>
 *     <li>Predefined validators, such as {@link #nonNull()} and {@link #unnecessary()}.</li>
 *     <li>Support for combining multiple validators sequentially or in parallel.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * Validator<Number> nonNullValidator = Validator.nonNull();
 * nonNullValidator.validate(42); // Passes validation
 *
 * Validator<Number> combinedValidator = Validator.combine(
 *     Number.class, Validator.nonNull(), Validator.unnecessary()
 * );
 * combinedValidator.validate(42); // Passes validation
 *
 * combinedValidator.validate(null); // Throws NullPointerException
 * }</pre>
 *
 * @param <T> the type of objects to be validated
 */
@FunctionalInterface
public interface Validator<T> {

    /**
     * Validates the given object.
     * <p>
     * This method defines the core validation logic for the object of type {@code T}.
     * Implementations should throw an appropriate exception if the validation fails.
     * </p>
     *
     * @param arg the object to validate
     * @throws RuntimeException if the object fails validation
     */
    void validate(T arg);

    /**
     * Creates a no-op validator that performs no validation.
     * <p>
     * This validator can be used as a placeholder when no validation logic is required.
     * </p>
     *
     * @param <T> the type of objects to be validated
     * @return a no-op validator
     */
    static <T extends Number> Validator<T> unnecessary() {
        return arg -> {};
    }

    /**
     * Creates a validator that ensures the object is non-null.
     * <p>
     * If the given object is {@code null}, this validator throws a {@link NullPointerException}.
     * </p>
     *
     * @param <T> the type of objects to be validated
     * @return a validator that checks for non-null values
     */
    static <T extends Number> Validator<T> nonNull() {
        return arg -> Objects.requireNonNull(arg, "Argument cannot be null");
    }

    /**
     * Combines multiple validators into a single sequential validator.
     * <p>
     * The returned validator applies each of the given validators in order, throwing
     * an exception if any of them fails.
     * </p>
     *
     * @param <T> the type of objects to be validated
     * @param type the class of the objects to be validated
     * @param validators the validators to combine
     * @return a combined validator that applies the given validators sequentially
     * @throws NullPointerException if {@code type} or any validator is {@code null}
     */
    @SafeVarargs
    static <T extends Number> Validator<T> combine(Class<T> type, Validator<T>... validators) {
        Objects.requireNonNull(type, "Type variable cannot be null");
        ValidationUtil.requireAllNonNull("Validator cannot be null", (Object[]) validators);

        return arg -> {
            for (Validator<T> validator : validators) {
                validator.validate(arg);
            }
        };
    }

    /**
     * Combines multiple validators into a single parallel validator.
     * <p>
     * The returned validator applies each of the given validators in parallel,
     * potentially improving performance for complex validation logic. Exceptions
     * are thrown if any of the validators fail.
     * </p>
     *
     * @param <T> the type of objects to be validated
     * @param type the class of the objects to be validated
     * @param validators the validators to combine
     * @return a combined validator that applies the given validators in parallel
     * @throws NullPointerException if {@code type} or any validator is {@code null}
     */
    @SafeVarargs
    static <T extends Number> Validator<T> combineParallel(Class<T> type, Validator<T>... validators) {
        Objects.requireNonNull(type, "Type variable cannot be null");
        ValidationUtil.requireAllNonNull("Validator cannot be null", (Object[]) validators);

        return arg -> Arrays.stream(validators)
                            .parallel()
                            .forEach(validator -> validator.validate(arg));
    }
}
