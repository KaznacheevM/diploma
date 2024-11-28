package mathTools.normalization;

import java.math.RoundingMode;

/**
 * Represents a utility for normalizing numerical values according to specific accuracy and rounding rules.
 *
 * <p>This interface provides methods for adjusting numerical values to meet predefined accuracy
 * requirements or for normalizing them to their minimal acceptable representation.
 *
 * <p>The normalization process can be useful in scenarios such as mathematical computations,
 * data standardization, or reducing precision errors.
 *
 * @param <T> the type of the numerical value to normalize, must extend {@link Number}.
 */
public interface Normalizer<T extends Number> {

    /**
     * Normalizes the given numerical argument to the specified accuracy, applying the provided rounding mode.
     *
     * <p>The method adjusts the numerical value based on the accuracy parameter (e.g., significant digits,
     * decimal places) and uses the specified {@link RoundingMode} to handle rounding.
     *
     * @param arg           the numerical argument to normalize, must not be {@code null}.
     * @param accuracy      the desired accuracy of the normalized value.
     * @param roundingMode  the rounding mode to apply, must not be {@code null}.
     * @return the normalized value of {@code arg}.
     * @throws NullPointerException     if {@code arg} or {@code roundingMode} is {@code null}.
     * @throws IllegalArgumentException if {@code accuracy} is less than zero.
     */
    T normalize(T arg, int accuracy, RoundingMode roundingMode);

    /**
     * Normalizes the given numerical argument to its minimal acceptable representation.
     *
     * <p>The method reduces the argument to its simplest form while maintaining its numerical equivalence.
     * The exact definition of "minimal representation" depends on the implementation.
     *
     * @param arg the numerical argument to normalize, must not be {@code null}.
     * @return the minimally normalized value of {@code arg}.
     * @throws NullPointerException if {@code arg} is {@code null}.
     */
    T normalizeMinimal(T arg);
}
