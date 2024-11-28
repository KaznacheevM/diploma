package toolbox.search.unspecific;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * An abstract implementation of {@link BoundedFinder} for finding threshold elements
 * within a bounded range based on a strictly monotonic predicate.
 * <p>
 * The {@code ThresholdFinder} class searches for the largest or smallest element in
 * a range {@code [lowerBound, upperBound]} where a strictly monotonic predicate changes
 * its result from {@code false} to {@code true}. The predicate is considered to be
 * strictly increasing or decreasing based on the {@code increasing} flag:
 * </p>
 * <ul>
 *     <li>If {@code increasing} is {@code true}, the predicate is {@code false} for smaller
 *     values and becomes {@code true} for larger values.</li>
 *     <li>If {@code increasing} is {@code false}, the predicate is {@code true} for smaller
 *     values and becomes {@code false} for larger values.</li>
 * </ul>
 *
 * <h2>Core Features</h2>
 * <ul>
 *     <li>Supports strictly monotonic predicates for defining search criteria.</li>
 *     <li>Handles both increasing and decreasing predicate cases.</li>
 *     <li>Abstract methods for midpoint calculation and boundary adjustments.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * ThresholdFinder<Integer> finder = new ThresholdFinder<>(x -> x > 5, true) {
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
 * Optional<Integer> result = finder.find(1, 10);
 * result.ifPresent(System.out::println); // Output: 6
 * }</pre>
 *
 * @param <T> the type of elements to search, which must be {@link Comparable}
 */
public abstract class ThresholdFinder<T extends Comparable<T>> implements BoundedFinder<T> {

    /**
     * The strictly monotonic predicate defining the search condition.
     */
    protected final Predicate<T> predicate;

    /**
     * Indicates whether the predicate is increasing or decreasing.
     */
    protected final boolean increasing;

    /**
     * Constructs a new {@code ThresholdFinder}.
     *
     * @param predicate the strictly monotonic predicate defining the search condition
     * @param increasing {@code true} if the predicate is increasing,
     *                   {@code false} if the predicate is decreasing
     * @throws NullPointerException if the predicate is {@code null}
     */
    public ThresholdFinder(Predicate<T> predicate, boolean increasing) {
        Objects.requireNonNull(predicate, "Predicate cannot be null");

        this.predicate = predicate;
        this.increasing = increasing;
    }

    /**
     * Finds the threshold element within the specified range.
     * <p>
     * The search uses a binary-like algorithm to find the point where the strictly monotonic
     * predicate changes its result from {@code false} to {@code true}. The algorithm ensures
     * correctness based on the assumption that the predicate is strictly monotonic.
     * </p>
     *
     * @param lowerBound the lower bound of the range
     * @param upperBound the upper bound of the range
     * @return an {@link Optional} containing the threshold element, or empty if no element satisfies the predicate
     * @throws IllegalArgumentException if {@code lowerBound} is greater than {@code upperBound}
     * @throws NullPointerException if either bound is {@code null}
     */
    @Override
    public final Optional<T> find(T lowerBound, T upperBound) {
        validateBounds(lowerBound, upperBound);

        if (lowerBound.compareTo(upperBound) > 0) {
            throw new IllegalArgumentException("Lower bound must not be greater than upper bound");
        }

        T result = null;
        while (lowerBound.compareTo(upperBound) <= 0) {
            T midPoint = computeMidPoint(lowerBound, upperBound);

            boolean testResult = predicate.test(midPoint);
            if (testResult) {
                result = midPoint;
            }

            if ((testResult && increasing) || (!testResult && !increasing)) {
                upperBound = decrement(midPoint);
            } else {
                lowerBound = increment(midPoint);
            }
        }

        return result == null ? Optional.empty() : Optional.of(result);
    }

    /**
     * Computes the midpoint of the given range.
     * <p>
     * This method must be implemented by subclasses to define how the midpoint
     * is calculated for the specific type {@code T}.
     * </p>
     *
     * @param lowerBound the lower bound of the range
     * @param upperBound the upper bound of the range
     * @return the midpoint of the range
     */
    protected abstract T computeMidPoint(T lowerBound, T upperBound);

    /**
     * Decrements the given value to adjust the upper bound.
     * <p>
     * This method must be implemented by subclasses to define how the upper bound
     * is decremented for the specific type {@code T}.
     * </p>
     *
     * @param midPoint the value to decrement
     * @return the decremented value
     */
    protected abstract T decrement(T midPoint);

    /**
     * Increments the given value to adjust the lower bound.
     * <p>
     * This method must be implemented by subclasses to define how the lower bound
     * is incremented for the specific type {@code T}.
     * </p>
     *
     * @param midPoint the value to increment
     * @return the incremented value
     */
    protected abstract T increment(T midPoint);

    /**
     * Validates that the given bounds are not null.
     *
     * @param lowerBound the lower bound to validate
     * @param upperBound the upper bound to validate
     * @throws NullPointerException if either bound is {@code null}
     */
    private static void validateBounds(Object lowerBound, Object upperBound) {
        Objects.requireNonNull(lowerBound, "Lower bound cannot be null");
        Objects.requireNonNull(upperBound, "Upper bound cannot be null");
    }
}
