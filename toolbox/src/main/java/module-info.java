/**
 * Provides a collection of reusable tools and utilities for various programming tasks.
 * <p>
 * The {@code toolbox} module includes functionalities such as:
 * <ul>
 *     <li>Search algorithms and abstractions for bounded, semi-bounded, and dynamic searches.</li>
 *     <li>Validation utilities for argument checks and conditions.</li>
 *     <li>Lazy initialization tools for managing resources efficiently.</li>
 * </ul>
 *
 * <h2>Key Packages</h2>
 * <ul>
 *     <li>{@link toolbox.lazy} — for lazy initialization of resources.</li>
 *     <li>{@link toolbox.search.unspecific} — for implementing various search algorithms.</li>
 *     <li>{@link toolbox.validation} — for validating input arguments and conditions.</li>
 * </ul>
 */
module toolbox {
    exports toolbox.lazy;
    exports toolbox.lazy.concurrency;
    exports toolbox.search.unspecific;
    exports toolbox.search.unspecific.semiBounded;
    exports toolbox.validation;
}