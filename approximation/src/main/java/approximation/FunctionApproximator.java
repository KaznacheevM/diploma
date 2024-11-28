package approximation;

import mathTools.accuracy.AccuracyStrategy;
import toolbox.validation.ValidationException;
import toolbox.validation.Validator;

/**
 * An abstract base class for function approximators.
 * <p>
 * Extends {@link AbstractApproximator} and provides additional functionality for handling
 * a single argument of type {@code T}, which represents the input to the approximated function.
 *
 * @param <T> the type of the argument and numerical result, such as {@link java.math.BigDecimal} or {@link Double}.
 */
public abstract class FunctionApproximator<T extends Number> extends AbstractApproximator<T> {

    /**
     * The argument for the function being approximated.
     */
    protected final T arg;

    /**
     * Constructs a {@code FunctionApproximator} with the specified accuracy strategy, argument validator, and argument.
     *
     * @param accuracyStrategy  the {@link AccuracyStrategy} used for handling accuracy adjustments.
     * @param argumentValidator a {@link Validator} used to validate the provided argument.
     * @param arg               the argument for the function to be approximated.
     * @throws NullPointerException     if any of the parameters are {@code null}.
     * @throws ValidationException if the argument fails validation.
     */
    public FunctionApproximator(AccuracyStrategy accuracyStrategy, Validator<T> argumentValidator, T arg) {
        super(accuracyStrategy);

        argumentValidator.validate(arg);
        this.arg = arg;
    }

    /**
     * Returns the argument used for the function approximation.
     *
     * @return the argument of type {@code T}.
     */
    public final T getArg() {
        return arg;
    }
}
