package approximation.commons.big;

import approximation.series.big.factory.BigSeriesFactory;
import approximation.AbstractApproximator;
import approximation.Approximator;
import mathTools.accuracy.AccuracyStrategy;
import mathTools.order.OrderComputer;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * An implementation of {@link Approximator} for computing the Euler number (e) with high precision.
 * <p>
 * This class delegates the approximation to a series-based implementation provided by a {@link BigSeriesFactory}.
 * The accuracy is managed using a positional accuracy strategy.
 */
public class EulerNumber extends AbstractApproximator<BigDecimal> {

    /**
     * The minimum index of the series terms to include in the summation.
     */
    private static final int MIN_SERIES_INDEX = 0;

    /**
     * The approximator for the Euler number series.
     */
    private final Approximator<BigDecimal> eulerNumberSeries;

    /**
     * Constructs an {@code EulerNumber} instance using the specified series factory.
     *
     * @param seriesFactory the {@link BigSeriesFactory} used to create the series for approximating the Euler number.
     * @throws NullPointerException if {@code seriesFactory} is {@code null}.
     */
    public EulerNumber(BigSeriesFactory seriesFactory) {
        super(AccuracyStrategy.POSITIONAL);
        eulerNumberSeries = seriesFactory.eulerNumberSeries(MIN_SERIES_INDEX);
    }

    /**
     * Approximates the Euler number (e) with the specified accuracy and rounding mode.
     *
     * @param accuracy      the positional accuracy for the approximation.
     * @param roundingMode  the {@link RoundingMode} to apply to the final result.
     * @return the approximated Euler number as a {@link BigDecimal}.
     */
    @Override
    public BigDecimal approximate(int accuracy, RoundingMode roundingMode) {
        return eulerNumberSeries.approximate(accuracy, roundingMode);
    }

    /**
     * Returns an {@link OrderComputer} for the Euler number.
     * <p>
     * The Euler number has a fixed order of 0.
     *
     * @return an {@link OrderComputer} that always returns 0.
     */
    @Override
    public OrderComputer getOrderComputer() {
        return () -> 0;
    }
}
