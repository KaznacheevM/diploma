package toolbox.search.unspecific;

import java.util.Optional;

/**
 * A functional interface for finding an element within a bounded range.
 * <p>
 * The {@code BoundedFinder} interface defines a contract for searching
 * within a specified range of elements, where the target element is not
 * explicitly known beforehand. The implementation determines the logic
 * for selecting the element from the range {@code lowerBound} to {@code upperBound}.
 * </p>
 *
 * <h2>Core Features</h2>
 * <ul>
 *     <li>Works with any type {@code T} that implements {@link Comparable}.</li>
 *     <li>Supports inclusive bounds for the range.</li>
 *     <li>Returns an {@link Optional} containing the selected element or empty if no valid element is found.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * BoundedFinder<Integer> finder = (lower, upper) -> {
 *     if (lower > upper) {
 *         return Optional.empty();
 *     }
 *     // Example logic: return the midpoint
 *     return Optional.of(lower + (upper - lower) / 2);
 * };
 *
 * Optional<Integer> result = finder.find(1, 10);
 * result.ifPresent(System.out::println); // Prints 5
 * }</pre>
 *
 * @param <T> the type of elements to search, which must be {@link Comparable}
 */
@FunctionalInterface
public interface BoundedFinder<T extends Comparable<T>> {

    /**
     * Finds an element within the specified range.
     * <p>
     * The range is inclusive, meaning the bounds {@code lowerBound} and
     * {@code upperBound} are considered valid candidates for selection.
     * The exact logic for selecting the element is implementation-defined.
     * </p>
     *
     * @param lowerBound the lower bound of the range
     * @param upperBound the upper bound of the range
     * @return an {@link Optional} containing the selected element, or
     *         an empty {@link Optional} if no valid element is found
     */
    Optional<T> find(T lowerBound, T upperBound);
}
