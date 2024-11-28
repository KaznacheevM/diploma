package approximation.commons.big;

import approximation.FunctionApproximator;
import approximation.order.big.BigOrderComputer;
import approximation.validation.interval.ArgIntervalFactory;
import approximation.Approximator;
import approximation.factory.ApproximatorFactory;
import approximation.validation.ArgValidatorFactory;
import mathTools.accuracy.AccuracyStrategy;
import mathTools.interval.Interval;
import mathTools.order.OrderComputer;
import mathTools.order.OrderUtil;
import mathTools.normalization.Normalizer;
import toolbox.lazy.LazyHolder;
import toolbox.lazy.concurrency.ConcurrentLazyHolder;
import toolbox.search.unspecific.semiBounded.AdaptiveIntegerFinder;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Predicate;

/**
 * A high-precision implementation of {@link Approximator} for computing the natural logarithm (\(\ln(x)\)).
 * <p>
 * This class handles arguments of arbitrary magnitude by dynamically scaling the argument
 * into a suitable range using exponential transformations. It supports optimized argument intervals
 * and positional accuracy strategies to deliver precise results efficiently.
 */
public class NaturalLogarithm extends FunctionApproximator<BigDecimal> {

    private final ArgNormalizer argNormalizer = new ArgNormalizer();

    private final ApproximatorFactory<BigDecimal> approximatorFactory;

    private final Interval<BigDecimal> argInterval;

    private final LazyHolder<Integer> scalingExponentHolder = new ConcurrentLazyHolder<>(this::computeScalingExponent);

    /**
     * Constructs a {@code NaturalLogarithm} instance for computing \(\ln(x)\).
     *
     * @param argIntervalFactory    the {@link ArgIntervalFactory} for creating argument intervals.
     * @param argValidatorFactory   the {@link ArgValidatorFactory} for validating arguments.
     * @param approximatorFactory   the {@link ApproximatorFactory} for creating approximators used in calculations.
     * @param arg                   the argument \(x\) for which \(\ln(x)\) is computed.
     * @throws NullPointerException     if any of the provided factories or the argument is {@code null}.
     * @throws IllegalArgumentException if the argument does not satisfy the validation criteria.
     */
    public NaturalLogarithm(ArgIntervalFactory<BigDecimal> argIntervalFactory,
                            ArgValidatorFactory<BigDecimal> argValidatorFactory,
                            ApproximatorFactory<BigDecimal> approximatorFactory,
                            BigDecimal arg) {
        super(AccuracyStrategy.POSITIONAL,
              argValidatorFactory.logarithmArgValidator(false),
              arg);

        argInterval = argIntervalFactory.logarithmArgInterval(true);
        this.approximatorFactory = approximatorFactory;
    }

    @Override
    public BigDecimal approximate(int accuracy, RoundingMode roundingMode) {
        if (arg.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ArithmeticException("Argument must be greater than zero for natural logarithm calculation.");
        }

        int adjustedAccuracy = adjustAccuracy(accuracy);
        BigDecimal scaledArg = argNormalizer.normalize(arg, adjustedAccuracy, RoundingMode.DOWN);

        Approximator<BigDecimal> optimizedNaturalLogarithm = approximatorFactory.naturalLogarithm(true, scaledArg);
        BigDecimal scaledLnValue = optimizedNaturalLogarithm.approximate(accuracy, roundingMode);

        int scalingExponent = scalingExponentHolder.getResource();
        BigDecimal bigScalingExponent = new BigDecimal(scalingExponent);

        return scaledLnValue.subtract(bigScalingExponent).setScale(-accuracy, roundingMode);
    }

    private int computeScalingExponent() {
        Predicate<Integer> scalingExponentPredicate = createScalingExponentPredicate();
        boolean isIncreasing = argInterval.isRightOfInterval(arg);
        AdaptiveIntegerFinder finder = new AdaptiveIntegerFinder(scalingExponentPredicate, isIncreasing);

        int minMatchingExponent = finder.find(0).orElseThrow();

        BigDecimal exponentialValue = computeTestingExponential(-minMatchingExponent);
        BigDecimal scaledArg = arg.multiply(exponentialValue);

        if (argInterval.isRightOfInterval(scaledArg)) {
            return -minMatchingExponent - 1;
        }

        return -minMatchingExponent;
    }

    private Predicate<Integer> createScalingExponentPredicate() {
        if (argInterval.isLeftOfInterval(arg)) {
            return i -> {
                Approximator<BigDecimal> exponential = approximatorFactory.exponential(i);
                BigDecimal exponentialValue = exponential.approximate(-arg.scale() - 1, RoundingMode.UP);

                return arg.compareTo(exponentialValue) > 0;
            };
        } else if (argInterval.isRightOfInterval(arg)) {
            return i -> {
                Approximator<BigDecimal> exponential = approximatorFactory.exponential(i+1);
                BigDecimal exponentialValue = exponential.approximate(-arg.scale() - 1, RoundingMode.DOWN);

                return arg.compareTo(exponentialValue) < 0;
            };
        }

        return i -> i == 0;
    }

    private BigDecimal computeTestingExponential(int minMatchingExponent) {
        int exponentialAccuracy = -argInterval.getUpperBound().getValue().scale() - OrderUtil.overestimateOrderOf(arg);
        Approximator<BigDecimal> exponential = approximatorFactory.exponential(minMatchingExponent);
        return exponential.approximate(exponentialAccuracy, RoundingMode.UP);
    }

    @Override
    public OrderComputer getOrderComputer() {
        return new LnOrderComputer(this);
    }

    /**
     * A helper class for normalizing the logarithm argument into a suitable range.
     */
    protected final class ArgNormalizer implements Normalizer<BigDecimal> {

        @Override
        public BigDecimal normalize(BigDecimal arg, int accuracy, RoundingMode roundingMode) {
            int scalingExponent = scalingExponentHolder.getResource();
            if (scalingExponent == 0) {
                return arg.setScale(-accuracy + 1, roundingMode);
            }

            BigDecimal doubledArg = arg.multiply(BigDecimal.TWO);
            int doubledArgOrder = OrderUtil.overestimateOrderOf(doubledArg);

            int exponentialAccuracy = accuracy - doubledArgOrder;

            Approximator<BigDecimal> exponential = approximatorFactory.exponential(scalingExponent);
            BigDecimal exponentialValue = exponential.approximate(exponentialAccuracy, RoundingMode.DOWN);

            BigDecimal scaledArg = arg.multiply(exponentialValue);
            return scaledArg.setScale(-accuracy + 1, roundingMode);
        }

        @Override
        public BigDecimal normalizeMinimal(BigDecimal decimal) {
            MathContext mathContext = new MathContext(1, RoundingMode.DOWN);

            int scalingExponent = scalingExponentHolder.getResource();
            if (scalingExponent == 0) {
                return arg.round(mathContext);
            }

            BigDecimal doubledArg = arg.multiply(BigDecimal.TWO);
            int doubledArgOrder = OrderUtil.overestimateOrderOf(doubledArg);

            Approximator<BigDecimal> exponential = approximatorFactory.exponential(scalingExponent);
            BigDecimal exponentialValue = exponential.approximate(doubledArgOrder, RoundingMode.DOWN);

            BigDecimal scaledArg = arg.multiply(exponentialValue);
            return scaledArg.round(mathContext);
        }
    }

    /**
     * A specialized {@link BigOrderComputer} for determining the order of natural logarithm approximations.
     */
    protected static final class LnOrderComputer extends BigOrderComputer {

        public LnOrderComputer(NaturalLogarithm naturalLogarithm) {
            super(naturalLogarithm);
        }

        @Override
        protected int orderLowerEstimation() {
            NaturalLogarithm naturalLogarithm = (NaturalLogarithm) approximator;
            if (naturalLogarithm.scalingExponentHolder.getResource() == 0) {
                BigDecimal scaledArg = naturalLogarithm.argNormalizer.normalizeMinimal(naturalLogarithm.arg);
                return naturalLogarithm.approximatorFactory.gregoryNaturalLogarithm(scaledArg).getOrderComputer().compute();
            } else {
                return OrderUtil.orderOf(naturalLogarithm.scalingExponentHolder.getResource()) - 1;
            }
        }
    }
}
