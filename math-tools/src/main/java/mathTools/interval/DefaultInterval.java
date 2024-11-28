package mathTools.interval;

import mathTools.interval.bound.Bound;


/**
 * Default implementation of the {@link Interval} interface.
 *
 * <p>This class represents an immutable interval with specific bounds and an interval type.
 * It validates the bounds during construction to ensure they are correct for the specified {@link IntervalType}.
 *
 * <p>Once created, the interval cannot be modified.
 *
 * @param <T> the type of the bound's value, must extend {@link Number} and implement {@link Comparable}.
 * @see Interval
 * @see IntervalType
 * @see Bound
 */
public class DefaultInterval<T extends Number & Comparable<T>> implements Interval<T> {

    protected final IntervalType intervalType;

    protected final Bound<T> lowerBound;

    protected final Bound<T> upperBound;

    /**
     * Constructs a new interval with the specified type and bounds.
     *
     * @param intervalType the type of the interval, must not be {@code null}.
     * @param lowerBound   the lower bound of the interval, must not be {@code null}.
     * @param upperBound   the upper bound of the interval, must not be {@code null}.
     * @throws NullPointerException  if any parameter is {@code null}.
     * @throws IllegalStateException if the bounds are invalid for the specified interval type.
     */
    public DefaultInterval(IntervalType intervalType, Bound<T> lowerBound, Bound<T> upperBound) {
        if (!intervalType.boundsAreCorrect(lowerBound, upperBound)) {
            throw new IllegalArgumentException("Invalid bounds: " + lowerBound + " to " + upperBound + " for " + intervalType + " type");
        }

        this.intervalType = intervalType;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * {@inheritDoc}
     *
     * @see Interval#getType()
     */
    @Override
    public final IntervalType getType() {
        return intervalType;
    }

    /**
     * {@inheritDoc}
     *
     * @see Interval#getLowerBound()
     */
    @Override
    public final Bound<T> getLowerBound() {
        return lowerBound;
    }

    /**
     * {@inheritDoc}
     *
     * @see Interval#getUpperBound()
     */
    @Override
    public final Bound<T> getUpperBound() {
        return upperBound;
    }

    /**
     * {@inheritDoc}
     *
     * @see Interval#isInInterval(Number)
     */
    @Override
    public boolean isInInterval(T number) {
        return intervalType.isInInterval(number, lowerBound, upperBound);
    }

    /**
     * {@inheritDoc}
     *
     * @see Interval#isLeftOfInterval(Number)
     */
    @Override
    public boolean isLeftOfInterval(T number) {
        return intervalType.isLeftOfInterval(number, lowerBound);
    }

    /**
     * {@inheritDoc}
     *
     * @see Interval#isRightOfInterval(Number)
     */
    @Override
    public boolean isRightOfInterval(T number) {
        return intervalType.isRightOfInterval(number, upperBound);
    }

    /**
     * Returns a string representation of this interval.
     *
     * <p>The string includes the interval type and its bounds, formatted as:
     * {@code Interval{intervalType=..., lowerBound=..., upperBound=...}}.
     *
     * @return a string representation of this interval.
     */
    @Override
    public String toString() {
        return "Interval{" +
                "intervalType=" + intervalType +
                ", lowerBound=" + lowerBound +
                ", upperBound=" + upperBound +
                '}';
    }
}
