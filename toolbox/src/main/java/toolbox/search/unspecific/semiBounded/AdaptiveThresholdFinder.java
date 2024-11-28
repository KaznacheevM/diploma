package toolbox.search.unspecific.semiBounded;

import toolbox.search.unspecific.ThresholdFinder;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * An implementation of {@link SemiBoundedFinder} that dynamically adapts search boundaries
 * before delegating the search to a {@link ThresholdFinder}.
 * <p>
 * The {@code AdaptiveThresholdFinder} class extends the functionality of {@link SemiBoundedFinder}
 * by dynamically computing search boundaries starting from a single point. Once the boundaries
 * are established, the actual search is delegated to the provided {@link ThresholdFinder}.
 * </p>
 *
 * <h2>Core Features</h2>
 * <ul>
 *     <li>Dynamically computes search boundaries based on a predicate.</li>
 *     <li>Handles strictly monotonic predicates (increasing or decreasing).</li>
 *     <li>Delegates the final search logic to a {@link ThresholdFinder} instance.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * ThresholdFinder<Integer> thresholdFinder = new ThresholdFinder<>(x -> x > 10, true) {
 *     @Override
 *     protected Integer computeMidPoint(Integer lower, Integer upper) {
 *         return lower + (upper - lower) / 2;
 *     }
 *
 *     @Override
 *     protected Integer decrement(Integer value) {
 *         return value - 1;
 *     }
 *
 *     @Override
 *     protected Integer increment(Integer value) {
 *         return value + 1;
 *     }
 * };
 *
 * AdaptiveThresholdFinder<Integer> adaptiveFinder = new AdaptiveThresholdFinder<>(
 *     x -> x > 0,
 *     true,
 *     thresholdFinder
 * ) {
 *     @Override
 *     protected Integer computeEndingPoint(Integer endBound) {
 *         return endBound + 10; // Example logic to compute the next boundary
 *     }
 * };
 *
 * Optional<Integer> result = adaptiveFinder.find(1);
 * result.ifPresent(System.out::println); // Output depends on the predicate and logic
 * }</pre>
 *
 * @param <T> the type of elements to search, which must be {@link Comparable}
 */
public abstract class AdaptiveThresholdFinder<T extends Comparable<T>> implements SemiBoundedFinder<T> {

    /**
     * The strictly monotonic predicate defining the search condition.
     */
    protected final Predicate<T> predicate;

    /**
     * Indicates whether the predicate is increasing or decreasing.
     */
    protected final boolean increasing;

    /**
     * The threshold finder responsible for performing the final search.
     */
    protected final ThresholdFinder<T> thresholdFinder;

    /**
     * Constructs a new {@code AdaptiveThresholdFinder}.
     *
     * @param predicate       the strictly monotonic predicate defining the search condition
     * @param increasing      {@code true} if the predicate is increasing,
     *                        {@code false} if the predicate is decreasing
     * @param thresholdFinder the {@link ThresholdFinder} used to perform the final search
     * @throws NullPointerException if any of the parameters is {@code null}
     */
    public AdaptiveThresholdFinder(Predicate<T> predicate, boolean increasing, ThresholdFinder<T> thresholdFinder) {
        Objects.requireNonNull(predicate, "Predicate cannot be null");
        Objects.requireNonNull(thresholdFinder, "Threshold finder cannot be null");

        this.predicate = predicate;
        this.increasing = increasing;
        this.thresholdFinder = thresholdFinder;
    }

    /**
     * Finds an element starting from the specified point.
     * <p>
     * This method starts the search from the provided {@code startingPoint} and dynamically
     * computes the search boundaries until the predicate condition is satisfied. Once the
     * boundaries are determined, the method delegates the actual search to the provided
     * {@link ThresholdFinder}.
     * </p>
     *
     * @param startingPoint the starting point for the search
     * @return an {@link Optional} containing the found element, or empty if no element satisfies the criteria
     * @throws NullPointerException if the {@code startingPoint} is {@code null}
     */
    @Override
    public Optional<T> find(T startingPoint) {
        Objects.requireNonNull(startingPoint, "Starting point cannot be null");

        T endingPoint = computeEndingPoint(startingPoint);

        while (!predicate.test(endingPoint)) {
            startingPoint = endingPoint;
            endingPoint = computeEndingPoint(endingPoint);
        }

        return thresholdFinder.find(
                increasing ? startingPoint : endingPoint,
                increasing ? endingPoint : startingPoint
        );
    }

    /**
     * Computes the next boundary for the search.
     * <p>
     * This method must be implemented by subclasses to define how the next boundary
     * is calculated based on the current boundary.
     * </p>
     *
     * @param previous the current boundary
     * @return the next boundary
     */
    protected abstract T computeEndingPoint(T previous);
}
