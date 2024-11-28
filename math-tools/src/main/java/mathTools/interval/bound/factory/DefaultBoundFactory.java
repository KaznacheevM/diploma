package mathTools.interval.bound.factory;

import mathTools.interval.bound.Bound;
import mathTools.interval.bound.finite.FiniteBound;
import mathTools.interval.bound.infinite.DirectionalInfiniteBound;

/**
 * The default implementation of the {@link BoundFactory} interface.
 *
 * <p>This class provides concrete methods for creating both finite and infinite bounds
 * using standard implementations:
 * <ul>
 *     <li>{@link FiniteBound} for finite bounds.</li>
 *     <li>{@link DirectionalInfiniteBound} for positive and negative infinite bounds.</li>
 * </ul>
 *
 * <p>It is designed to be accessed exclusively through the {@link BoundFactory#getDefaultInstance()}
 * method. This ensures controlled access to the factory and enforces encapsulation of
 * the creation logic.
 *
 * <p>Note: This class has package-private visibility and is not intended to be used
 * directly outside its package.
 */
class DefaultBoundFactory implements BoundFactory {

    /**
     * Creates a finite bound with the specified value.
     *
     * @param value the numerical value of the bound, must not be {@code null}.
     * @param <T> the type of the numerical value of the bound, must extend {@link Number}
     *           and implement {@link Comparable}.
     * @return a finite bound with the specified value.
     * @throws NullPointerException if {@code value} is {@code null}.
     * @see FiniteBound
     */
    @Override
    public <T extends Number & Comparable<T>> Bound<T> createFinitBound(T value) {
        return new FiniteBound<>(value);
    }

    /**
     * Creates a positive infinite bound.
     *
     * @param <T> the type of the numerical value of the bound, must extend {@link Number}
     *           and implement {@link Comparable}.
     * @return a positive infinite bound.
     * @see DirectionalInfiniteBound
     */
    @Override
    public <T extends Number & Comparable<T>> Bound<T> createPositiveInfinity() {
        return new DirectionalInfiniteBound<>(true);
    }

    /**
     * Creates a negative infinite bound.
     *
     * @param <T> the type of the numerical value of the bound, must extend {@link Number}
     *           and implement {@link Comparable}.
     * @return a negative infinite bound.
     * @see DirectionalInfiniteBound
     */
    @Override
    public <T extends Number & Comparable<T>> Bound<T> createNegativeInfinity() {
        return new DirectionalInfiniteBound<>(false);
    }
}
