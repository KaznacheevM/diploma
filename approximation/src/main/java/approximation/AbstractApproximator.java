package approximation;

import mathTools.accuracy.AccuracyStrategy;

import java.util.Objects;

/**
 * An abstract base class for implementing the {@link Approximator} interface.
 * <p>
 * This class provides a foundation for numerical approximation implementations by handling
 * accuracy strategies and offering a method to adjust the accuracy as needed.
 *
 * @param <T> the type of the numerical result, such as {@link java.math.BigDecimal} or {@link Double}.
 */
public abstract class AbstractApproximator<T extends Number> implements Approximator<T> {

    /**
     * The strategy used for handling numerical accuracy.
     */
    protected final AccuracyStrategy accuracyStrategy;

    /**
     * Constructs an {@code AbstractApproximator} with the specified accuracy strategy.
     *
     * @param accuracyStrategy the {@link AccuracyStrategy} to be used for handling accuracy adjustments.
     * @throws NullPointerException if the provided accuracy strategy is {@code null}.
     */
    public AbstractApproximator(AccuracyStrategy accuracyStrategy) {
        Objects.requireNonNull(accuracyStrategy, "Accuracy strategy cannot be null");
        this.accuracyStrategy = accuracyStrategy;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the accuracy strategy associated with this approximator.
     *
     * @return the {@link AccuracyStrategy} instance used by this approximator.
     */
    @Override
    public final AccuracyStrategy getAccuracyStrategy() {
        return accuracyStrategy;
    }

    /**
     * Adjusts the given accuracy value using the configured accuracy strategy.
     *
     * @param accuracy the initial accuracy value.
     * @return the adjusted accuracy, as defined by the {@link AccuracyStrategy}.
     * @throws IllegalArgumentException if the accuracy value is invalid for adjustment.
     */
    protected int adjustAccuracy(int accuracy) {
        return accuracyStrategy.adjustAccuracy(accuracy);
    }
}
