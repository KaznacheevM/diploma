package mathTools.interval.bound.finite;

import java.util.Objects;


/**
 * Represents a finite bound of an interval with a specific numerical value.
 *
 * <p>This class extends {@link AbstractFiniteBound} and provides an implementation
 * for finite bounds that store a concrete numerical {@code value}. Instances of this
 * class are immutable and designed for use in interval calculations and comparisons.
 *
 * <p>Equality and hash code calculations are based on the bound's value, ensuring
 * consistent behavior when used in collections or compared to other bounds.
 *
 * @param <T> the type of the numerical value of the bound, must extend {@link Number}
 *            and implement {@link Comparable}.
 * @see AbstractFiniteBound
 */
public class FiniteBound<T extends Number & Comparable<T>> extends AbstractFiniteBound<T> {

    /**
     * The numerical value of this finite bound.
     */
    protected final T value;

    /**
     * Creates a new finite bound with the specified value.
     *
     * @param value the numerical value of the bound, must not be {@code null}.
     * @throws NullPointerException if the specified value is {@code null}.
     */
    public FiniteBound(T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        this.value = value;
    }

    /**
     * Returns the numerical value of this finite bound.
     *
     * @return the numerical value of this bound.
     */
    @Override
    public final T getValue() {
        return value;
    }

    /**
     * Compares this bound to another object for equality.
     *
     * <p>Two finite bounds are considered equal if:
     * <ul>
     *     <li>They are instances of {@code FiniteBound}, and</li>
     *     <li>Their values are equal according to {@link Objects#equals(Object, Object)}.</li>
     * </ul>
     *
     * <p>This implementation ensures compatibility when used in collections
     * or when comparing bounds in interval calculations.
     *
     * @param object the object to compare to, may be {@code null}.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        FiniteBound<?> bound = (FiniteBound<?>) object;
        return Objects.equals(value, bound.value);
    }

    /**
     * Returns the hash code of this bound.
     *
     * <p>The hash code is computed as {@code 31 * value.hashCode()} to ensure a consistent
     * distribution for use in hash-based collections such as {@link java.util.HashMap}.
     *
     * @return the hash code of this bound.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(value) * 31;
    }
}
