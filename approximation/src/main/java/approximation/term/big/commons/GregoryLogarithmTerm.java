package approximation.term.big.commons;

import mathTools.indexMapping.IntegerIndexMapper;
import mathTools.signMapping.IntegerSignMapper;

import java.math.BigDecimal;

/**
 * A class representing terms in the Gregory series expansion for logarithms.
 * <p>
 * Each term in this series is represented as:
 * <pre>
 *     (x - 1)^n / (n * (x + 1)^n)
 * </pre>
 * where {@code x} is the argument, and {@code n} is the (shifted odd) term index.
 * <p>
 * This series is commonly used for approximating logarithmic functions.
 * The implementation uses {@link IntegerSignMapper#POSITIVE} to assign positive signs
 * to terms and {@link IntegerIndexMapper#SHIFTED_ODD} to handle odd-index mapping.
 */
public final class GregoryLogarithmTerm extends GregoryTerm {

    /**
     * Constructs a {@code GregoryLogarithmTerm} with the specified argument.
     *
     * @param arg the argument of the series. It represents the value of \(x\) in the logarithmic expansion.
     * @throws NullPointerException if {@code arg} is {@code null}.
     */
    public GregoryLogarithmTerm(BigDecimal arg) {
        super(IntegerSignMapper.POSITIVE, IntegerIndexMapper.SHIFTED_ODD, arg);
    }

    /**
     * Computes the numerator of the term at the specified index.
     * <p>
     * The numerator is calculated as \( (x - 1)^n \), where {@code x} is the argument
     * and {@code n} is the mapped (shifted odd) index of the term.
     *
     * @param termIndex the index of the term.
     * @return the numerator of the term as a {@link BigDecimal}.
     */
    @Override
    protected BigDecimal numerator(int termIndex) {
        int mappedIndex = indexMapper.map(termIndex);
        return arg.subtract(BigDecimal.ONE).pow(mappedIndex);
    }

    /**
     * Computes the denominator of the term at the specified index.
     * <p>
     * The denominator is calculated as \( n \cdot (x + 1)^n \), where {@code x} is the argument
     * and {@code n} is the mapped (shifted odd) index of the term.
     *
     * @param termIndex the index of the term.
     * @return the denominator of the term as a {@link BigDecimal}.
     */
    @Override
    protected BigDecimal denominator(int termIndex) {
        int mappedIndex = indexMapper.map(termIndex);
        BigDecimal denominator = arg.add(BigDecimal.ONE);
        denominator = denominator.pow(mappedIndex);
        BigDecimal bigMappedIndex = new BigDecimal(mappedIndex);
        return denominator.multiply(bigMappedIndex);
    }
}
