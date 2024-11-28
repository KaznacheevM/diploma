package approximation.util;

import approximation.Approximator;
import approximation.factory.ApproximatorFactory;
import mathTools.accuracy.AccuracyTransformations;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * A utility class for performing high-precision approximations of mathematical functions using {@link BigDecimal}.
 * <p>
 * This class provides methods for computing values such as the Euler number, exponential functions, and natural logarithms,
 * leveraging an {@link ApproximatorFactory} to generate the necessary approximators.
 */
public class BigApproximationUtil implements ApproximationUtil<BigDecimal> {

    private final ApproximatorFactory<BigDecimal> approximatorFactory;

    public BigApproximationUtil(ApproximatorFactory<BigDecimal> approximatorFactory) {
        this.approximatorFactory = approximatorFactory;
    }

    @Override
    public BigDecimal eulerNumber(MathContext mathContext) {
        Approximator<BigDecimal> eulerNumber = approximatorFactory.eulerNumber();
        return approximate(eulerNumber, mathContext);
    }

    @Override
    public BigDecimal exponential(BigDecimal arg, MathContext mathContext) {
        Approximator<BigDecimal> exponential = approximatorFactory.exponential(arg);
        return approximate(exponential, mathContext);
    }

    @Override
    public BigDecimal naturalLogarithm(BigDecimal arg, MathContext mathContext) {
        Approximator<BigDecimal> naturalLogarithm = approximatorFactory.naturalLogarithm(false, arg);
        return approximate(naturalLogarithm, mathContext);
    }

    @Override
    public BigDecimal logarithm(BigDecimal base, BigDecimal arg, MathContext mathContext) {
        Approximator<BigDecimal> logarithm = approximatorFactory.logarithm(base, arg);
        return approximate(logarithm, mathContext);
    }

    @Override
    public BigDecimal commonLogarithm(BigDecimal arg, MathContext mathContext) {
        Approximator<BigDecimal> logarithm = approximatorFactory.commonLogarithm(arg);
        return approximate(logarithm, mathContext);
    }

    private int computePositionalAccuracy(Approximator<BigDecimal> approximator, MathContext mathContext) {
        int order = approximator.getOrderComputer().compute();
        int precision = mathContext.getPrecision();
        return AccuracyTransformations.getLeastDigitPositionByPrecision(precision, order);
    }

    private BigDecimal approximate(Approximator<BigDecimal> approximator, MathContext mathContext) {
        int positionalAccuracy = computePositionalAccuracy(approximator, mathContext);
        RoundingMode roundingMode = mathContext.getRoundingMode();
        return approximator.approximate(positionalAccuracy, roundingMode);
    }
}
