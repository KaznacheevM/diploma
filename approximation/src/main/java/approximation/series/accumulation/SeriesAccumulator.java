package approximation.series.accumulation;

import approximation.term.SeriesTerm;

import java.math.BigDecimal;

/**
 * A functional interface for accumulating the sum of terms in a series.
 * <p>
 * This interface defines a single method for summing terms in a series based on
 * the starting index, the number of terms to include, and the accuracy of each term.
 * Implementations can define specific accumulation strategies, such as iterative or parallel summation.
 *
 * @param <T> the numerical type of the accumulated result, such as {@link BigDecimal} or {@link Double}.
 */
@FunctionalInterface
public interface SeriesAccumulator<T extends Number> {

    /**
     * Accumulates the sum of terms in a series.
     *
     * @param minSeriesIndex the minimum index of the series terms to include in the summation.
     * @param termsCount     the number of terms to include in the summation.
     * @param termAccuracy   the accuracy required for each term in the summation.
     * @param seriesTerm     the {@link SeriesTerm} used to compute individual terms.
     * @return the accumulated sum as an instance of {@code T}.
     */
    T accumulate(int minSeriesIndex, int termsCount, int termAccuracy, SeriesTerm<T> seriesTerm);

    /**
     * Returns an instance of {@code SeriesAccumulator} for high-precision calculations using {@link BigDecimal}.
     *
     * @return a {@link SeriesAccumulator} implementation for {@link BigDecimal}.
     */
    static SeriesAccumulator<BigDecimal> getBigInstance() {
        return new BigSeriesAccumulator();
    }
}
