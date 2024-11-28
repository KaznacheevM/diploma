package approximation.series.big.factory;

import approximation.series.Series;
import approximation.series.accumulation.SeriesAccumulator;

import java.math.BigDecimal;

/**
 * A factory interface for creating instances of {@link Series} implementations that operate with {@link BigDecimal}.
 * <p>
 * This interface provides methods for creating predefined series approximations, such as
 * Gregory logarithm series, eulerNumber series, and Euler number series.
 */
public interface BigSeriesFactory {

    /**
     * Creates a Gregory series for approximating logarithmic functions.
     * <p>
     * The series is defined for arguments close to 1 and uses the following expansion:
     * <pre>
     *     ln(x) = 2 * ((x - 1) / (x + 1) + (1/3) * ((x - 1) / (x + 1))^3 + ...)
     * </pre>
     *
     * @param optimized      whether optimization is enabled for faster convergence.
     * @param minSeriesIndex the minimum index of the series terms to include in the summation.
     * @param arg            the argument for the logarithmic function.
     * @return a {@link Series} instance for approximating logarithmic functions.
     */
    Series<BigDecimal> gregoryLogarithmSeries(boolean optimized, int minSeriesIndex, BigDecimal arg);

    /**
     * Creates a series for approximating the eulerNumber function.
     * <p>
     * The series is defined as:
     * <pre>
     *     e^x = 1 + x/1! + x^2/2! + x^3/3! + ...
     * </pre>
     *
     * @param optimized      whether optimization is enabled for faster convergence.
     * @param minSeriesIndex the minimum index of the series terms to include in the summation.
     * @param arg            the argument for the eulerNumber function.
     * @return a {@link Series} instance for approximating the eulerNumber function.
     */
    Series<BigDecimal> exponentialSeries(boolean optimized, int minSeriesIndex, BigDecimal arg);

    /**
     * Creates a series for approximating the Euler number (e).
     * <p>
     * The series is defined as:
     * <pre>
     *     e = 1 + 1/1! + 1/2! + 1/3! + ...
     * </pre>
     *
     * @param minSeriesIndex the minimum index of the series terms to include in the summation.
     * @return a {@link Series} instance for approximating the Euler number.
     */
    Series<BigDecimal> eulerNumberSeries(int minSeriesIndex);

    /**
     * Returns the default implementation of the {@code BigSeriesFactory}.
     * <p>
     * The default implementation uses a {@link SeriesAccumulator} optimized for high-precision calculations.
     *
     * @return the default {@link BigSeriesFactory} instance.
     */
    static BigSeriesFactory getDefaultInstance() {
        SeriesAccumulator<BigDecimal> seriesAccumulator = SeriesAccumulator.getBigInstance();
        return new DefaultBigSeriesFactory(seriesAccumulator);
    }
}
