package approximation.validation.interval;

import mathTools.interval.Interval;
import mathTools.interval.commons.CommonIntervalFactory;
import mathTools.interval.factory.IntervalFactory;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A high-precision implementation of {@link ArgIntervalFactory} for creating argument intervals using {@link BigDecimal}.
 * <p>
 * This class provides intervals for validating arguments of mathematical functions such as logarithms and exponentials.
 * It uses a combination of {@link CommonIntervalFactory} and {@link IntervalFactory} to construct intervals with
 * predefined or custom bounds.
 */
public class BigIntervalFactory implements ArgIntervalFactory<BigDecimal> {

    /**
     * The factory for creating custom intervals.
     */
    private final IntervalFactory intervalFactory;

    /**
     * The factory for creating common predefined intervals for {@link BigDecimal}.
     */
    private final CommonIntervalFactory<BigDecimal> commonIntervalFactory = CommonIntervalFactory.getBigInstance();

    /**
     * Constructs a {@code BigIntervalFactory} with the specified {@link IntervalFactory}.
     *
     * @param intervalFactory the factory for creating custom intervals.
     * @throws NullPointerException if {@code intervalFactory} is {@code null}.
     */
    public BigIntervalFactory(IntervalFactory intervalFactory) {
        Objects.requireNonNull(intervalFactory, "Interval factory cannot be null");
        this.intervalFactory = intervalFactory;
    }

    /**
     * Creates an interval for validating arguments of the logarithmic function.
     * <p>
     * The interval is defined as:
     * <ul>
     *   <li>Non-optimized: [0; +\infty), using {@link CommonIntervalFactory#createNonNegative()}.</li>
     *   <li>Optimized: [0.52; 1.92], using a custom closed interval.</li>
     * </ul>
     *
     * @param optimized whether the interval should be optimized for specific use cases.
     * @return an {@link Interval} instance representing the valid range of logarithmic arguments.
     */
    @Override
    public Interval<BigDecimal> logarithmArgInterval(boolean optimized) {
        if (optimized) {
            return optimizedLogarithmArgInterval();
        }
        return commonIntervalFactory.createNonNegative();
    }

    /**
     * Creates an optimized interval for logarithmic arguments.
     * <p>
     * The interval is defined as [0.52; 1.92].
     *
     * @return an optimized {@link Interval} instance for logarithmic arguments.
     */
    private Interval<BigDecimal> optimizedLogarithmArgInterval() {
        BigDecimal lowerBound = new BigDecimal("0.52");
        BigDecimal upperBound = new BigDecimal("1.92");

        return intervalFactory.createClosed(lowerBound, upperBound);
    }

    /**
     * Creates an interval for validating arguments of the exponential function.
     * <p>
     * The interval is defined as:
     * <pre>
     *     (-∞; +∞)
     * </pre>
     * indicating that the exponential function is defined for all real numbers.
     *
     * @return an {@link Interval} instance representing the valid range of exponential arguments.
     */
    @Override
    public Interval<BigDecimal> exponentialInterval() {
        return intervalFactory.createUnbounded();
    }
}
