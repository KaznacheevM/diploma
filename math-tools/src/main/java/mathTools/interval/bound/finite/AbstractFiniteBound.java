package mathTools.interval.bound.finite;

import mathTools.interval.bound.Bound;

import java.util.Objects;

/**
 * A base implementation for finite bounds of an interval.
 *
 * <p>This class provides a consistent implementation for finite bounds by ensuring:
 * <ul>
 *     <li>{@link #isInfinite()} always returns {@code false},</li>
 *     <li>{@link #isFinite()} always returns {@code true}.</li>
 * </ul>
 *
 * <p>Subclasses are only required to implement the {@link #getValue()} method
 * to provide the numerical value of the bound. This class handles comparison
 * logic and ensures compatibility with both finite and infinite bounds.
 *
 * @param <T> the type of the numerical value of the bound, must extend {@link Number}
 *            and implement {@link Comparable}.
 * @see Bound
 */
public abstract class AbstractFiniteBound<T extends Number & Comparable<T>> implements Bound<T> {

    /**
     * Always returns {@code false}, as this class represents finite bounds.
     *
     * @return {@code false}, indicating that the bound is not infinite.
     */
    @Override
    public final boolean isInfinite() {
        return false;
    }

    /**
     * Always returns {@code true}, as this class represents finite bounds.
     *
     * @return {@code true}, indicating that the bound is finite.
     */
    @Override
    public final boolean isFinite() {
        return true;
    }

    /**
     * Compares this finite bound to another bound.
     *
     * <p>The comparison logic ensures compatibility with both finite and infinite bounds:
     * <ul>
     *     <li>If the specified bound is infinite, the comparison is delegated to the
     *     infinite bound by calling {@code -bound.compareTo(this)}.</li>
     *     <li>If the specified bound is finite, the comparison is performed using
     *     the natural ordering of the numerical values.</li>
     * </ul>
     *
     * @param bound the bound to compare to, must not be {@code null}.
     * @return a negative integer, zero, or a positive integer as this bound is less than,
     *         equal to, or greater than the specified bound.
     * @throws NullPointerException if the specified bound is {@code null}.
     * @see Comparable#compareTo(Object)
     */
    @Override
    public final int compareTo(Bound<T> bound) {
        Objects.requireNonNull(bound, "Bound cannot be null");

        if (bound.isInfinite()) {
            return -bound.compareTo(this);
        }

        return compareFiniteBounds(bound);
    }

    /**
     * Compares the numerical values of this finite bound with another finite bound.
     *
     * <p>This method assumes that the specified bound is finite. The caller must
     * ensure this precondition before invoking the method.
     *
     * @param bound the finite bound to compare with, must not be {@code null}.
     * @return a negative integer, zero, or a positive integer as this bound's value
     *         is less than, equal to, or greater than the specified bound's value.
     */
    private int compareFiniteBounds(Bound<T> bound) {
        T thisValue = getValue();
        T boundValue = bound.getValue();
        return thisValue.compareTo(boundValue);
    }

    /**
     * Returns a string representation of this finite bound.
     *
     * <p>This implementation relies on the {@link #getValue()} method to obtain
     * the numerical value of the bound and calls {@code toString()} on it.
     *
     * @return the string representation of the value of this finite bound.
     */
    @Override
    public String toString() {
        return getValue().toString();
    }
}
