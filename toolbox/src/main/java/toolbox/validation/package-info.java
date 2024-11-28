/**
 * Provides utilities and interfaces for validating objects and handling validation errors.
 * <p>
 * This package contains classes and interfaces designed to simplify validation
 * of objects in various contexts. It includes flexible validation utilities,
 * functional interfaces for custom validation logic, predefined methods for common
 * validation scenarios, and support for handling validation errors through exceptions.
 * </p>
 *
 * <h2>Core Components</h2>
 * <ul>
 *     <li>{@link toolbox.validation.Validator} — a functional interface
 *     for implementing custom validation logic and combining multiple validators.</li>
 *     <li>{@link toolbox.validation.ValidationUtil} — a utility class for
 *     validating multiple arguments, including null-checks with or without custom messages.</li>
 *     <li>{@link toolbox.validation.ValidationException} — an exception
 *     thrown when a validation error occurs, allowing for detailed error messages
 *     and causes.</li>
 * </ul>
 *
 * <h2>Features</h2>
 * <ul>
 *     <li>Customizable validation logic with the {@code Validator} functional interface.</li>
 *     <li>Static utility methods for quick and reusable validation tasks.</li>
 *     <li>Support for combining multiple validators sequentially or in parallel.</li>
 *     <li>Error handling via {@link toolbox.validation.ValidationException}.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * Validator<String> nonEmptyStringValidator = arg -> {
 *     if (arg == null || arg.isEmpty()) {
 *         throw new ValidationException("String must not be null or empty");
 *     }
 * };
 *
 * // Using ValidationUtil for argument validation
 * ValidationUtil.requireAllNonNull("Value cannot be null", "example", 42);
 * }</pre>
 *
 * @see toolbox.validation.Validator
 * @see toolbox.validation.ValidationUtil
 * @see toolbox.validation.ValidationException
 */
package toolbox.validation;