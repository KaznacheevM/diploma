package toolbox.search.unspecific.semiBounded;

import java.util.Optional;

/**
 * An interface for finding elements starting from a single boundary.
 * <p>
 * The {@code SemiBoundedFinder} interface defines a contract for searching
 * elements when only one boundary is provided. The starting point of the search
 * is specified, and the logic for determining how to proceed with the search
 * is defined by the implementation.
 * </p>
 *
 * <h2>Core Features</h2>
 * <ul>
 *     <li>Supports searches starting from a single boundary.</li>
 *     <li>Allows for flexible implementation of search logic.</li>
 *     <li>Returns an {@link Optional} to handle cases where no result is found.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * SemiBoundedFinder<Integer> finder = startingPoint -> {
 *     // Example: find the first multiple of 5 starting from the given point
 *     return Optional.of(startingPoint + (5 - startingPoint % 5));
 * };
 *
 * Optional<Integer> result = finder.find(12);
 * result.ifPresent(System.out::println); // Output: 15
 * }</pre>
 *
 * @param <T> the type of elements to search, which must be {@link Comparable}
 */
public interface SemiBoundedFinder<T extends Comparable<T>> {

    /**
     * Finds an element starting from the specified point.
     * <p>
     * This method initiates a search starting from the provided {@code startingPoint}.
     * The exact logic for searching and selecting elements is defined by the implementation.
     * </p>
     *
     * @param startingPoint the starting point for the search
     * @return an {@link Optional} containing the found element, or empty if no element satisfies the criteria
     */
    Optional<T> find(T startingPoint);
}
