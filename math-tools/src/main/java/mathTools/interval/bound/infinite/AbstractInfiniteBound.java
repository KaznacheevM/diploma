package mathTools.interval.bound.infinite;

import mathTools.interval.bound.Bound;

import java.util.Objects;

/**
 * A base implementation for infinite bounds of an interval.
 *
 * <p>This abstract class provides core functionality for infinite bounds, which represent
 * either positive or negative infinity. It defines consistent behavior for comparing infinite
 * bounds with both finite and infinite bounds and ensures logical consistency across methods.
 *
 * <p>Subclasses must implement the {@link #isPositive()} method to indicate whether the bound
 * represents positive or negative infinity. This class provides a default implementation of
 * {@link #isNegative()}, which returns the opposite of {@link #isPositive()}.
 *
 * <p>Important characteristics of infinite bounds:
 * <ul>
 *     <li>They do not have a numerical value. The {@link #getValue()} method is unsupported and
 *     throws {@link UnsupportedOperationException}.</li>
 *     <li>They are always considered infinite, as indicated by {@link #isInfinite()}.</li>
 *     <li>They cannot be finite, as indicated by {@link #isFinite()}.</li>
 * </ul>
 *
 * <p>Usage example:
 * <pre>{@code
 * class PositiveInfinity<T extends Number & Comparable<T>> extends AbstractInfiniteBound<T> {
 *     @Override
 *     public boolean isPositive() {
 *         return true;
 *     }
 * }
 *
 * class NegativeInfinity<T extends Number & Comparable<T>> extends AbstractInfiniteBound<T> {
 *     @Override
 *     public boolean isPositive() {
 *         return false;
 *     }
 * }
 * }</pre>
 *
 * @param <T> the type of the numerical value of the bound, must extend {@link Number}
 *            and implement {@link Comparable}.
 */
public abstract class AbstractInfiniteBound<T extends Number & Comparable<T>> implements InfiniteBound<T> {

    /**
     * Indicates whether this infinite bound is negative.
     *
     * <p>This method delegates to {@link #isPositive()} and returns the opposite value.
     *
     * @return {@code true} if this bound represents negative infinity, {@code false} otherwise.
     */
    @Override
    public final boolean isNegative() {
        return !isPositive();
    }

    /**
     * Returns the numerical value of this infinite bound.
     *
     * <p>This method is unsupported for infinite bounds and always throws
     * {@link UnsupportedOperationException}.
     *
     * @return never returns a value.
     * @throws UnsupportedOperationException because infinite bounds do not have a numerical value.
     */
    @Override
    public T getValue() {
        throw new UnsupportedOperationException("Infinity does not have a value.");
    }

    /**
     * Indicates that this bound is infinite.
     *
     * @return {@code true}, always.
     */
    @Override
    public final boolean isInfinite() {
        return true;
    }

    /**
     * Indicates that this bound is not finite.
     *
     * @return {@code false}, always.
     */
    @Override
    public final boolean isFinite() {
        return false;
    }

    /**
     * Compares this infinite bound to another bound.
     *
     * <p>The comparison logic:
     * <ul>
     *     <li>Positive infinity is always greater than any finite bound.</li>
     *     <li>Negative infinity is always less than any finite bound.</li>
     *     <li>If both bounds are infinite, their comparison depends on their direction:
     *         positive infinity is greater than negative infinity.</li>
     * </ul>
     *
     * <p>If the other bound is infinite but does not implement {@link InfiniteBound}, this method
     * throws an {@link IllegalArgumentException}.
     *
     * @param bound the bound to compare to, must not be {@code null}.
     * @return a negative integer, zero, or a positive integer as this bound is less than,
     *         equal to, or greater than the specified bound.
     * @throws NullPointerException if the specified bound is {@code null}.
     * @throws IllegalArgumentException if the specified bound is infinite but does not implement {@link InfiniteBound}.
     */
    @Override
    public final int compareTo(Bound<T> bound) {
        Objects.requireNonNull(bound, "Bound cannot be null");

        if (bound.isFinite()) {
            return compareWithFiniteBound(bound);
        }

        if (bound instanceof InfiniteBound<T> infiniteBound) {
            return compareWithInfiniteBound(infiniteBound);
        } else {
            throw new IllegalArgumentException("The specified bound is infinite but does not implement InfiniteBound: "
                    + bound.getClass().getName());
        }
    }

    /**
     * Compares this infinite bound to another infinite bound.
     *
     * <p>The comparison is based on the direction (positive or negative) of the bounds.
     *
     * @param infiniteBound the infinite bound to compare to.
     * @return a negative integer, zero, or a positive integer as this bound is less than,
     *         equal to, or greater than the specified bound.
     */
    private int compareWithInfiniteBound(InfiniteBound<T> infiniteBound) {
        return Boolean.compare(isPositive(), infiniteBound.isPositive());
    }

    /**
     * Compares this infinite bound to a finite bound.
     *
     * <p>Positive infinity is always greater than any finite bound, and negative infinity
     * is always less than any finite bound.
     *
     * @param bound the finite bound to compare to.
     * @return a positive integer if this bound is positive infinity,
     *         or a negative integer if this bound is negative infinity.
     */
    private int compareWithFiniteBound(Bound<T> bound) {
        return isPositive() ? 1 : -1;
    }

    /**
     * Returns a string representation of this infinite bound.
     *
     * <p>This implementation returns {@code "+infinity"} for positive infinity,
     * or {@code "-infinity"} for negative infinity. The result is a human-readable
     * representation of the bound's direction.
     *
     * @return {@code "+infinity"} for positive infinity, or {@code "-infinity"} for negative infinity.
     */
    @Override
    public String toString() {
        return isPositive() ? "+infinity" : "-infinity";
    }
}
