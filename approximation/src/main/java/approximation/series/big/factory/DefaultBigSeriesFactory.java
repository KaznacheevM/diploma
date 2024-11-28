package approximation.series.big.factory;

import approximation.term.big.commons.ExponentialTerm;
import approximation.term.big.commons.GregoryLogarithmTerm;
import approximation.series.Series;
import approximation.series.accumulation.SeriesAccumulator;
import approximation.series.big.BigSeries;
import approximation.series.big.FunctionSeries;
import approximation.term.SeriesTerm;
import approximation.term.big.commons.EulerNumberTerm;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The default implementation of {@link BigSeriesFactory}.
 * <p>
 * This factory creates instances of {@link Series} for high-precision computations using {@link BigDecimal}.
 * It supports predefined series for logarithmic functions, eulerNumber functions, and the Euler number.
 */
class DefaultBigSeriesFactory implements BigSeriesFactory {

    /**
     * The {@link SeriesAccumulator} used for summing series terms.
     */
    private final SeriesAccumulator<BigDecimal> seriesAccumulator;

    /**
     * Constructs a {@code DefaultBigSeriesFactory} with the specified series accumulator.
     *
     * @param seriesAccumulator the {@link SeriesAccumulator} responsible for summing series terms.
     * @throws NullPointerException if {@code seriesAccumulator} is {@code null}.
     */
    DefaultBigSeriesFactory(SeriesAccumulator<BigDecimal> seriesAccumulator) {
        Objects.requireNonNull(seriesAccumulator, "Series Accumulator cannot be null");
        this.seriesAccumulator = seriesAccumulator;
    }

    /**
     * Creates a Gregory series for approximating logarithmic functions.
     *
     * @param optimized      whether optimization is enabled for faster convergence.
     * @param minSeriesIndex the minimum index of the series terms to include in the summation.
     * @param arg            the argument for the logarithmic function.
     * @return a {@link FunctionSeries} instance for approximating logarithmic functions.
     */
    @Override
    public Series<BigDecimal> gregoryLogarithmSeries(boolean optimized, int minSeriesIndex, BigDecimal arg) {
        SeriesTerm<BigDecimal> seriesTerm = new GregoryLogarithmTerm(arg);
        return new FunctionSeries(optimized, minSeriesIndex, seriesTerm, seriesAccumulator, arg);
    }

    /**
     * Creates a series for approximating the eulerNumber function.
     *
     * @param optimized      whether optimization is enabled for faster convergence.
     * @param minSeriesIndex the minimum index of the series terms to include in the summation.
     * @param arg            the argument for the eulerNumber function.
     * @return a {@link FunctionSeries} instance for approximating the eulerNumber function.
     */
    @Override
    public Series<BigDecimal> exponentialSeries(boolean optimized, int minSeriesIndex, BigDecimal arg) {
        SeriesTerm<BigDecimal> seriesTerm = new ExponentialTerm(arg);
        return new FunctionSeries(optimized, minSeriesIndex, seriesTerm, seriesAccumulator, arg);
    }

    /**
     * Creates a series for approximating the Euler number (e).
     *
     * @param minSeriesIndex the minimum index of the series terms to include in the summation.
     * @return a {@link BigSeries} instance for approximating the Euler number.
     */
    @Override
    public Series<BigDecimal> eulerNumberSeries(int minSeriesIndex) {
        SeriesTerm<BigDecimal> seriesTerm = new EulerNumberTerm();
        return new BigSeries(false, minSeriesIndex, seriesTerm, seriesAccumulator);
    }
}
