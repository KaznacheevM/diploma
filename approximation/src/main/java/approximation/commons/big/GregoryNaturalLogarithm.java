package approximation.commons.big;

import approximation.Approximator;
import approximation.FunctionApproximator;
import approximation.series.big.factory.BigSeriesFactory;
import approximation.validation.ArgValidatorFactory;
import mathTools.accuracy.AccuracyStrategy;
import mathTools.order.OrderComputer;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A high-precision implementation of {@link Approximator} for computing the natural logarithm (\(\ln(x)\)) using Gregory's series.
 * <p>
 * This class computes the natural logarithm based on the series expansion:
 * <pre>
 *     ln(x) = 2 * ((x - 1) / (x + 1) + (1/3) * ((x - 1) / (x + 1))^3 + ...)
 * </pre>
 * The approximation uses a series-based implementation provided by a {@link BigSeriesFactory} and supports both
 * optimized and standard argument validation via {@link ArgValidatorFactory}.
 */
public final class GregoryNaturalLogarithm extends FunctionApproximator<BigDecimal> {

    private static final int MIN_SERIES_INDEX = 0;

    /**
     * The series approximator used for computing the logarithm.
     */
    private final Approximator<BigDecimal> series;

    /**
     * Constructs a {@code GregoryNaturalLogarithm} instance for approximating \(\ln(x)\).
     *
     * @param seriesFactory        the {@link BigSeriesFactory} for creating the series-based approximation.
     * @param argValidatorFactory  the {@link ArgValidatorFactory} for validating the argument.
     * @param optimized            whether to use an optimized argument range for better performance.
     * @param arg                  the argument \(x\) for which \(\ln(x)\) is computed.
     * @throws NullPointerException     if any of the provided factories or the argument is {@code null}.
     * @throws IllegalArgumentException if the argument does not satisfy the validation criteria.
     */
    public GregoryNaturalLogarithm(BigSeriesFactory seriesFactory,
                                   ArgValidatorFactory<BigDecimal> argValidatorFactory,
                                   boolean optimized,
                                   BigDecimal arg) {
        super(AccuracyStrategy.POSITIONAL,
              argValidatorFactory.logarithmArgValidator(optimized),
              arg);

        series = seriesFactory.gregoryLogarithmSeries(optimized, MIN_SERIES_INDEX, arg);
    }

    @Override
    public BigDecimal approximate(int accuracy, RoundingMode roundingMode) {
        int adjustedAccuracy = adjustAccuracy(accuracy);
        int seriesAccuracy = adjustedAccuracy - 1;
        BigDecimal seriesSum = series.approximate(seriesAccuracy, RoundingMode.DOWN);
        BigDecimal approximation = seriesSum.multiply(BigDecimal.TWO);
        return approximation.setScale(-accuracy, roundingMode);
    }

    @Override
    public OrderComputer getOrderComputer() {
        return series.getOrderComputer();
    }
}
