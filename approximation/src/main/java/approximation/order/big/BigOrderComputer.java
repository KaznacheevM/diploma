package approximation.order.big;

import approximation.order.ApproximationOrderComputer;
import approximation.Approximator;
import mathTools.order.OrderUtil;

import java.math.BigDecimal;

/**
 * An abstract implementation of {@link ApproximationOrderComputer} for high-precision approximations using {@link BigDecimal}.
 * <p>
 * This class computes the order of approximation by estimating the magnitude of the result
 * obtained from coarse approximation.
 */
public abstract class BigOrderComputer extends ApproximationOrderComputer<BigDecimal> {

    /**
     * Constructs a {@code BigOrderComputer} with the specified {@link Approximator}.
     *
     * @param approximator the {@link Approximator} used to compute coarse approximations.
     */
    public BigOrderComputer(Approximator<BigDecimal> approximator) {
        super(approximator, approximator.getAccuracyStrategy());
    }

    /**
     * Computes the order of approximation by determining the magnitude of the coarsely approximated result.
     * <p>
     * The order is calculated using {@link OrderUtil#orderOf(BigDecimal)}.
     *
     * @return the computed order as an integer.
     */
    @Override
    public final int compute() {
        BigDecimal approximation = approximateCoarsely();
        return OrderUtil.orderOf(approximation);
    }
}