package mathTools.interval;

import mathTools.interval.bound.Bound;

import java.util.Objects;

/**
 * Represents the type of interval with specific behavior for bounds and membership checks.
 *
 * <p>This enum provides predefined types of intervals (e.g., closed, open, half-open, and unbounded).
 * Each type defines its own implementation for:
 * <ul>
 *     <li>Checking whether a number is strictly to the left of the interval.</li>
 *     <li>Checking whether a number is strictly to the right of the interval.</li>
 *     <li>Validating whether the bounds of the interval are correct.</li>
 * </ul>
 *
 * <p>For each interval type, the behavior for bounds and membership checks is implemented
 * via abstract methods {@code isLeftOfIntervalImpl}, {@code isRightOfIntervalImpl}, and
 * {@code boundsAreCorrectImpl}, which are overridden for each specific type.
 *
 * @see Bound
 */
public enum IntervalType {

    /**
     * A closed interval that includes both the lower and upper bounds.
     *
     * <p>In this type of interval:
     * <ul>
     *     <li>A number is considered to the left of the interval if it is strictly less
     *     than the lower bound.</li>
     *     <li>A number is considered to the right of the interval if it is strictly greater
     *     than the upper bound.</li>
     * </ul>
     *
     * <p>Both bounds must be finite, and the lower bound must be less than or equal
     * to the upper bound.
     */
    CLOSED {
        @Override
        protected <T extends Number & Comparable<T>> boolean isLeftOfIntervalImpl(T number, Bound<T> lowerBound) {
            validateFiniteBound(lowerBound, "Expected finite lower bound for closed interval, but was");

            T boundValue = lowerBound.getValue();
            return number.compareTo(boundValue) < 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean isRightOfIntervalImpl(T number, Bound<T> upperBound) {
            validateFiniteBound(upperBound, "Expected finite upper bound for closed interval, but was");

            T boundValue = upperBound.getValue();
            return number.compareTo(boundValue) > 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean boundsAreCorrectImpl(Bound<T> lowerBound, Bound<T> upperBound) {
            if (lowerBound.isInfinite() || upperBound.isInfinite()) {
                return false;
            }

            T lowerBoundValue = lowerBound.getValue();
            T upperBoundValue = upperBound.getValue();
            return lowerBoundValue.compareTo(upperBoundValue) <= 0;
        }
    },

    /**
     * An open interval that excludes both the lower and upper bounds.
     *
     * <p>In this type of interval:
     * <ul>
     *     <li>A number is considered to the left of the interval if it is less than
     *     or equal to the lower bound.</li>
     *     <li>A number is considered to the right of the interval if it is greater than
     *     or equal to the upper bound.</li>
     * </ul>
     *
     * <p>Bounds must be finite, and the lower bound must be strictly less than the upper bound.
     */
    OPEN {
        @Override
        protected <T extends Number & Comparable<T>> boolean isLeftOfIntervalImpl(T number, Bound<T> lowerBound) {
            validateFiniteBound(lowerBound, "Expected finite lower bound for open interval, but was");

            T boundValue = lowerBound.getValue();
            return number.compareTo(boundValue) <= 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean isRightOfIntervalImpl(T number, Bound<T> upperBound) {
            validateFiniteBound(upperBound, "Expected finite upper bound for open interval, but was");

            T boundValue = upperBound.getValue();
            return number.compareTo(boundValue) >= 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean boundsAreCorrectImpl(Bound<T> lowerBound, Bound<T> upperBound) {
            if (lowerBound.isInfinite() || upperBound.isInfinite()) {
                return false;
            }

            T lowerBoundValue = lowerBound.getValue();
            T upperBoundValue = upperBound.getValue();
            return lowerBoundValue.compareTo(upperBoundValue) < 0;
        }
    },

    /**
     * A right-open interval that includes the lower bound but excludes the upper bound.
     *
     * <p>In this type of interval:
     * <ul>
     *     <li>A number is considered to the left of the interval if it is less than
     *     the lower bound.</li>
     *     <li>A number is considered to the right of the interval if it is greater than
     *     or equal to the upper bound.</li>
     * </ul>
     *
     * <p>Bounds must be finite, and the lower bound must be strictly less than the upper bound.
     */
    RIGHT_OPEN {
        @Override
        protected <T extends Number & Comparable<T>> boolean isLeftOfIntervalImpl(T number, Bound<T> lowerBound) {
            validateFiniteBound(lowerBound, "Expected finite lower bound for right-open interval, but was");

            T boundValue = lowerBound.getValue();
            return number.compareTo(boundValue) < 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean isRightOfIntervalImpl(T number, Bound<T> upperBound) {
            validateFiniteBound(upperBound, "Expected finite upper bound for right-open interval, but was");

            T boundValue = upperBound.getValue();
            return number.compareTo(boundValue) >= 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean boundsAreCorrectImpl(Bound<T> lowerBound, Bound<T> upperBound) {
            if (lowerBound.isInfinite() || upperBound.isInfinite()) {
                return false;
            }

            T lowerBoundValue = lowerBound.getValue();
            T upperBoundValue = upperBound.getValue();
            return lowerBoundValue.compareTo(upperBoundValue) < 0;
        }
    },

    /**
     * A left-open interval that excludes the lower bound but includes the upper bound.
     *
     * <p>In this type of interval:
     * <ul>
     *     <li>A number is considered to the left of the interval if it is less than
     *     or equal to the lower bound.</li>
     *     <li>A number is considered to the right of the interval if it is greater than
     *     the upper bound.</li>
     * </ul>
     *
     * <p>Bounds must be finite, and the lower bound must be strictly less than the upper bound.
     */
    LEFT_OPEN {
        @Override
        protected <T extends Number & Comparable<T>> boolean isLeftOfIntervalImpl(T number, Bound<T> lowerBound) {
            validateFiniteBound(lowerBound, "Expected finite lower bound for left-open interval, but was");

            T boundValue = lowerBound.getValue();
            return number.compareTo(boundValue) <= 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean isRightOfIntervalImpl(T number, Bound<T> upperBound) {
            validateFiniteBound(upperBound, "Expected finite upper bound for left-open interval, but was");

            T boundValue = upperBound.getValue();
            return number.compareTo(boundValue) > 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean boundsAreCorrectImpl(Bound<T> lowerBound, Bound<T> upperBound) {
            if (lowerBound.isInfinite() || upperBound.isInfinite()) {
                return false;
            }

            T lowerBoundValue = lowerBound.getValue();
            T upperBoundValue = upperBound.getValue();
            return lowerBoundValue.compareTo(upperBoundValue) < 0;
        }
    },

    /**
     * A right-unbounded open interval that has a finite lower bound but no upper bound.
     *
     * <p>In this type of interval:
     * <ul>
     *     <li>A number is considered to the left of the interval if it is less than
     *     or equal to the lower bound.</li>
     *     <li>No number is considered to the right of the interval.</li>
     * </ul>
     *
     * <p>The lower bound must be finite, and the upper bound must be infinite.
     */
    RIGHT_UNBOUNDED_OPEN {
        @Override
        protected <T extends Number & Comparable<T>> boolean isLeftOfIntervalImpl(T number, Bound<T> lowerBound) {
            validateFiniteBound(lowerBound, "Expected finite lower bound for right-unbounded open interval, but was");

            T boundValue = lowerBound.getValue();
            return number.compareTo(boundValue) <= 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean isRightOfIntervalImpl(T number, Bound<T> upperBound) {
            validateInfiniteBound(upperBound, "Expected infinite upper bound for right-unbounded open interval, but was");

            return false;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean boundsAreCorrectImpl(Bound<T> lowerBound, Bound<T> upperBound) {
            return lowerBound.isFinite() && upperBound.isInfinite();
        }
    },

    /**
     * A left-unbounded open interval that has no lower bound but a finite upper bound.
     *
     * <p>In this type of interval:
     * <ul>
     *     <li>No number is considered to the left of the interval.</li>
     *     <li>A number is considered to the right of the interval if it is greater than
     *     or equal to the upper bound.</li>
     * </ul>
     *
     * <p>The lower bound must be infinite, and the upper bound must be finite.
     */
    LEFT_UNBOUNDED_OPEN {
        @Override
        protected <T extends Number & Comparable<T>> boolean isLeftOfIntervalImpl(T number, Bound<T> lowerBound) {
            validateInfiniteBound(lowerBound, "Expected infinite lower bound for left-unbounded open interval, but was");

            return false;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean isRightOfIntervalImpl(T number, Bound<T> upperBound) {
            validateFiniteBound(upperBound, "Expected finite upper bound for left-unbounded open interval, but was");

            T boundValue = upperBound.getValue();
            return number.compareTo(boundValue) >= 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean boundsAreCorrectImpl(Bound<T> lowerBound, Bound<T> upperBound) {
            return lowerBound.isInfinite() && upperBound.isFinite();
        }
    },

    /**
     * A right-unbounded closed interval that has a finite lower bound and includes it,
     * but no upper bound.
     *
     * <p>In this type of interval:
     * <ul>
     *     <li>A number is considered to the left of the interval if it is less than
     *     the lower bound.</li>
     *     <li>No number is considered to the right of the interval.</li>
     * </ul>
     *
     * <p>The lower bound must be finite, and the upper bound must be infinite.
     */
    RIGHT_UNBOUNDED_CLOSED {
        @Override
        protected <T extends Number & Comparable<T>> boolean isLeftOfIntervalImpl(T number, Bound<T> lowerBound) {
            validateFiniteBound(lowerBound, "Expected finite lower bound for right-unbounded closed interval, but was");

            T boundValue = lowerBound.getValue();
            return number.compareTo(boundValue) < 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean isRightOfIntervalImpl(T number, Bound<T> upperBound) {
            validateInfiniteBound(upperBound, "Expected infinite upper bound for right-unbounded closed interval, but was");

            return false;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean boundsAreCorrectImpl(Bound<T> lowerBound, Bound<T> upperBound) {
            return lowerBound.isFinite() && upperBound.isInfinite();
        }
    },

    /**
     * A left-unbounded closed interval that has no lower bound but includes the finite upper bound.
     *
     * <p>In this type of interval:
     * <ul>
     *     <li>No number is considered to the left of the interval.</li>
     *     <li>A number is considered to the right of the interval if it is greater than
     *     the upper bound.</li>
     * </ul>
     *
     * <p>The lower bound must be infinite, and the upper bound must be finite.
     */
    LEFT_UNBOUNDED_CLOSED {
        @Override
        protected <T extends Number & Comparable<T>> boolean isLeftOfIntervalImpl(T number, Bound<T> lowerBound) {
            validateInfiniteBound(lowerBound, "Expected infinite lower bound for left-unbounded closed interval, but was");

            return false;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean isRightOfIntervalImpl(T number, Bound<T> upperBound) {
            validateFiniteBound(upperBound, "Expected finite upper bound for left-unbounded closed interval, but was");

            T boundValue = upperBound.getValue();
            return number.compareTo(boundValue) > 0;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean boundsAreCorrectImpl(Bound<T> lowerBound, Bound<T> upperBound) {
            return lowerBound.isInfinite() && upperBound.isFinite();
        }
    },

    /**
     * Represents an unbounded interval with no lower or upper bounds.
     *
     * <p>In this type of interval:
     * <ul>
     *     <li>No number is considered to the left of the interval.</li>
     *     <li>No number is considered to the right of the interval.</li>
     * </ul>
     *
     * <p>Both bounds must be infinite.
     */
    UNBOUNDED {
        @Override
        protected <T extends Number & Comparable<T>> boolean isLeftOfIntervalImpl(T number, Bound<T> lowerBound) {
            validateInfiniteBound(lowerBound, "Expected infinite lower bound for left-unbounded closed interval, but was");

            return false;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean isRightOfIntervalImpl(T number, Bound<T> upperBound) {
            validateInfiniteBound(upperBound, "Expected infinite upper bound for right-unbounded closed interval, but was");

            return false;
        }

        @Override
        protected <T extends Number & Comparable<T>> boolean boundsAreCorrectImpl(Bound<T> lowerBound, Bound<T> upperBound) {
            return lowerBound.isInfinite() && upperBound.isInfinite();
        }
    };

    /**
     * Checks whether the given number lies within the interval defined by the specified bounds.
     *
     * @param number the number to check, must not be {@code null}.
     * @param lowerBound the lower bound of the interval, must not be {@code null}.
     * @param upperBound the upper bound of the interval, must not be {@code null}.
     * @param <T> the type of the bound's value.
     * @return {@code true} if the number lies within the interval, {@code false} otherwise.
     * @throws IllegalStateException if the bounds are invalid for the interval type.
     * @throws NullPointerException if {@code number}, {@code lowerBound}, or {@code upperBound} is {@code null}.
     */
    public <T extends Number & Comparable<T>> boolean isInInterval(T number, Bound<T> lowerBound, Bound<T> upperBound) {
        Objects.requireNonNull(number, "Number cannot be null");

        if (boundsAreCorrect(lowerBound, upperBound)) {
            return !isLeftOfIntervalImpl(number, lowerBound) && !isRightOfIntervalImpl(number, upperBound);
        }

        throw new IllegalStateException("Invalid bounds: " + lowerBound + " to " + upperBound + " for " + this + " type");
    }

    /**
     * Checks whether the given number is strictly to the left of the interval.
     *
     * @param number the number to check, must not be {@code null}.
     * @param lowerBound the lower bound of the interval, must not be {@code null}.
     * @param <T> the type of the bound's value.
     * @return {@code true} if the number is less than the lower bound, {@code false} otherwise.
     * @throws NullPointerException if {@code number} or {@code lowerBound} is {@code null}.
     */
    public <T extends Number & Comparable<T>> boolean isLeftOfInterval(T number, Bound<T> lowerBound) {
        Objects.requireNonNull(number, "Number cannot be null");
        Objects.requireNonNull(lowerBound, "Lower bound cannot be null");

        return isLeftOfIntervalImpl(number, lowerBound);
    }

    /**
     * Checks whether the given number is strictly to the right of the interval.
     *
     * @param number the number to check, must not be {@code null}.
     * @param upperBound the upper bound of the interval, must not be {@code null}.
     * @param <T> the type of the bound's value.
     * @return {@code true} if the number is greater than the upper bound, {@code false} otherwise.
     * @throws NullPointerException if {@code number} or {@code upperBound} is {@code null}.
     */
    public <T extends Number & Comparable<T>> boolean isRightOfInterval(T number, Bound<T> upperBound) {
        Objects.requireNonNull(number, "Number cannot be null");
        Objects.requireNonNull(upperBound, "Upper bound cannot be null");

        return isRightOfIntervalImpl(number, upperBound);
    }

    /**
     * Validates whether the specified bounds are correct for this interval type.
     *
     * @param lowerBound the lower bound to validate, must not be {@code null}.
     * @param upperBound the upper bound to validate, must not be {@code null}.
     * @param <T> the type of the bound's value.
     * @return {@code true} if the bounds are valid for this interval type, {@code false} otherwise.
     * @throws NullPointerException if {@code lowerBound} or {@code upperBound} is {@code null}.
     */
    public <T extends Number & Comparable<T>> boolean boundsAreCorrect(Bound<T> lowerBound, Bound<T> upperBound) {
        Objects.requireNonNull(lowerBound, "Lower bound cannot be null");
        Objects.requireNonNull(upperBound, "Upper bound cannot be null");

        return boundsAreCorrectImpl(lowerBound, upperBound);
    }

    /**
     * Abstract method for checking whether a number is strictly to the left of the interval.
     *
     * @param number the number to check.
     * @param lowerBound the lower bound of the interval.
     * @param <T> the type of the bound's value.
     * @return {@code true} if the number is strictly to the left, {@code false} otherwise.
     */
    protected abstract <T extends Number & Comparable<T>> boolean isLeftOfIntervalImpl(T number, Bound<T> lowerBound);

    /**
     * Abstract method for checking whether a number is strictly to the right of the interval.
     *
     * @param number the number to check.
     * @param upperBound the upper bound of the interval.
     * @param <T> the type of the bound's value.
     * @return {@code true} if the number is strictly to the right, {@code false} otherwise.
     */
    protected abstract <T extends Number & Comparable<T>> boolean isRightOfIntervalImpl(T number, Bound<T> upperBound);

    /**
     * Abstract method for validating whether the bounds are correct for this interval type.
     *
     * @param lowerBound the lower bound to validate.
     * @param upperBound the upper bound to validate.
     * @param <T> the type of the bound's value.
     * @return {@code true} if the bounds are valid, {@code false} otherwise.
     */
    protected abstract <T extends Number & Comparable<T>> boolean boundsAreCorrectImpl(Bound<T> lowerBound, Bound<T> upperBound);

    /**
     * Validates that the given bound is finite.
     *
     * @param bound the bound to validate.
     * @param message the error message to use if the validation fails.
     * @param <T> the type of the bound's value.
     * @throws IllegalStateException if the bound is infinite.
     */
    protected <T extends Number & Comparable<T>> void validateFiniteBound(Bound<T> bound, String message) {
        if (bound.isInfinite()) {
            throw new IllegalStateException(message + ": " + bound);
        }
    }

    /**
     * Validates that the given bound is infinite.
     *
     * @param bound the bound to validate.
     * @param message the error message to use if the validation fails.
     * @param <T> the type of the bound's value.
     * @throws IllegalStateException if the bound is finite.
     */
    protected <T extends Number & Comparable<T>> void validateInfiniteBound(Bound<T> bound, String message) {
        if (bound.isFinite()) {
            throw new IllegalStateException(message + ": " + bound);
        }
    }
}
