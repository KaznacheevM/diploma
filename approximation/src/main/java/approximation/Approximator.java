package approximation;

import mathTools.accuracy.AccuracyStrategy;
import mathTools.order.OrderProvider;

import java.math.RoundingMode;

/**
 * An interface for performing numerical approximations with specified accuracy.
 * <p>
 * Extends the functionality of {@link OrderProvider} and supports specifying accuracy
 * handling strategies through {@link AccuracyStrategy}.
 *
 * @param <T> the type of the numerical result, such as {@link java.math.BigDecimal} or {@link Double}.
 */
public interface Approximator<T extends Number> extends OrderProvider {

    /**
     * Computes the approximation with the specified accuracy and rounding mode.
     *
     * @param accuracy     the required accuracy for the computation. Interpretation of this value
     *                     depends on the implementation (e.g., significant figures, decimal places, etc.).
     * @param roundingMode the rounding mode to be applied, as defined in {@link java.math.RoundingMode}.
     * @return the approximated value of type {@code T}.
     * @throws IllegalArgumentException if the provided parameters are invalid, such as negative accuracy.
     */
    T approximate(int accuracy, RoundingMode roundingMode);

    /**
     * Returns the strategy used for handling numerical accuracy.
     *
     * @return the {@link AccuracyStrategy} associated with this approximator.
     */
    AccuracyStrategy getAccuracyStrategy();
}
