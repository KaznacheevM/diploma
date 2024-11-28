package approximation.term.big.commons;

import approximation.term.big.BigFractionalTerm;
import mathTools.indexMapping.IndexMapper;
import mathTools.signMapping.SignMapper;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A class representing terms in the series expansion of the Gregory-Leibniz series.
 * <p>
 * Each term in this series is represented as:
 * <pre>
 *     x^n / n
 * </pre>
 * where {@code x} is the argument, and {@code n} is the term index. The series can be used
 * in various approximations, including arctangent calculations.
 * <p>
 * This implementation allows custom index mapping via {@link IndexMapper}
 * and sign mapping via {@link SignMapper}.
 */
public class GregoryTerm extends BigFractionalTerm {

    /**
     * The index mapper used to transform term indices.
     */
    protected final IndexMapper<Integer> indexMapper;

    /**
     * The argument of the series term.
     */
    protected final BigDecimal arg;

    /**
     * Constructs a {@code GregoryTerm} with the specified sign mapper, index mapper, and argument.
     *
     * @param signMapper the {@link SignMapper} used to apply signs to terms.
     * @param indexMapper the {@link IndexMapper} used to map term indices before calculations.
     * @param arg the argument of the series term.
     * @throws NullPointerException if any of the provided parameters are {@code null}.
     */
    public GregoryTerm(SignMapper<Integer> signMapper, IndexMapper<Integer> indexMapper, BigDecimal arg) {
        super(signMapper);

        Objects.requireNonNull(indexMapper, "Index mapping pattern cannot be null");
        Objects.requireNonNull(arg, "Argument cannot be null");

        this.indexMapper = indexMapper;
        this.arg = arg;
    }

    /**
     * Computes the numerator of the term at the specified index.
     * <p>
     * The numerator is calculated as \( x^n \), where {@code x} is the argument
     * and {@code n} is the mapped index of the term.
     *
     * @param termIndex the index of the term.
     * @return the numerator of the term as a {@link BigDecimal}.
     */
    @Override
    protected BigDecimal numerator(int termIndex) {
        int mappedIndex = indexMapper.map(termIndex);
        return arg.pow(mappedIndex);
    }

    /**
     * Computes the denominator of the term at the specified index.
     * <p>
     * The denominator is calculated as {@code n}, where {@code n} is the mapped index of the term.
     *
     * @param termIndex the index of the term.
     * @return the denominator of the term as a {@link BigDecimal}.
     */
    @Override
    protected BigDecimal denominator(int termIndex) {
        int mappedIndex = indexMapper.map(termIndex);
        return new BigDecimal(mappedIndex);
    }
}
