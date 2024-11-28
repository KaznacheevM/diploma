package approximation.commons.big;

import approximation.Approximator;
import approximation.FunctionApproximator;
import approximation.order.big.BigOrderComputer;
import approximation.validation.ArgValidatorFactory;
import approximation.factory.ApproximatorFactory;
import mathTools.accuracy.AccuracyStrategy;
import mathTools.order.OrderComputer;
import toolbox.lazy.concurrency.ConcurrentLazyHolder;
import toolbox.validation.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A high-precision implementation of {@link Approximator} for computing logarithms of arbitrary base.
 * <p>
 * This class computes logarithms using the formula:
 * <pre>
 *     log_b(x) = ln(x) / ln(b)
 * </pre>
 * where \( ln(x) \) and \( ln(b) \) are natural logarithms of the argument and the base, respectively.
 * <p>
 * The class leverages concurrent lazy holders for caching and reusing orders of the logarithm terms,
 * and ensures accurate computation by dynamically adjusting the precision for each term.
 */
public class Logarithm extends FunctionApproximator<BigDecimal> {

    /**
     * The base of the logarithm.
     */
    protected final BigDecimal base;

    /**
     * The approximator for the natural logarithm of the base.
     */
    protected final Approximator<BigDecimal> baseLn;

    /**
     * The approximator for the natural logarithm of the argument.
     */
    protected final Approximator<BigDecimal> argLn;

    protected final ConcurrentLazyHolder<Integer> baseLnOrderHolder = new ConcurrentLazyHolder<>(this::baseLnOrder);
    protected final ConcurrentLazyHolder<Integer> argLnOrderHolder = new ConcurrentLazyHolder<>(this::argLnOrder);

    /**
     * Constructs a {@code Logarithm} instance for computing \(\log_b(x)\).
     *
     * @param approximatorFactory  the {@link ApproximatorFactory} for creating natural logarithm approximators.
     * @param argValidatorFactory  the {@link ArgValidatorFactory} for validating the base and argument.
     * @param base                 the base \(b\) of the logarithm.
     * @param arg                  the argument \(x\) of the logarithm.
     * @throws NullPointerException     if any of the provided factories, base, or argument is {@code null}.
     * @throws IllegalArgumentException if the base or argument does not satisfy validation criteria.
     */
    public Logarithm(ApproximatorFactory<BigDecimal> approximatorFactory,
                     ArgValidatorFactory<BigDecimal> argValidatorFactory,
                     BigDecimal base,
                     BigDecimal arg) {
        super(AccuracyStrategy.POSITIONAL, argValidatorFactory.logarithmArgValidator(false), arg);

        Validator<BigDecimal> baseValidator = argValidatorFactory.logarithmBaseValidator();
        baseValidator.validate(base);
        this.base = base;
        this.baseLn = approximatorFactory.naturalLogarithm(false, base);
        this.argLn = approximatorFactory.naturalLogarithm(false, arg);
    }

    @Override
    public BigDecimal approximate(int accuracy, RoundingMode roundingMode) {
        int adjustedAccuracy = adjustAccuracy(accuracy);

        BigDecimal argLn = computeArgLn(adjustedAccuracy);
        BigDecimal baseLn = computeBaseLn(adjustedAccuracy);

        return argLn.divide(baseLn, -accuracy, roundingMode);
    }

    @Override
    public OrderComputer getOrderComputer() {
        return new LogarithmOrderComputer(this);
    }

    protected int baseLnOrder() {
        return baseLn.getOrderComputer().compute();
    }

    protected int argLnOrder() {
        return argLn.getOrderComputer().compute();
    }

    protected BigDecimal computeArgLn(int adjustedAccuracy) {
        int baseLnOrder = baseLnOrderHolder.getResource();
        int argLnAccuracy = adjustedAccuracy - baseLnOrder - 2;

        return argLn.approximate(argLnAccuracy, RoundingMode.DOWN);
    }

    /**
     * A specialized {@link BigOrderComputer} for determining the order of logarithm approximations.
     */
    protected BigDecimal computeBaseLn(int adjustedAccuracy) {
        int baseLnOrder = baseLnOrderHolder.getResource();
        int argLnOrder = argLnOrderHolder.getResource();
        int baseLnAccuracy = adjustedAccuracy + 2 * baseLnOrder - argLnOrder - 2;

        return baseLn.approximate(baseLnAccuracy, RoundingMode.DOWN);
    }

    protected static final class LogarithmOrderComputer extends BigOrderComputer {

        public LogarithmOrderComputer(Logarithm approximator) {
            super(approximator);
        }

        @Override
        protected int orderLowerEstimation() {
            Logarithm logarithm = (Logarithm) approximator;
            int baseLnOrder = logarithm.baseLnOrderHolder.getResource();
            int argLnOrder = logarithm.argLnOrderHolder.getResource();
            return argLnOrder - baseLnOrder - 1;
        }
    }
}
