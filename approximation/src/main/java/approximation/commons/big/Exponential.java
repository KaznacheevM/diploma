package approximation.commons.big;

import approximation.FunctionApproximator;
import approximation.order.big.BigOrderComputer;
import approximation.series.big.factory.BigSeriesFactory;
import approximation.Approximator;
import approximation.validation.ArgValidatorFactory;
import mathTools.bigMath.BigMath;
import mathTools.accuracy.AccuracyStrategy;
import mathTools.order.OrderComputer;
import mathTools.order.OrderUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * A high-precision implementation of {@link Approximator} for computing the exponential function \(e^x\).
 * <p>
 * This class handles both positive and negative arguments, as well as fractional and integer parts
 * of the argument, to compute \(e^x\) with optimal accuracy. For performance, it uses series-based
 * approximations provided by a {@link BigSeriesFactory}.
 */
public class Exponential extends FunctionApproximator<BigDecimal> {

    private static final int MIN_SERIES_INDEX = 0;
    private final BigSeriesFactory seriesFactory;
    private final ArgValidatorFactory<BigDecimal> argValidatorFactory;

    /**
     * The integer part of the argument.
     */
    private final int argIntPart;

    /**
     * The fractional part of the argument.
     */
    private final BigDecimal argfracPart;

    /**
     * The approximator for the fractional part of the exponential series.
     */
    private final Approximator<BigDecimal> series;

    /**
     * Constructs an {@code Exponential} instance for computing \(e^x\).
     *
     * @param seriesFactory        the {@link BigSeriesFactory} for creating series-based approximations.
     * @param argValidatorFactory  the {@link ArgValidatorFactory} for validating the argument.
     * @param arg                  the argument \(x\) for which \(e^x\) is computed.
     * @throws NullPointerException     if any of the provided factories or the argument is {@code null}.
     * @throws IllegalArgumentException if the argument is too large to be represented as an integer part.
     */
    public Exponential(BigSeriesFactory seriesFactory,
                       ArgValidatorFactory<BigDecimal> argValidatorFactory,
                       BigDecimal arg) {
        super(AccuracyStrategy.POSITIONAL,
              argValidatorFactory.exponentialValidator(),
              arg);
        this.seriesFactory = seriesFactory;
        this.argValidatorFactory = argValidatorFactory;

        argIntPart = argIntPart(arg);
        argfracPart = argFracPart(arg, argIntPart);

        series = seriesFactory.exponentialSeries(false, MIN_SERIES_INDEX, argfracPart);
    }

    @Override
    public BigDecimal approximate(int accuracy, RoundingMode roundingMode) {
        if (arg.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        }

        if (arg.compareTo(BigDecimal.ZERO) < 0) {
            return approximateNegative(arg, accuracy, roundingMode);
        }

        if (arg.compareTo(BigDecimal.ONE) == 0) {
            return approximateEulerNumber(accuracy, roundingMode);
        }

        if (arg.abs().compareTo(BigDecimal.ONE) < 0) {
            return approximateFracPart(accuracy, roundingMode);
        }

        int adjustedAccuracy = adjustAccuracy(accuracy);
        BigDecimal approximation = approximateDecimal(adjustedAccuracy, RoundingMode.DOWN);

        return approximation.setScale(-accuracy, roundingMode);
    }

    private BigDecimal approximateEulerNumber(int accuracy, RoundingMode roundingMode) {
        Approximator<BigDecimal> eulerNumber = new EulerNumber(seriesFactory);
        return eulerNumber.approximate(accuracy, roundingMode);
    }

    private BigDecimal approximateNegative(BigDecimal arg, int accuracy, RoundingMode roundingMode) {
        Exponential exponential = new Exponential(seriesFactory,
                                                  argValidatorFactory,
                                                  arg.negate());

        BigDecimal exponentialValue = exponential.approximate(accuracy, RoundingMode.DOWN);
        return BigDecimal.ONE.divide(exponentialValue, -accuracy, roundingMode);
    }

    private BigDecimal approximateFracPart(int accuracy, RoundingMode roundingMode) {
        return series.approximate(accuracy, roundingMode);
    }

    private BigDecimal approximateIntPart(int accuracy, RoundingMode roundingMode) {
        int intOrder = OrderUtil.overestimateOrderOf(argIntPart);

        BigDecimal exponentialEstimation = new BigDecimal(3).pow(argIntPart - 1);
        int exponentialOrder = OrderUtil.overestimateOrderOf(exponentialEstimation);

        int eAccuracy = accuracy - 1 - intOrder - exponentialOrder;
        BigDecimal e = approximateEulerNumber(eAccuracy, RoundingMode.DOWN);

        BigDecimal approximation = BigMath.pow(e, argIntPart);

        return approximation.setScale(-accuracy, roundingMode);
    }

    private BigDecimal approximateDecimal(int accuracy, RoundingMode roundingMode) {
        BigDecimal intExp = approximateIntPart(accuracy, RoundingMode.DOWN);

        BigDecimal exponentialEstimation = new BigDecimal(3).pow(argIntPart + 1);
        int exponentialOrder = OrderUtil.overestimateOrderOf(exponentialEstimation);

        int fractionalExpAccuracy = accuracy - exponentialOrder;
        BigDecimal fractionalExp = approximateFracPart(fractionalExpAccuracy, RoundingMode.DOWN);

        BigDecimal approximation = intExp.multiply(fractionalExp);
        return approximation.setScale(-accuracy, roundingMode);
    }


    private static int argIntPart(BigDecimal arg) {
        arg = arg.setScale(0, RoundingMode.FLOOR);
        int intPart;

        try {
            intPart = arg.intValueExact();
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("The number is too big", e);
        }

        return intPart;
    }

    private static BigDecimal argFracPart(BigDecimal arg, int intPart) {
        BigDecimal bigIntPart = new BigDecimal(intPart);
        return bigIntPart.subtract(arg);
    }

    @Override
    public OrderComputer getOrderComputer() {
        if (argIntPart == 0) {
            return series.getOrderComputer();
        }
        return new ExponentialOrderComputer(this);
    }

    private static final class ExponentialOrderComputer extends BigOrderComputer {

        public ExponentialOrderComputer(Exponential exponential) {
            super(exponential);
        }

        @Override
        protected int orderLowerEstimation() {
            Exponential exponential = (Exponential) approximator;
            if (exponential.argIntPart == 0) {
                throw new IllegalStateException();
            }
            BigDecimal expApprox;
            if (exponential.argIntPart >= 0) {
                expApprox = BigMath.pow(BigDecimal.TWO, exponential.argIntPart);
            } else {
                expApprox = BigMath.pow(BigDecimal.TWO, -exponential.argIntPart);
                expApprox = BigDecimal.ONE.divide(expApprox, new MathContext(1, RoundingMode.DOWN));
            }
            return OrderUtil.orderOf(expApprox);
        }
    }
}
