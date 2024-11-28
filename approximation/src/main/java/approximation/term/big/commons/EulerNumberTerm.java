package approximation.term.big.commons;

import approximation.term.big.BigFactorialTerm;
import mathTools.indexMapping.IntegerIndexMapper;
import mathTools.signMapping.IntegerSignMapper;

import java.math.BigDecimal;


/**
 * A class representing terms in the series expansion of the Euler number (e).
 * <p>
 * Each term in the series is represented as:
 * <pre>
 *     1 / n!
 * </pre>
 * where {@code n} is the term index. This class uses {@link BigFactorialTerm} to compute
 * the denominator (factorial) and assumes a constant numerator of 1.
 * <p>
 * The series is defined as:
 * <pre>
 *     e = 1 + 1/1! + 1/2! + 1/3! + ...
 * </pre>
 * This implementation applies positive signs to all terms using {@link IntegerSignMapper#POSITIVE}
 * and does not modify the term indices ({@link IntegerIndexMapper#IDENTITY}).
 */
public final class EulerNumberTerm extends BigFactorialTerm {

    /**
     * Constructs an {@code EulerNumberTerm} instance.
     * <p>
     * Uses {@link IntegerSignMapper#POSITIVE} to ensure all terms have positive signs,
     * and {@link IntegerIndexMapper#IDENTITY} to preserve term indices without modification.
     */
    public EulerNumberTerm() {
        super(IntegerSignMapper.POSITIVE, IntegerIndexMapper.IDENTITY);
    }

    /**
     * Returns the numerator for the term at the specified index.
     * <p>
     * In the Euler number series, all terms have a constant numerator of 1.
     *
     * @param termIndex the index of the term (ignored, as the numerator is always 1).
     * @return {@link BigDecimal#ONE}, representing the numerator of the term.
     */
    @Override
    protected BigDecimal numerator(int termIndex) {
        return BigDecimal.ONE;
    }
}
