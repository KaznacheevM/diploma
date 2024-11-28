/**
 * Provides utilities for lazy initialization and resource management.
 * <p>
 * This package includes interfaces and classes designed to simplify
 * the lazy initialization of resources. Lazy initialization allows
 * resources to be created only when they are first accessed, improving
 * performance and reducing unnecessary resource usage.
 * </p>
 *
 * <h2>Core Components</h2>
 * <ul>
 *     <li>{@link toolbox.lazy.LazyHolder} — an interface for managing
 *     lazily initialized resources.</li>
 *     <li>{@link toolbox.lazy.concurrency.ConcurrentLazyHolder} — a thread-safe
 *     implementation of {@code LazyHolder} for multi-threaded environments.</li>
 * </ul>
 *
 * <h2>Key Features</h2>
 * <ul>
 *     <li>Lazy initialization: Resources are created only when needed.</li>
 *     <li>Reset capabilities: Allows invalidating and reinitializing resources.</li>
 *     <li>Thread safety: Ensures proper behavior in concurrent environments
 *     (provided by sub-packages).</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * // Create a LazyHolder instance with a custom initializer
 * LazyHolder<MyResource> lazyHolder = new ConcurrentLazyHolder<>(() -> new MyResource());
 *
 * // Retrieve the lazily initialized resource
 * MyResource resource = lazyHolder.getResource();
 *
 * // Reset the resource
 * lazyHolder.reset();
 * }</pre>
 *
 * <h2>Sub-Packages</h2>
 * <ul>
 *     <li>{@link xyz.derivora.toolbox.lazy.concurrency} — thread-safe implementations of lazy initialization utilities.</li>
 * </ul>
 *
 * @see toolbox.lazy.LazyHolder
 * @see toolbox.lazy.concurrency.ConcurrentLazyHolder
 */
package toolbox.lazy;