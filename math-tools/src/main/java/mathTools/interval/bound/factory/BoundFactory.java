package mathTools.interval.bound.factory;

import mathTools.interval.bound.Bound;
import mathTools.interval.bound.finite.FiniteBound;
import mathTools.interval.bound.infinite.DirectionalInfiniteBound;

/**
 * A factory interface for creating instances of {@link Bound}.
 *
 * <p>This interface provides methods for creating both finite and infinite bounds
 * for an interval. It abstracts the instantiation logic, allowing for consistent
 * creation of bounds across the application. The factory also includes a static
 * method to access a default implementation.
 *
 * <p>The default implementation is accessible via {@link #getDefaultInstance()} and
 * provides standard behaviors for creating {@link Bound} instances.
 *
 * <p>Usage example:
 * <pre>{@code
 * BoundFactory factory = BoundFactory.getDefaultInstance();
 * Bound<Integer> finiteBound = factory.createFiniteBound(10);
 * Bound<Double> positiveInfinity = factory.createPositiveInfinity();
 * Bound<Double> negativeInfinity = factory.createNegativeInfinity();
 * }</pre>
 */
public interface BoundFactory {

    /**
     * Creates a finite bound with the specified value.
     *
     * @param value the numerical value of the bound, must not be {@code null}.
     * @param <T> the type of the numerical value of the bound, must extend {@link Number}
     *           and implement {@link Comparable}.
     * @return a finite bound with the specified value.
     * @throws NullPointerException if {@code value} is {@code null}.
     */
    <T extends Number & Comparable<T>> Bound<T> createFinitBound(T value);

    /**
     * Creates a positive infinite bound.
     *
     * @param <T> the type of the numerical value of the bound, must extend {@link Number}
     *           and implement {@link Comparable}.
     * @return a positive infinite bound.
     */
    <T extends Number & Comparable<T>> Bound<T> createPositiveInfinity();

    /**
     * Creates a negative infinite bound.
     *
     * @param <T> the type of the numerical value of the bound, must extend {@link Number}
     *           and implement {@link Comparable}.
     * @return a negative infinite bound.
     */
    <T extends Number & Comparable<T>> Bound<T> createNegativeInfinity();

    /**
     * Returns the default implementation of the {@link BoundFactory}.
     *
     * <p>The default implementation uses standard classes such as {@link FiniteBound} and
     * {@link DirectionalInfiniteBound} to create bounds.
     *
     * @return the default bound factory instance.
     */
    static BoundFactory getDefaultInstance() {
        return new DefaultBoundFactory();
    }
}
