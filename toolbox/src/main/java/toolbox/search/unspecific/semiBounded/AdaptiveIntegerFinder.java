package toolbox.search.unspecific.semiBounded;

import toolbox.search.unspecific.IntegerFinder;

import java.util.function.Predicate;

/**
 * An implementation of {@link AdaptiveThresholdFinder} for searching within integer ranges.
 * <p>
 * The {@code AdaptiveIntegerFinder} dynamically calculates search boundaries for integers,
 * doubling or halving the range based on the predicate's nature and the direction of the search.
 * The search has an exponential nature, as each step increases or decreases the range size exponentially.
 * </p>
 *
 * <h2>Core Features</h2>
 * <ul>
 *     <li>Supports strictly monotonic predicates for integer ranges.</li>
 *     <li>Adapts search boundaries dynamically by doubling or halving values.</li>
 *     <li>Handles overflow scenarios by stopping the search and returning an empty result.</li>
 *     <li>Delegates the final search to an instance of {@link IntegerFinder}.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * AdaptiveIntegerFinder finder = new AdaptiveIntegerFinder(x -> x >= 10, true);
 *
 * Optional<Integer> result = finder.find(1);
 * result.ifPresent(System.out::println); // Output depends on the predicate and logic
 * }</pre>
 *
 * @see AdaptiveThresholdFinder
 * @see IntegerFinder
 */
public final class AdaptiveIntegerFinder extends AdaptiveThresholdFinder<Integer> {

    /**
     * Constructs a new {@code AdaptiveIntegerFinder}.
     *
     * @param predicate  the strictly monotonic predicate defining the search condition
     * @param increasing {@code true} if the predicate is increasing,
     *                   {@code false} if the predicate is decreasing
     * @throws NullPointerException if the predicate is {@code null}
     */
    public AdaptiveIntegerFinder(Predicate<Integer> predicate, boolean increasing) {
        super(predicate, increasing, new IntegerFinder(predicate, increasing));
    }

    /**
     * Dynamically computes the next boundary for the search.
     * <p>
     * The boundary is adjusted by either doubling or halving its value, depending
     * on the direction of the search and the sign of the current boundary. This method
     * handles overflow scenarios by stopping the search and returning an empty result.
     * </p>
     *
     * @param previous the current boundary
     * @return the next boundary
     * @throws ArithmeticException if overflow occurs during multiplication
     */
    @Override
    protected Integer computeEndingPoint(Integer previous) {
        if (previous == 0) {
            return increasing ? 1 : -1;
        }

        try {
            if (previous < 0) {
                return increasing
                        ? Math.ceilDiv(previous, 2)
                        : Math.multiplyExact(previous, 2);
            }
            return increasing
                    ? Math.multiplyExact(previous, 2)
                    : Math.floorDiv(previous, 2);
        } catch (ArithmeticException e) {
            // Stop search if overflow occurs
            throw new ArithmeticException("Overflow occurred during boundary calculation");
        }
    }
}
