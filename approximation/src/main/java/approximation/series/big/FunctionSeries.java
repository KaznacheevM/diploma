package approximation.series.big;

import approximation.series.accumulation.SeriesAccumulator;
import approximation.term.SeriesTerm;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A specialization of {@link BigSeries} for function-based series approximations.
 * <p>
 * This class represents series that depend on a specific argument, which is used
 * in the calculation of terms.
 */
public class FunctionSeries extends BigSeries {

    /**
     * The argument of the function for which the series is approximated.
     */
    protected final BigDecimal arg;

    /**
     * Constructs a {@code FunctionSeries} with the specified parameters.
     *
     * @param optimized        whether the optimization flag is enabled.
     * @param minSeriesIndex   the minimum index of the series terms to include in the summation.
     * @param seriesTerm       the {@link SeriesTerm} generator for computing individual terms.
     * @param seriesAccumulator the {@link SeriesAccumulator} responsible for accumulating the series terms.
     * @param arg              the argument of the function for which the series is approximated.
     * @throws NullPointerException if {@code arg} is {@code null}.
     */
    public FunctionSeries(boolean optimized,
                          int minSeriesIndex,
                          SeriesTerm<BigDecimal> seriesTerm,
                          SeriesAccumulator<BigDecimal> seriesAccumulator,
                          BigDecimal arg) {

        super(optimized, minSeriesIndex, seriesTerm, seriesAccumulator);

        Objects.requireNonNull(arg, "Argument cannot be null");
        this.arg = arg;
    }

    /**
     * Returns the argument of the function for which the series is approximated.
     *
     * @return the argument as a {@link BigDecimal}.
     */
    public final BigDecimal getArg() {
        return arg;
    }
}
