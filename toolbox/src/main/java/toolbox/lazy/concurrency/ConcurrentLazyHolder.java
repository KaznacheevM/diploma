package toolbox.lazy.concurrency;

import toolbox.lazy.LazyHolder;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * A thread-safe implementation of {@link LazyHolder}.
 * <p>
 * This class ensures proper lazy initialization in concurrent environments using
 * double-checked locking. It supports resource initialization via a user-provided
 * {@link Supplier} and provides methods for resetting the resource
 * if needed.
 * </p>
 *
 * <h2>Features</h2>
 * <ul>
 *     <li>Thread-safe lazy initialization using double-checked locking.</li>
 *     <li>Reset capability to reinitialize the resource.</li>
 *     <li>Customizable initialization logic using a {@code Supplier<T>}.</li>
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
 * @param <T> the type of the lazily initialized resource
 */
public final class ConcurrentLazyHolder<T> implements LazyHolder<T> {

    private final Supplier<T> initializer;

    private volatile T resource;

    /**
     * Constructs a new {@code ConcurrentLazyHolder} with the given initializer.
     *
     * @param initializer the supplier used to initialize the resource; must not be {@code null}
     * @throws NullPointerException if the provided {@code initializer} is {@code null}
     */
    public ConcurrentLazyHolder(Supplier<T> initializer) {
        this.initializer = Objects.requireNonNull(initializer, "Supplier cannot be null");
    }

    /**
     * Retrieves the resource, initializing it if necessary.
     * <p>
     * This method uses double-checked locking to ensure that the resource is initialized
     * only once, even in multi-threaded environments. Subsequent calls return the same instance.
     * </p>
     *
     * @return the lazily initialized resource
     * @throws IllegalStateException if the initializer throws an exception during resource creation
     */
    @Override
    public T getResource() {
        if (resource == null) {
            synchronized (this) {
                if (resource == null) {
                    try {
                        resource = initializer.get();
                    } catch (Exception e) {
                        throw new IllegalStateException("Failed to initialize resource", e);
                    }
                }
            }
        }

        return resource;
    }

    /**
     * Resets the current resource, allowing it to be reinitialized on the next access.
     * <p>
     * This method is thread-safe and ensures that the resource will be re-created
     * when {@link #getResource()} is next called.
     * </p>
     */
    @Override
    public void reset() {
        synchronized (this) {
            resource = null;
        }
    }

    /**
     * Resets the current resource and retrieves a newly initialized resource.
     * <p>
     * This method is thread-safe and combines the functionality of {@link #reset()}
     * and {@link #getResource()}, ensuring that the resource is invalidated and
     * re-initialized atomically.
     * </p>
     *
     * @return the newly initialized resource
     * @throws IllegalStateException if the initializer throws an exception during resource creation
     */
    @Override
    public T resetAndGet() {
        synchronized (this) {
            try {
                resource = initializer.get();
            } catch (Exception e) {
                resource = null; // Ensure the state is reset
                throw new IllegalStateException("Failed to reset and initialize resource", e);
            }
        }

        return resource;
    }
}
