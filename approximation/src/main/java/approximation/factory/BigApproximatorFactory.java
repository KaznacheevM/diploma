package approximation.factory;

import approximation.Approximator;
import approximation.commons.big.*;
import approximation.series.big.factory.BigSeriesFactory;
import approximation.validation.ArgValidatorFactory;
import approximation.validation.interval.ArgIntervalFactory;

import java.math.BigDecimal;

/**
 * A high-precision implementation of {@link ApproximatorFactory} for creating approximators using {@link BigDecimal}.
 * <p>
 * It leverages different approximator classes for specific use cases and optimizations.
 */
class BigApproximatorFactory implements ApproximatorFactory<BigDecimal> {

    private final ArgValidatorFactory<BigDecimal> argValidatorFactory;
    private final BigSeriesFactory seriesFactory;
    private final ArgIntervalFactory<BigDecimal> argIntervalFactory;

    public BigApproximatorFactory(BigSeriesFactory seriesFactory,
                                  ArgValidatorFactory<BigDecimal> argValidatorFactory,
                                  ArgIntervalFactory<BigDecimal> argIntervalFactory) {
        this.argValidatorFactory = argValidatorFactory;
        this.seriesFactory = seriesFactory;
        this.argIntervalFactory = argIntervalFactory;
    }

    @Override
    public Approximator<BigDecimal> gregoryNaturalLogarithm(BigDecimal arg) {
        return new GregoryNaturalLogarithm(seriesFactory, argValidatorFactory, false, arg);
    }

    @Override
    public Approximator<BigDecimal> naturalLogarithm(boolean optimized, BigDecimal arg) {
        if (optimized) {
            return new GregoryNaturalLogarithm(seriesFactory, argValidatorFactory, true, arg);
        }
        return new NaturalLogarithm(argIntervalFactory, argValidatorFactory, this, arg);
    }

    @Override
    public Approximator<BigDecimal> commonLogarithm(BigDecimal arg) {
        return new CommonLogarithm(this, argValidatorFactory, arg);
    }

    @Override
    public Approximator<BigDecimal> logarithm(BigDecimal base, BigDecimal arg) {
        return new Logarithm(this, argValidatorFactory, base, arg);
    }

    @Override
    public Approximator<BigDecimal> exponential(BigDecimal arg) {
        return new Exponential(seriesFactory, argValidatorFactory, arg);
    }

    @Override
    public Approximator<BigDecimal> exponential(double arg) {
        BigDecimal bigDecimalArg = BigDecimal.valueOf(arg);
        return exponential(bigDecimalArg);
    }

    @Override
    public Approximator<BigDecimal> eulerNumber() {
        return new EulerNumber(seriesFactory);
    }
}
