package approximation.term.big;

import mathTools.order.OrderUtil;
import mathTools.signMapping.SignMapper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * An abstract base class for series terms that represent fractional values as {@link BigDecimal}.
 * <p>
 * This class extends {@link BigSignedTerm} and provides implementations for handling
 * fractional terms, with support for positional accuracy. Positional accuracy means that
 * the {@code accuracy} parameter specifies the position of the least significant digit
 * (e.g., for accuracy = -2, calculations are rounded to the hundredths place).
 */
public abstract class BigFractionalTerm extends BigSignedTerm {

    /**
     * Constructs a {@code BigFractionalTerm} with the specified sign mapper.
     *
     * @param signMapper the {@link SignMapper} used to apply signs to terms.
     * @throws NullPointerException if {@code signMapper} is {@code null}.
     */
    public BigFractionalTerm(SignMapper<Integer> signMapper) {
        super(signMapper);
    }

    /**
     * Approximates the unsigned value of the fractional term at the specified index
     * with the given positional accuracy.
     * <p>
     * The {@code accuracy} parameter is positional, meaning it specifies the position
     * of the least significant digit. For example, if {@code accuracy} is -2, the result
     * is rounded to the hundredths place.
     *
     * @param termIndex the index of the term to approximate.
     * @param accuracy  the positional accuracy of the approximation.
     * @return the unsigned approximated value of the fractional term as a {@link BigDecimal}.
     * @throws ArithmeticException if division by zero occurs.
     */
    @Override
    public final BigDecimal approximateUnsigned(int termIndex, int accuracy) {
        BigDecimal numerator = numerator(termIndex);
        BigDecimal denominator = denominator(termIndex);
        return numerator.divide(denominator, -accuracy, RoundingMode.DOWN);
    }

    /**
     * Approximates the minimal unsigned value of the fractional term at the specified index.
     * <p>
     * The result includes only the first significant digit, with rounding applied
     * using {@link RoundingMode#DOWN}.
     *
     * @param termIndex the index of the term to approximate.
     * @return the minimally approximated unsigned value of the fractional term as a {@link BigDecimal}.
     * @throws ArithmeticException if division by zero occurs.
     */
    @Override
    public final BigDecimal approximateUnsignedMinimal(int termIndex) {
        BigDecimal numerator = numerator(termIndex);
        BigDecimal denominator = denominator(termIndex);

        MathContext mathContext = new MathContext(1, RoundingMode.DOWN);
        return numerator.divide(denominator, mathContext);
    }

    /**
     * Provides an overestimated order of the fractional term at the specified index.
     * <p>
     * The order is computed as the difference between the overestimated order of the numerator
     * and the exact order of the denominator.
     *
     * @param termIndex the index of the term.
     * @return the overestimated order of the term as an integer.
     */
    @Override
    public int overestimateOrder(int termIndex) {
        BigDecimal numerator = numerator(termIndex);
        BigDecimal denominator = denominator(termIndex);

        int numeratorOrder = OrderUtil.overestimateOrderOf(numerator);
        int denominatorOrder = OrderUtil.orderOf(denominator);
        return numeratorOrder - denominatorOrder;
    }

    /**
     * Returns the numerator of the fractional term at the specified index.
     *
     * @param termIndex the index of the term.
     * @return the numerator of the term as a {@link BigDecimal}.
     */
    protected abstract BigDecimal numerator(int termIndex);

    /**
     * Returns the denominator of the fractional term at the specified index.
     *
     * @param termIndex the index of the term.
     * @return the denominator of the term as a {@link BigDecimal}.
     */
    protected abstract BigDecimal denominator(int termIndex);
}
