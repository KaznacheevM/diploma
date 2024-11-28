package mathTools.interval.bound;

import mathTools.interval.Interval;

/**
 * Represents a bound of an interval, which can be either finite or infinite.
 *
 * <p>This interface supports operations for distinguishing between finite and infinite bounds
 * while maintaining logical consistency:
 * <ul>
 *     <li>If {@link #isInfinite()} returns {@code true}, then {@link #isFinite()} must return {@code false}.</li>
 *     <li>If {@link #isFinite()} returns {@code true}, then {@link #isInfinite()} must return {@code false}.</li>
 * </ul>
 *
 * <p>For infinite bounds, calling {@link #getValue()} may throw an exception, as infinite bounds
 * do not have a numerical representation. Implementations should ensure type safety by providing
 * proper behavior for comparison operations.
 *
 * @param <T> the type of the numerical value of the bound, must extend {@link Number}
 *            and implement {@link Comparable}.
 * @see Interval
 */
public interface Bound<T extends Number & Comparable<T>> extends Comparable<Bound<T>> {

    /**
     * Returns the numerical value of this bound.
     *
     * <p>For finite bounds, this method returns the corresponding numerical value.
     * For infinite bounds, this method may throw an exception, as infinite bounds
     * do not have a numerical representation. Implementations may define specific behavior
     * for infinite bounds or throw {@link UnsupportedOperationException}.
     *
     * <p>Recommendation: Call {@link #isFinite()} to check the bound type before invoking this method.
     *
     * @return the value of the bound.
     * @throws UnsupportedOperationException if the bound is infinite and its value
     *         cannot be represented.
     */
    T getValue();

    /**
     * Indicates whether this bound represents an infinite value.
     *
     * <p>If this method returns {@code true}, {@link #isFinite()} must return {@code false}.
     *
     * @return {@code true} if the bound is infinite, {@code false} otherwise.
     */
    boolean isInfinite();


    /**
     * Indicates whether this bound represents a finite value.
     *
     * <p>If this method returns {@code true}, {@link #isInfinite()} must return {@code false}.
     *
     * <p>Common implementation:
     * <pre>{@code
     * default boolean isFinite() {
     *     return !isInfinite();
     * }
     * }</pre>
     *
     * @return {@code true} if the bound is finite, {@code false} otherwise.
     */
    boolean isFinite();
}
