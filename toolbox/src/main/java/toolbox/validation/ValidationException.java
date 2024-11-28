package toolbox.validation;

/**
 * Represents an exception thrown when a validation error occurs.
 *
 * <p>This exception is used to indicate that a given input does not meet
 * the expected validation criteria. It allows for detailed error messages
 * and optional causes for better debugging and error handling.
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructs a new validation exception with the specified detail message.
     *
     * @param message the detail message, must not be {@code null}.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new validation exception with the specified detail message and cause.
     *
     * @param message the detail message, must not be {@code null}.
     * @param cause   the cause of the exception, may be {@code null}.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
