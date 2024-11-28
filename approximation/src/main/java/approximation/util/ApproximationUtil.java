package approximation.util;

import approximation.factory.ApproximatorFactory;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * A utility interface for performing high-precision approximations of mathematical functions.
 * <p>
 * This interface defines methods for approximating values such as the Euler number, exponential functions,
 * and logarithms with a specified level of precision and rounding mode provided by a {@link MathContext}.
 * It abstracts the approximation process, allowing for seamless integration with different numerical types.
 *
 * @param <T> the type of number for which approximations are performed, typically {@link BigDecimal}.
 */
public interface ApproximationUtil<T extends Number> {

    /**
     * Approximates the Euler number e with the specified {@link MathContext}.
     *
     * @param mathContext the {@link MathContext} specifying the precision and rounding mode for the approximation.
     * @return the approximated value of e as an instance of {@code T}.
     */
    T eulerNumber(MathContext mathContext);

    /**
     * Approximates the exponential function e^x for the given argument with the specified {@link MathContext}.
     *
     * @param arg         the argument x for which e^x is computed.
     * @param mathContext the {@link MathContext} specifying the precision and rounding mode for the approximation.
     * @return the approximated value of e^x as an instance of {@code T}.
     */
    T exponential(BigDecimal arg, MathContext mathContext);

    /**
     * Approximates the natural logarithm ln(x) for the given argument with the specified {@link MathContext}.
     *
     * @param arg         the argument x for which ln(x) is computed.
     * @param mathContext the {@link MathContext} specifying the precision and rounding mode for the approximation.
     * @return the approximated value of ln(x) as an instance of {@code T}.
     */
    T naturalLogarithm(BigDecimal arg, MathContext mathContext);

    /**
     * Approximates the logarithm log_{10}(x) for the given argument with the specified {@link MathContext}.
     *
     * @param arg         the argument x for which log_{10}(x) is computed.
     * @param mathContext the {@link MathContext} specifying the precision and rounding mode for the approximation.
     * @return the approximated value of log_{10}(x) as an instance of {@code T}.
     */
    T logarithm(BigDecimal base, BigDecimal arg, MathContext mathContext);

    /**
     * Approximates the common logarithm log_{10}(x) for the given argument with the specified {@link MathContext}.
     *
     * @param arg         the argument x for which log_{10}(x) is computed.
     * @param mathContext the {@link MathContext} specifying the precision and rounding mode for the approximation.
     * @return the approximated value of log_{10}(x) as an instance of {@code T}.
     */
    T commonLogarithm(BigDecimal arg, MathContext mathContext);

    /**
     * Returns a default implementation of {@code ApproximationUtil} for {@link BigDecimal}.
     * <p>
     * The default implementation uses an {@link ApproximatorFactory} to perform the approximations.
     *
     * @return a {@link ApproximationUtil} instance for {@link BigDecimal}.
     */
    static ApproximationUtil<BigDecimal> getBigInstance() {
        ApproximatorFactory<BigDecimal> approximatorFactory = ApproximatorFactory.getBigInstance();
        return new BigApproximationUtil(approximatorFactory);
    }
}
