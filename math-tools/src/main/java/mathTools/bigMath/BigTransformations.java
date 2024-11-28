package mathTools.bigMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Provides utility methods for performing transformations on {@link BigDecimal} values.
 *
 * <p>This class includes methods for calculating the symmetric ratio of a number,
 * a common transformation in numerical computations, particularly in the context
 * of mathematical series or ratios.
 *
 * <p>The symmetric ratio is defined as:
 * <pre>{@code
 * symmetricRatio(arg) = (arg - 1) / (arg + 1)
 * }</pre>
 *
 * <p>These methods allow for precision control via {@link MathContext} or
 * explicit scale and {@link RoundingMode}. The class also includes a method
 * for computing the symmetric ratio with minimal precision.
 *
 * <p>The class is not instantiable and is intended to be used statically.
 */
public class BigTransformations {

    /**
     * Private constructor to prevent instantiation.
     */
    private BigTransformations() {
    }

    /**
     * Computes the symmetric ratio of the given {@link BigDecimal} argument using the specified scale
     * and rounding mode.
     *
     * <p>The symmetric ratio is defined as:
     * {@code (arg - 1) / (arg + 1)}.
     *
     * @param arg          the argument for which to compute the symmetric ratio, must not be {@code null}.
     * @param scale        the scale to use for the result.
     * @param roundingMode the rounding mode to apply, must not be {@code null}.
     * @return the symmetric ratio of {@code arg}.
     * @throws NullPointerException if {@code arg} or {@code roundingMode} is {@code null}.
     */
    public static BigDecimal symmetricRatio(BigDecimal arg, int scale, RoundingMode roundingMode) {
        Objects.requireNonNull(arg, "Argument cannot be null");
        Objects.requireNonNull(roundingMode, "Rounding mode cannot be null");

        BigDecimal numerator = arg.subtract(BigDecimal.ONE);
        BigDecimal denominator = arg.add(BigDecimal.ONE);
        return numerator.divide(denominator, scale, roundingMode);
    }

    /**
     * Computes the symmetric ratio of the given {@link BigDecimal} argument using the specified {@link MathContext}.
     *
     * <p>The symmetric ratio is defined as:
     * {@code (arg - 1) / (arg + 1)}.
     *
     * @param arg         the argument for which to compute the symmetric ratio, must not be {@code null}.
     * @param mathContext the {@link MathContext} to use for the computation, must not be {@code null}.
     * @return the symmetric ratio of {@code arg}.
     * @throws NullPointerException if {@code arg} or {@code mathContext} is {@code null}.
     */
    public static BigDecimal symmetricRatio(BigDecimal arg, MathContext mathContext) {
        Objects.requireNonNull(arg, "Argument cannot be null");
        Objects.requireNonNull(mathContext, "Math context cannot be null");

        BigDecimal numerator = arg.subtract(BigDecimal.ONE);
        BigDecimal denominator = arg.add(BigDecimal.ONE);
        return numerator.divide(denominator, mathContext);
    }

    /**
     * Computes the symmetric ratio of the given {@link BigDecimal} argument using minimal precision.
     *
     * <p>The result is computed using a {@link MathContext} with a precision of 1 digit and the
     * specified rounding mode.
     *
     * @param arg          the argument for which to compute the symmetric ratio, must not be {@code null}.
     * @param roundingMode the rounding mode to apply, must not be {@code null}.
     * @return the symmetric ratio of {@code arg} with minimal precision.
     * @throws NullPointerException if {@code arg} or {@code roundingMode} is {@code null}.
     */
    public static BigDecimal symmetricRatioMinimal(BigDecimal arg, RoundingMode roundingMode) {
        MathContext mathContext = new MathContext(1, roundingMode);
        return symmetricRatio(arg, mathContext);
    }
}
