/**
 * Provides abstractions and tools for implementing various search algorithms.
 * <p>
 * The {@code search} package serves as the root for different search strategies and utilities,
 * including bounded, semi-bounded, and adaptive search mechanisms. It organizes the search-related
 * functionality into logical groups, making it easier to navigate and understand the structure.
 * </p>
 *
 * <h2>Key Sub-Packages</h2>
 * <ul>
 *     <li>{@link xyz.derivora.toolbox.search.unspecific} — for searches where the target element is not predefined.</li>
 *     <li>{@link xyz.derivora.toolbox.search.unspecific.semiBounded} — for searches with one fixed boundary.</li>
 * </ul>
 *
 * <h2>Design Principles</h2>
 * <ul>
 *     <li>Modularity: Each sub-package is focused on a specific type of search.</li>
 *     <li>Flexibility: Interfaces allow users to implement custom search logic.</li>
 *     <li>Efficiency: Includes adaptive and exponential search strategies.</li>
 * </ul>
 *
 * @see xyz.derivora.toolbox.search.unspecific
 * @see xyz.derivora.toolbox.search.unspecific.semiBounded
 */
package toolbox.search;