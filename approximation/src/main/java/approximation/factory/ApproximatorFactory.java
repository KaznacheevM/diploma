package approximation.factory;

import approximation.Approximator;
import approximation.series.big.factory.BigSeriesFactory;
import approximation.validation.ArgValidatorFactory;
import approximation.validation.interval.ArgIntervalFactory;

import java.math.BigDecimal;

/**
 * A factory interface for creating high-precision {@link Approximator} instances.
 * <p>
 * This interface defines methods for generating approximators for various mathematical functions,
 * such as logarithms (natural, common, arbitrary base), exponential functions, and the Euler number.
 * It abstracts the creation of approximators, enabling consistent and reusable approximations.
 *
 * @param <T> the type of number for which the approximators operate, typically {@link BigDecimal}.
 */
public interface ApproximatorFactory<T extends Number> {

    /**
     * Creates an approximator for the natural logarithm ln(x) using Gregory's series.
     *
     * @param arg the argument x for which ln(x) is computed.
     * @return an {@link Approximator} instance for the natural logarithm.
     */
    Approximator<T> gregoryNaturalLogarithm(BigDecimal arg);

    /**
     * Creates an approximator for the natural logarithm ln(x).
     *
     * @param optimized whether to use an optimized argument range for improved performance.
     * @param arg       the argument x for which ln(x) is computed.
     * @return an {@link Approximator} instance for the natural logarithm.
     */
    Approximator<T> naturalLogarithm(boolean optimized, BigDecimal arg);

    /**
     * Creates an approximator for the common logarithm log_{10}(x).
     *
     * @param arg the argument x for which log_{10}(x) is computed.
     * @return an {@link Approximator} instance for the common logarithm.
     */
    Approximator<T> commonLogarithm(BigDecimal arg);

    /**
     * Creates an approximator for the logarithm with an arbitrary base log_b(x).
     *
     * @param base the base b of the logarithm.
     * @param arg  the argument x for which log_b(x) is computed.
     * @return an {@link Approximator} instance for the logarithm.
     */
    Approximator<T> logarithm(BigDecimal base, BigDecimal arg);

    /**
     * Creates an approximator for the exponential function e^x with a high-precision argument.
     *
     * @param arg the argument x for which e^x is computed.
     * @return an {@link Approximator} instance for the exponential function.
     */
    Approximator<T> exponential(BigDecimal arg);

    /**
     * Creates an approximator for the exponential function e^x with a double-precision argument.
     *
     * @param arg the argument x for which e^x is computed.
     * @return an {@link Approximator} instance for the exponential function.
     */
    Approximator<T> exponential(double arg);

    /**
     * Creates an approximator for the Euler number e.
     *
     * @return an {@link Approximator} instance for the Euler number.
     */
    Approximator<T> eulerNumber();

    /**
     * Returns a default implementation of {@code ApproximatorFactory} for {@link BigDecimal}.
     * <p>
     * The default implementation uses predefined factories for argument validation, intervals, and series.
     *
     * @return a default {@link ApproximatorFactory} instance for {@link BigDecimal}.
     */
    static ApproximatorFactory<BigDecimal> getBigInstance() {
        ArgIntervalFactory<BigDecimal> argIntervalFactory = ArgIntervalFactory.getBigInstance();
        ArgValidatorFactory<BigDecimal> argValidatorFactory = ArgValidatorFactory.getBigInstance();
        BigSeriesFactory seriesFactory = BigSeriesFactory.getDefaultInstance();
        return new BigApproximatorFactory(seriesFactory, argValidatorFactory,argIntervalFactory);
    }
}
