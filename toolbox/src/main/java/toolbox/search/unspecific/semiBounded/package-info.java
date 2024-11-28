/**
 * Provides tools for searches with one fixed boundary.
 * <p>
 * The utilities in this package focus on searches that start from a single specified boundary
 * and dynamically calculate the range or determine the target element based on given logic.
 * This is particularly useful for scenarios such as exponential searches or searches
 * that extend boundaries iteratively.
 * </p>
 *
 * <h2>Core Components</h2>
 * <ul>
 *     <li>{@link toolbox.search.unspecific.semiBounded.SemiBoundedFinder} — an interface for searches
 *     starting from a single boundary.</li>
 *     <li>{@link toolbox.search.unspecific.semiBounded.AdaptiveThresholdFinder} — a flexible implementation
 *     that dynamically calculates ranges before delegating to a {@code ThresholdFinder}.</li>
 *     <li>{@link toolbox.search.unspecific.semiBounded.AdaptiveIntegerFinder} — a concrete implementation
 *     for integer ranges with exponential boundary adjustments.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * SemiBoundedFinder<Integer> finder = startingPoint -> {
 *     // Example logic: find the next power of 2 starting from the given point
 *     int value = 1;
 *     while (value < startingPoint) {
 *         value *= 2;
 *     }
 *     return Optional.of(value);
 * };
 *
 * Optional<Integer> result = finder.find(5);
 * result.ifPresent(System.out::println); // Output: 8
 * }</pre>
 */
package toolbox.search.unspecific.semiBounded;