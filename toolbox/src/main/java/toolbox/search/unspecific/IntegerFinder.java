package toolbox.search.unspecific;

import java.util.function.Predicate;

/**
 * A concrete implementation of {@link ThresholdFinder} for finding thresholds within an integer range.
 * <p>
 * The {@code IntegerFinder} class specializes {@link ThresholdFinder} to operate on {@link Integer} ranges.
 * It provides efficient calculations for the midpoint and adjusts the range boundaries
 * by incrementing or decrementing integers.
 * </p>
 *
 * <h2>Core Features</h2>
 * <ul>
 *     <li>Uses integer arithmetic for midpoint calculation.</li>
 *     <li>Efficient boundary adjustments with increment and decrement operations.</li>
 *     <li>Supports strictly monotonic predicates to define search criteria.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * IntegerFinder finder = new IntegerFinder(x -> x >= 5, true);
 *
 * Optional<Integer> result = finder.find(1, 10);
 * result.ifPresent(System.out::println); // Output: 5
 * }</pre>
 *
 * @see ThresholdFinder
 */
public final class IntegerFinder extends ThresholdFinder<Integer> {

    /**
     * Constructs a new {@code IntegerFinder} with the specified predicate and search direction.
     *
     * @param predicate  the strictly monotonic predicate defining the search condition
     * @param increasing {@code true} if the predicate is increasing,
     *                   {@code false} if the predicate is decreasing
     * @throws NullPointerException if the predicate is {@code null}
     */
    public IntegerFinder(Predicate<Integer> predicate, boolean increasing) {
        super(predicate, increasing);
    }

    /**
     * Computes the midpoint of the given integer range.
     * <p>
     * This implementation calculates the midpoint using integer arithmetic to avoid
     * potential overflow issues.
     * </p>
     *
     * @param lowerBound the lower bound of the range
     * @param upperBound the upper bound of the range
     * @return the midpoint of the range
     */
    @Override
    protected Integer computeMidPoint(Integer lowerBound, Integer upperBound) {
        return lowerBound + (upperBound - lowerBound) / 2;
    }

    /**
     * Decrements the given midpoint to adjust the upper bound.
     *
     * @param midPoint the value to decrement
     * @return the decremented value
     */
    @Override
    protected Integer decrement(Integer midPoint) {
        return midPoint - 1;
    }

    /**
     * Increments the given midpoint to adjust the lower bound.
     *
     * @param midPoint the value to increment
     * @return the incremented value
     */
    @Override
    protected Integer increment(Integer midPoint) {
        return midPoint + 1;
    }
}
