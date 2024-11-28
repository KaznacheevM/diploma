package mathTools.interval.bound.infinite;

/**
 * Represents an infinite bound of an interval with a fixed direction.
 *
 * <p>This class provides a factory implementation of an infinite bound that can
 * represent either positive or negative infinity. The direction of the bound is
 * determined by the {@code isPositive} property, which is specified during construction.
 *
 * <p>Instances of this class are immutable and can be used wherever infinite bounds
 * are required in interval calculations.
 *
 * <p>Key characteristics:
 * <ul>
 *     <li>A {@code DirectionalInfiniteBound} object always represents an infinite bound
 *     (never finite).</li>
 *     <li>The {@link #isPositive()} method indicates whether the bound is positive
 *     or negative infinity.</li>
 *     <li>The {@link #getValue()} method is not supported and will throw
 *     {@link UnsupportedOperationException}.</li>
 * </ul>
 *
 * @param <T> the type of the numerical value of the bound, must extend {@link Number}
 *            and implement {@link Comparable}.
 * @see AbstractInfiniteBound
 */
public class DirectionalInfiniteBound<T extends Number & Comparable<T>> extends AbstractInfiniteBound<T> {

    /**
     * Indicates whether this infinite bound is positive.
     */
    protected final boolean isPositive;

    /**
     * Creates a new infinite bound with the specified direction.
     *
     * @param isPositive {@code true} for positive infinity, {@code false} for negative infinity.
     */
    public DirectionalInfiniteBound(boolean isPositive) {
        this.isPositive = isPositive;
    }

    /**
     * Indicates whether this infinite bound is positive.
     *
     * @return {@code true} if this bound represents positive infinity, {@code false} otherwise.
     */
    @Override
    public final boolean isPositive() {
        return isPositive;
    }

    /**
     * Compares this bound to another object for equality.
     *
     * <p>Two {@code DirectionalInfiniteBound} objects are considered equal if they:
     * <ul>
     *     <li>Have the same class.</li>
     *     <li>Have the same {@code isPositive} value.</li>
     * </ul>
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

        DirectionalInfiniteBound<?> that = (DirectionalInfiniteBound<?>) object;
        return isPositive == that.isPositive;
    }

    /**
     * Returns the hash code of this bound.
     *
     * <p>The hash code is computed based on the signMapping of the bound.
     *
     * @return the hash code of this bound.
     */
    @Override
    public int hashCode() {
        return Boolean.hashCode(isPositive) * 31;
    }
}
