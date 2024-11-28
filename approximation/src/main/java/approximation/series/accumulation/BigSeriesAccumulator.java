package approximation.series.accumulation;

import approximation.term.SeriesTerm;

import java.math.BigDecimal;
import java.util.concurrent.*;

/**
 * A high-precision implementation of {@link SeriesAccumulator} for summing terms in a series using {@link BigDecimal}.
 * <p>
 * This implementation utilizes multithreading to compute terms in parallel, leveraging available CPU cores
 * to optimize performance for large series computations.
 */
final class BigSeriesAccumulator implements SeriesAccumulator<BigDecimal> {

    /**
     * Accumulates the sum of terms in a series using multithreaded computation.
     *
     * @param minSeriesIndex the minimum index of the series terms to include in the summation.
     * @param termsCount     the number of terms to include in the summation.
     * @param termAccuracy   the accuracy required for each term in the summation.
     * @param seriesTerm     the {@link SeriesTerm} used to compute individual terms.
     * @return the accumulated sum as a {@link BigDecimal}.
     * @throws ArithmeticException if {@code termsCount} is negative or the computed greatest index overflows.
     * @throws RuntimeException    if an exception occurs during parallel computation of terms.
     */
    @Override
    public BigDecimal accumulate(int minSeriesIndex, int termsCount, int termAccuracy, SeriesTerm<BigDecimal> seriesTerm) {
        if (termsCount == 0) {
            return BigDecimal.ZERO;
        }

        if (termsCount < 0) {
            throw new ArithmeticException("Terms number can't be negative number");
        }

        int greatestIndex = findGreatestIndex(minSeriesIndex, termsCount);
        CompletionService<BigDecimal> completionService = executeComputations(minSeriesIndex,
                                                                              greatestIndex,
                                                                              termAccuracy,
                                                                              seriesTerm);

        return computeSum(termsCount, completionService);
    }

    /**
     * Executes computations for series terms in parallel using a fixed thread pool.
     *
     * @param minSeriesIndex the minimum index of the series terms to include.
     * @param greatestIndex  the greatest index of the series terms to include.
     * @param termScale      the accuracy required for each term.
     * @param seriesTerm     the {@link SeriesTerm} used to compute individual terms.
     * @return a {@link CompletionService} managing the parallel computations.
     */
    private CompletionService<BigDecimal> executeComputations(int minSeriesIndex, int greatestIndex, int termScale, SeriesTerm<BigDecimal> seriesTerm) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);
        CompletionService<BigDecimal> completionService = new ExecutorCompletionService<>(executor);

        for (int i = minSeriesIndex; i <= greatestIndex; i++) {
            final int termIndex = i;

            Callable<BigDecimal> termCallable = () -> seriesTerm.approximate(termIndex, termScale);

            completionService.submit(termCallable);
        }

        executor.shutdown();
        return completionService;
    }

    /**
     * Computes the total sum of terms by collecting results from the {@link CompletionService}.
     *
     * @param termsNumber       the number of terms to include in the summation.
     * @param completionService the {@link CompletionService} managing the completed tasks.
     * @return the accumulated sum as a {@link BigDecimal}.
     * @throws RuntimeException if an exception occurs while retrieving or summing term results.
     */
    private static BigDecimal computeSum(int termsNumber, CompletionService<BigDecimal> completionService) {
        BigDecimal sum = BigDecimal.ZERO;

        for (int i = 0; i < termsNumber; i++) {
            try {
                Future<BigDecimal> futureAugend = completionService.take();
                BigDecimal augend = futureAugend.get();
                sum = sum.add(augend);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Caught exception while accumulating terms", e);
            }
        }

        return sum;
    }

    /**
     * Finds the greatest index in the series based on the minimum index and the number of terms.
     *
     * @param minIndex    the minimum index of the series.
     * @param termsNumber the number of terms to include in the series.
     * @return the greatest index as an integer.
     * @throws ArithmeticException if the computed greatest index overflows {@link Integer#MAX_VALUE}.
     */
    private int findGreatestIndex(int minIndex, int termsNumber) {
        int greatestIndex;

        try {
            greatestIndex = Math.addExact(termsNumber - 1, minIndex);
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Greatest index is greater than Integer.MAX_VALUE");
        }

        return greatestIndex;
    }
}
