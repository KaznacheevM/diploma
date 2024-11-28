package approximation.series.big;

import approximation.order.big.BigOrderComputer;
import approximation.series.accumulation.SeriesAccumulator;
import approximation.term.SeriesTerm;
import approximation.series.Series;
import mathTools.order.OrderComputer;
import mathTools.order.OrderUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A high-precision implementation of {@link Series} for summing terms in a series using {@link BigDecimal}.
 * <p>
 * This class provides functionality for accumulating series terms with positional accuracy
 * and supports various rounding modes for the final approximation.
 */
public class BigSeries extends Series<BigDecimal> {

    /**
     * Constructs a {@code BigSeries} instance with the specified parameters.
     *
     * @param optimized        whether the optimization flag is enabled.
     * @param minSeriesIndex   the minimum index of the series terms to include in the summation.
     * @param seriesTerm       the {@link SeriesTerm} generator for computing individual terms.
     * @param seriesAccumulator the {@link SeriesAccumulator} responsible for accumulating the series terms.
     */
    public BigSeries(boolean optimized,
                     int minSeriesIndex,
                     SeriesTerm<BigDecimal> seriesTerm,
                     SeriesAccumulator<BigDecimal> seriesAccumulator) {

        super(optimized, minSeriesIndex, seriesTerm, seriesAccumulator);
    }

    /**
     * Approximates the sum of the series with the specified accuracy and rounding mode.
     *
     * @param accuracy      the positional accuracy for the final result.
     * @param roundingMode  the {@link RoundingMode} to apply to the final result.
     * @return the approximated sum as a {@link BigDecimal}.
     */
    @Override
    public final BigDecimal approximate(int accuracy, RoundingMode roundingMode) {
        int adjustedAccuracy = adjustAccuracy(accuracy);
        BigDecimal sum = accumulateSum(adjustedAccuracy);
        return sum.setScale(-accuracy, roundingMode);
    }

    /**
     * Computes the first term in the series up to the first significant digit,
     * using {@link RoundingMode#DOWN} for rounding.
     *
     * @return the first term of the series as a {@link BigDecimal}.
     */
    protected final BigDecimal firstTerm() {
        return seriesTerm.approximateMinimal(minSeriesIndex);
    }

    /**
     * Returns the {@link OrderComputer} associated with this series.
     *
     * @return a new {@link SeriesOrderComputer} for this series.
     */
    @Override
    public OrderComputer getOrderComputer() {
        return new SeriesOrderComputer(this);
    }

    /**
     * An implementation of {@link BigOrderComputer} for computing the order of the series.
     * <p>
     * This class provides an estimation of the lower order of the series based on the first term.
     */
    private static class SeriesOrderComputer extends BigOrderComputer {

        /**
         * Constructs a {@code SeriesOrderComputer} for the specified {@code BigSeries}.
         *
         * @param approximator the {@link BigSeries} instance for which the order is computed.
         */
        public SeriesOrderComputer(BigSeries approximator) {
            super(approximator);
        }

        /**
         * Estimates the lower bound of the order of the series.
         * <p>
         * The order is computed as the order of the first term minus 1.
         *
         * @return the estimated lower bound of the order as an integer.
         */
        @Override
        protected int orderLowerEstimation() {
            BigSeries series = (BigSeries) approximator;
            BigDecimal firstTerm = series.firstTerm();
            return OrderUtil.orderOf(firstTerm) - 1;
        }
    }
}
