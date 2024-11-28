package approximation.commons.big;

import approximation.order.big.BigOrderComputer;
import approximation.validation.ArgValidatorFactory;
import approximation.factory.ApproximatorFactory;
import mathTools.order.OrderComputer;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A high-precision implementation of the common logarithm (\(\log_{10}(x)\)) using base-10.
 * <p>
 * This class extends {@link Logarithm} and specializes its behavior for base-10 logarithms. It leverages
 * optimized computations for the base \(\ln(10)\), which is pre-defined as a constant.
 */
public final class CommonLogarithm extends Logarithm{

    /**
     * Constructs a {@code CommonLogarithm} instance for computing \(\log_{10}(x)\).
     *
     * @param approximatorFactory  the {@link ApproximatorFactory} for creating natural logarithm approximators.
     * @param argValidatorFactory  the {@link ArgValidatorFactory} for validating the argument.
     * @param arg                  the argument \(x\) for which \(\log_{10}(x)\) is computed.
     * @throws NullPointerException     if any of the provided factories or argument is {@code null}.
     * @throws IllegalArgumentException if the argument does not satisfy validation criteria.
     */
    public CommonLogarithm(ApproximatorFactory<BigDecimal> approximatorFactory,
                           ArgValidatorFactory<BigDecimal> argValidatorFactory,
                           BigDecimal arg) {
        super(approximatorFactory, argValidatorFactory, BigDecimal.TEN, arg);
    }

    @Override
    protected BigDecimal computeArgLn(int adjustedAccuracy) {
        int argLnAccuracy = adjustedAccuracy - 2;

        return argLn.approximate(argLnAccuracy, RoundingMode.DOWN);
    }

    @Override
    protected BigDecimal computeBaseLn(int adjustedAccuracy) {
        int argLnOrder = argLnOrderHolder.getResource();
        int baseLnAccuracy = adjustedAccuracy - argLnOrder - 1;

        return baseLn.approximate(baseLnAccuracy, RoundingMode.DOWN);
    }

    @Override
    public OrderComputer getOrderComputer() {
        return new CommonLogarithmOrderComputer(this);
    }

    /**
     * A specialized {@link BigOrderComputer} for determining the order of common logarithm approximations.
     */
    protected static final class CommonLogarithmOrderComputer extends BigOrderComputer {

        public CommonLogarithmOrderComputer(CommonLogarithm approximator) {
            super(approximator);
        }

        @Override
        protected int orderLowerEstimation() {
            CommonLogarithm logarithm = (CommonLogarithm) approximator;
            int argLnOrder = logarithm.argLnOrderHolder.getResource();
            return argLnOrder - 1;
        }
    }
}
