package approximation.series;

import approximation.series.accumulation.SeriesAccumulator;
import approximation.term.SeriesTerm;
import approximation.AbstractApproximator;
import mathTools.accuracy.AccuracyStrategy;
import mathTools.order.OrderUtil;
import toolbox.search.unspecific.semiBounded.AdaptiveIntegerFinder;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * An abstract base class for approximations based on series summation.
 * <p>
 * This class handles the accumulation of series terms and the management of term accuracy,
 * allowing for flexible implementations of numerical approximations using series expansions.
 *
 * @param <T> the numerical type of the result, such as {@link java.math.BigDecimal} or {@link Double}.
 */
public abstract class Series<T extends Number> extends AbstractApproximator<T> {

    /**
     * A flag indicating whether the optimization is enabled.
     * <p>
     * When {@code true}, the convergence is accelerated by ensuring that the remainder term satisfies the condition:
     * <pre>
     *     |R_{n+1}| ≤ 1/10 * |R_n|
     * </pre>
     */
    protected final boolean isOptimized;

    /**
     * The minimum index of the series terms to be included in the summation.
     */
    protected final int minSeriesIndex;

    /**
     * The term generator used to compute individual terms in the series.
     */
    protected final SeriesTerm<T> seriesTerm;

    /**
     * The accumulator used to sum the series terms.
     */
    protected final SeriesAccumulator<T> seriesAccumulator;

    /**
     * Constructs a {@code Series} instance with the specified parameters.
     *
     * @param isOptimized       whether the optimization flag is enabled.
     * @param minSeriesIndex    the minimum index of the series terms to include in the summation.
     * @param seriesTerm        the {@link SeriesTerm} generator for computing individual terms.
     * @param seriesAccumulator the {@link SeriesAccumulator} responsible for accumulating the series terms.
     * @throws NullPointerException if {@code seriesTerm} or {@code seriesAccumulator} is {@code null}.
     */
    public Series(boolean isOptimized,
                  int minSeriesIndex,
                  SeriesTerm<T> seriesTerm,
                  SeriesAccumulator<T> seriesAccumulator) {

        super(AccuracyStrategy.POSITIONAL);

        Objects.requireNonNull(seriesTerm, "Series term cannot be null");
        Objects.requireNonNull(seriesAccumulator, "Series accumulator cannot be null");

        this.isOptimized = isOptimized;
        this.minSeriesIndex = minSeriesIndex;
        this.seriesTerm = seriesTerm;
        this.seriesAccumulator = seriesAccumulator;
    }

    /**
     * Indicates whether the optimization flag is enabled.
     *
     * @return {@code true} if optimization is enabled; {@code false} otherwise.
     */
    public final boolean isOptimized() {
        return isOptimized;
    }

    /**
     * Accumulates the sum of series terms based on the required number of terms and term accuracy.
     *
     * @param requiredTermsCount the number of terms to include in the summation.
     * @param termAccuracy       the accuracy required for each term.
     * @return the accumulated sum as an instance of {@code T}.
     */
    protected final T accumulateSum(int requiredTermsCount, int termAccuracy) {
        return seriesAccumulator.accumulate(minSeriesIndex, requiredTermsCount, termAccuracy, seriesTerm);
    }

    /**
     * Accumulates the sum of series terms based on the adjusted accuracy.
     *
     * @param adjustedAccuracy the adjusted accuracy for the summation.
     * @return the accumulated sum as an instance of {@code T}.
     */
    protected final T accumulateSum(int adjustedAccuracy) {
        int requiredTermsCount = this.requiredTermsCount(adjustedAccuracy);
        int termAccuracy = computeTermAccuracy(adjustedAccuracy, requiredTermsCount);
        return accumulateSum(requiredTermsCount, termAccuracy);
    }

    /**
     * Computes the accuracy required for each term in the series based on the total adjusted accuracy.
     *
     * @param adjustedAccuracy the total adjusted accuracy.
     * @param termsCount       the number of terms included in the summation.
     * @return the accuracy required for each term.
     */
    protected static int computeTermAccuracy(int adjustedAccuracy, int termsCount) {
        if (termsCount == 0) {
            return adjustedAccuracy;
        }
        return adjustedAccuracy - OrderUtil.overestimateOrderOf(termsCount);
    }

    /**
     * Determines the number of terms required to meet the specified accuracy.
     *
     * @param adjustedAccuracy the adjusted accuracy for the summation.
     * @return the number of terms required.
     */
    protected final int requiredTermsCount(int adjustedAccuracy) {
        Predicate<Integer> predicate = createNegligibleTermPredicate(adjustedAccuracy);

        // Finds the minimum index at which the terms become negligible and can be ignored,
        // meaning they no longer impact the required accuracy of the partial sum.
        AdaptiveIntegerFinder finder = new AdaptiveIntegerFinder(predicate, true);
        int minMatchingIndex = finder.find(minSeriesIndex).orElse(minSeriesIndex);

        return minMatchingIndex - minSeriesIndex;
    }

    /**
     * Creates a predicate to determine whether a term is negligible based on its order and the adjusted accuracy.
     *
     * @param adjustedAccuracy the adjusted accuracy for the summation.
     * @return a {@link Predicate} that checks if a term is negligible.
     */
    private Predicate<Integer> createNegligibleTermPredicate(int adjustedAccuracy) {
        if (isOptimized) {
            final int threshold = adjustedAccuracy - 1;
            return i -> seriesTerm.overestimateOrder(i) < threshold;
        }

        return i -> {
            int threshold = computeTermAccuracy(adjustedAccuracy, i - minSeriesIndex);
            return seriesTerm.overestimateOrder(i) < threshold;
        };
    }
}
