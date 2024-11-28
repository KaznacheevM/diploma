package approximation.order;

import approximation.Approximator;
import mathTools.accuracy.AccuracyStrategy;
import mathTools.order.OrderComputer;

import java.math.RoundingMode;
import java.util.Objects;

/**
 * An abstract base class for computing the order of approximations.
 * <p>
 * This class provides a framework for calculating the magnitude or order of an approximation
 * by combining an {@link Approximator} with an {@link AccuracyStrategy}. Subclasses
 * are responsible for providing a method to estimate the lower bound of the order.
 *
 * @param <T> the numerical type of the approximations (e.g., {@link java.math.BigDecimal}, {@link Double}).
 */
public abstract class ApproximationOrderComputer<T extends Number> implements OrderComputer {

    /**
     * The approximator used to compute values for determining the order.
     */
    protected final Approximator<T> approximator;

    /**
     * The accuracy strategy used to adjust the precision of computations.
     */
    protected final AccuracyStrategy accuracyStrategy;

    /**
     * Constructs an {@code ApproximationOrderComputer} with the specified approximator and accuracy strategy.
     *
     * @param approximator      the {@link Approximator} used to compute approximations.
     * @param accuracyStrategy  the {@link AccuracyStrategy} used to manage precision adjustments.
     * @throws NullPointerException if {@code approximator} or {@code accuracyStrategy} is {@code null}.
     */
    public ApproximationOrderComputer(Approximator<T> approximator, AccuracyStrategy accuracyStrategy) {
        Objects.requireNonNull(approximator, "Approximator cannot be null");
        this.approximator = approximator;
        this.accuracyStrategy = accuracyStrategy;
    }

    /**
     * Computes a coarse approximation of the value.
     * <p>
     * This method calculates an approximation with reduced accuracy, determined
     * by the {@link #orderLowerEstimation()} method and adjusted using the {@link AccuracyStrategy}.
     *
     * @return a coarse approximation as an instance of {@code T}.
     */
    protected final T approximateCoarsely() {
        int orderLowerEstimation = orderLowerEstimation();
        int accuracy = accuracyStrategy.leadingDigitPosition(orderLowerEstimation);
        return approximator.approximate(accuracy, RoundingMode.DOWN);
    }

    /**
     * Estimates the lower bound of the order of the approximation.
     * <p>
     * This method must be implemented by subclasses to provide a specific strategy
     * for estimating the order.
     *
     * @return the estimated lower bound of the order as an integer.
     */
    protected abstract int orderLowerEstimation();
}
