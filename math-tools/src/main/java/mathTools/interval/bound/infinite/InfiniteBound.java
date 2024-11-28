package mathTools.interval.bound.infinite;

import mathTools.interval.bound.Bound;


/**
 * Represents an infinite bound of an interval.
 *
 * <p>This interface extends {@link Bound} and specializes it for infinite bounds, which
 * represent either positive or negative infinity. Infinite bounds are typically used to
 * define unbounded intervals or to compare extreme values.
 *
 * <p>Key features of infinite bounds:
 * <ul>
 *     <li>They do not have a finite numerical value.</li>
 *     <li>The {@link #isPositive()} and {@link #isNegative()} methods distinguish between
 *     positive and negative infinity.</li>
 * </ul>
 *
 * <p>Behavior of {@link #getValue()}:
 * <ul>
 *     <li>For most implementations, this method may return {@code null}, as infinite bounds
 *     typically do not have a numerical representation.</li>
 *     <li>Some implementations may provide a value for additional context, but this is not
 *     guaranteed.</li>
 *     <li>It is recommended to use {@link #isPositive()} or {@link #isNegative()} to
 *     determine the type of infinity instead of relying on {@code getValue()}.</li>
 * </ul>
 *
 * <p>Comparison rules for infinite bounds:
 * <ul>
 *     <li>Positive infinity is greater than all other bounds, including other positive infinities.</li>
 *     <li>Negative infinity is less than all other bounds, including other negative infinities.</li>
 * </ul>
 *
 * @param <T> the type of the numerical value of the bound, must extend {@link Number}
 *            and implement {@link Comparable}.
 * @see Bound
 */
public interface InfiniteBound<T extends Number & Comparable<T>> extends Bound<T> {

    /**
     * Indicates whether this infinite bound is positive.
     *
     * <p>If this method returns {@code true}, {@link #isNegative()} must return {@code false}.
     *
     * @return {@code true} if this bound represents positive infinity, {@code false} otherwise.
     */
    boolean isPositive();

    /**
     * Indicates whether this infinite bound is negative.
     *
     * <p>If this method returns {@code true}, {@link #isPositive()} must return {@code false}.
     *
     * @return {@code true} if this bound represents negative infinity, {@code false} otherwise.
     */
    boolean isNegative();
}
