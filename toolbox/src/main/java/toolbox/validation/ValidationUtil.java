package toolbox.validation;

import java.util.Objects;

/**
 * A utility class for validating input arguments.
 * <p>
 * This class provides static methods to validate that all provided arguments are non-null,
 * throwing {@link NullPointerException} if any argument is {@code null}. It supports both
 * default and custom exception messages.
 * </p>
 *
 * <h2>Features</h2>
 * <ul>
 *     <li>Validate multiple arguments for non-null values.</li>
 *     <li>Provide custom error messages for null values.</li>
 *     <li>Lightweight and reusable validation utilities.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * // Validate that all arguments are non-null
 * ValidationUtil.requireAllNonNull("arg1", "arg2", new Object());
 *
 * // Validate with a custom error message
 * ValidationUtil.requireAllNonNull("Custom error message", "arg1", null); // Throws NullPointerException
 * }</pre>
 *
 * @see Objects
 */
public class ValidationUtil {

    /**
     * Validates that all provided arguments are non-null.
     * <p>
     * This method checks each argument in the provided array and throws a {@link NullPointerException}
     * if any of them is {@code null}. If the array itself is {@code null}, a {@code NullPointerException}
     * is thrown.
     * </p>
     *
     * @param args the array of arguments to validate
     * @throws NullPointerException if the array or any of its elements is {@code null}
     */
    public static void requireAllNonNull(Object... args) {
        Objects.requireNonNull(args, "Arguments array cannot be null");
        for (Object arg : args) {
            Objects.requireNonNull(arg, "Argument cannot be null");
        }
    }

    /**
     * Validates that all provided arguments are non-null, using a custom error message.
     * <p>
     * This method checks each argument in the provided array and throws a {@link NullPointerException}
     * if any of them is {@code null}. If the array itself is {@code null}, a {@code NullPointerException}
     * is thrown with the default message "Arguments array cannot be null".
     * </p>
     *
     * <p>
     * The custom message is included in the exception if a specific argument is {@code null}.
     * </p>
     *
     * @param message the custom error message to use if an argument is {@code null}
     * @param args the array of arguments to validate
     * @throws NullPointerException if the array or any of its elements is {@code null}
     */
    public static void requireAllNonNull(String message, Object... args) {
        Objects.requireNonNull(args, "Arguments array cannot be null");
        for (Object arg : args) {
            Objects.requireNonNull(arg, message);
        }
    }
}
