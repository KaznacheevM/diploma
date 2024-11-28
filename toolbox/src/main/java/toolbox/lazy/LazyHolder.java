package toolbox.lazy;

/**
 * An interface for lazily initializing and managing resources.
 * <p>
 * This interface provides methods for lazy initialization, retrieval, and resetting
 * of a resource, potentially improving performance by deferring resource creation
 * until it is actually needed.
 *
 * @param <T> the type of the resource
 */
public interface LazyHolder<T> {

    /**
     * Retrieves the resource, initializing it if necessary.
     * <p>
     * If the resource has not been initialized yet, this method will lazily create
     * and return it. Subsequent calls will return the same instance until {@link #reset()}
     * is invoked.
     *
     * @return the lazily initialized resource
     */
    T getResource();

    /**
     * Resets the current resource, allowing it to be reinitialized on the next access.
     * <p>
     * This method invalidates the existing resource, if any, and prepares the holder
     * to create a new resource during the next call to {@link #getResource()}.
     */
    void reset();

    /**
     * Resets the current resource and retrieves a newly initialized resource.
     * <p>
     * This method combines the functionality of {@link #reset()} and {@link #getResource()}
     * by invalidating the current resource and immediately returning a new instance.
     *
     * @return the newly initialized resource
     */
    T resetAndGet();
}
