/**
 * Provides thread-safe implementations of lazy initialization utilities.
 * <p>
 * This package contains classes for managing resources in multi-threaded environments
 * using lazy initialization. It ensures proper synchronization to avoid race conditions
 * during resource creation and provides mechanisms for resetting and reinitializing resources.
 * </p>
 *
 * <h2>Core Class</h2>
 * <ul>
 *     <li>{@link toolbox.lazy.concurrency.ConcurrentLazyHolder} — a thread-safe
 *     implementation of {@link toolbox.lazy.LazyHolder}.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * LazyHolder<MyResource> lazyHolder = new ConcurrentLazyHolder<>(() -> new MyResource());
 *
 * MyResource resource = lazyHolder.getResource();
 *
 * lazyHolder.reset();
 * }</pre>
 *
 * @see toolbox.lazy.LazyHolder
 */
package toolbox.lazy.concurrency;