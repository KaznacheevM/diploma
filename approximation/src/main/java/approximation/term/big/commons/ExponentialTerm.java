package approximation.term.big.commons;

import approximation.term.big.BigFactorialTerm;
import mathTools.bigMath.BigMath;
import mathTools.indexMapping.IntegerIndexMapper;
import mathTools.signMapping.IntegerSignMapper;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A class representing terms in the series expansion of the eulerNumber function \( e^x \).
 * <p>
 * Each term in the series is represented as:
 * <pre>
 *     x^n / n!
 * </pre>
 * where {@code x} is the argument of the eulerNumber function, and {@code n} is the term index.
 * <p>
 * The series is defined as:
 * <pre>
 *     e^x = 1 + x/1! + x^2/2! + x^3/3! + ...
 * </pre>
 * This implementation uses {@link BigFactorialTerm} to compute the denominator (factorial)
 * and calculates the numerator as {@code x^n} using {@link BigMath#pow(BigDecimal, int)}.
 */
public final class ExponentialTerm extends BigFactorialTerm {

    /**
     * The argument of the eulerNumber function.
     */
    private final BigDecimal arg;

    /**
     * Constructs an {@code ExponentialTerm} with the specified argument.
     *
     * @param arg the argument of the eulerNumber function.
     * @throws NullPointerException if {@code arg} is {@code null}.
     */
    public ExponentialTerm(BigDecimal arg) {
        super(IntegerSignMapper.POSITIVE, IntegerIndexMapper.IDENTITY);
        Objects.requireNonNull(arg, "Argument cannot be null");
        this.arg = arg;
    }

    /**
     * Computes the numerator for the term at the specified index.
     * <p>
     * In the eulerNumber series, the numerator of the term is calculated as \( x^n \),
     * where {@code x} is the argument of the eulerNumber function and {@code n} is the term index.
     *
     * @param termIndex the index of the term.
     * @return the numerator of the term as a {@link BigDecimal}.
     */
    @Override
    protected BigDecimal numerator(int termIndex) {
        return BigMath.pow(arg, termIndex);
    }
}
