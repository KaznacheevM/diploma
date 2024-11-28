package approximation.term.big;

import mathTools.bigMath.BigMath;
import mathTools.indexMapping.IndexMapper;
import mathTools.signMapping.SignMapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * An abstract base class for series terms where the denominator is represented
 * by a factorial value, computed using {@link BigMath#factorial}.
 * <p>
 * Extends {@link BigFractionalTerm} and integrates functionality for mapping term indices
 * through an {@link IndexMapper}.
 */
public abstract class BigFactorialTerm extends BigFractionalTerm {

    /**
     * The index mapper used to transform term indices before calculating the factorial.
     */
    protected final IndexMapper<Integer> indexMapper;

    /**
     * Constructs a {@code BigFactorialTerm} with the specified sign pattern and index mapper.
     *
     * @param signPattern the {@link SignMapper} used to apply signs to terms.
     * @param indexMapper the {@link IndexMapper} used to map term indices before calculating the denominator.
     * @throws NullPointerException if {@code indexMapper} is {@code null}.
     */
    public BigFactorialTerm(SignMapper<Integer> signPattern, IndexMapper<Integer> indexMapper) {
        super(signPattern);
        Objects.requireNonNull(indexMapper, "Index mapper cannot be null");
        this.indexMapper = indexMapper;
    }

    /**
     * Computes the denominator of the term at the specified index.
     * <p>
     * The term index is first mapped using {@link IndexMapper#map(Number)}, and the factorial
     * of the mapped index is calculated using {@link BigMath#factorial(long)}.
     *
     * @param termIndex the index of the term.
     * @return the denominator of the term as a {@link BigDecimal}.
     * @throws ArithmeticException if the mapped index is invalid for factorial computation (e.g., negative).
     */
    @Override
    protected BigDecimal denominator(int termIndex) {
        int mappedIndex = indexMapper.map(termIndex);
        BigInteger factorial = BigMath.factorial(mappedIndex);
        return new BigDecimal(factorial);
    }
}
