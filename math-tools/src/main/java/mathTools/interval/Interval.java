package mathTools.interval;

import mathTools.interval.bound.Bound;

/**
 * Represents an interval defined by its lower and upper bounds.
 *
 * <p>This interface provides methods for retrieving the bounds and type of the interval,
 * as well as checking whether a given number lies inside, to the left of, or to the right
 * of the interval.
 *
 * @param <T> the type of the numerical value of the bounds, must extend {@link Number}
 *            and implement {@link Comparable}.
 * @see IntervalType
 */
public interface Interval<T extends Number & Comparable<T>> {

    /**
     * Returns the type of this interval (e.g., open, closed, half-open).
     *
     * @return the type of this interval.
     */
    IntervalType getType();

    /**
     * Returns the lower bound of this interval.
     *
     * @return the lower bound of this interval, never {@code null}.
     */
    Bound<T> getLowerBound();

    /**
     * Returns the upper bound of this interval.
     *
     * @return the upper bound of this interval, never {@code null}.
     */
    Bound<T> getUpperBound();

    /**
     * Checks whether the given number lies within this interval.
     * The behavior depends on the type of the interval.
     *
     * @param number the number to check, must not be {@code null}.
     * @return {@code true} if the number lies within this interval, {@code false} otherwise.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    boolean isInInterval(T number);

    /**
     * Checks whether the given number lies strictly to the left of this interval.
     *
     * @param number the number to check, must not be {@code null}.
     * @return {@code true} if the number is less than the lower bound, {@code false} otherwise.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    boolean isLeftOfInterval(T number);

    /**
     * Checks whether the given number lies strictly to the right of this interval.
     *
     * @param number the number to check, must not be {@code null}.
     * @return {@code true} if the number is greater than the upper bound, {@code false} otherwise.
     * @throws NullPointerException if {@code number} is {@code null}.
     */
    boolean isRightOfInterval(T number);
}
