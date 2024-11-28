package approximation.term.big;

import approximation.term.SeriesTerm;
import mathTools.signMapping.SignMapper;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * An abstract base class for series terms represented as {@link BigDecimal} values with a sign-mapping mechanism.
 * <p>
 * Implements the {@link SeriesTerm} interface for handling terms in a series, allowing unsigned term approximation
 * and applying signs based on the provided {@link SignMapper}.
 */
public abstract class BigSignedTerm implements SeriesTerm<BigDecimal> {

    /**
     * The sign mapper used to determine and apply the sign of a term based on its index.
     */
    private final SignMapper<Integer> signMapper;

    /**
     * Constructs a {@code BigSignedTerm} with the specified sign mapper.
     *
     * @param signMapper the {@link SignMapper} used to apply signs to terms.
     * @throws NullPointerException if the {@code signMapper} is {@code null}.
     */
    public BigSignedTerm(SignMapper<Integer> signMapper) {
        Objects.requireNonNull(signMapper, "Sign mapper cannot be null");
        this.signMapper = signMapper;
    }

    /**
     * Approximates the value of a term in the series at the specified index with the given accuracy.
     * <p>
     * This method delegates the calculation of the unsigned value to {@link #approximateUnsigned(int, int)}
     * and applies the appropriate sign using {@link #applySign(int, BigDecimal)}.
     *
     * @param termIndex the index of the term to approximate.
     * @param accuracy  the required accuracy for the term approximation.
     * @return the signed approximated value of the term as a {@link BigDecimal}.
     * @throws IllegalArgumentException if the provided parameters are invalid.
     */
    @Override
    public final BigDecimal approximate(int termIndex, int accuracy) {
        BigDecimal unsigned = approximateUnsigned(termIndex, accuracy);
        return applySign(termIndex, unsigned);
    }

    /**
     * Approximates the unsigned value of a term in the series at the specified index with the given accuracy.
     *
     * @param termIndex the index of the term to approximate.
     * @param accuracy  the required accuracy for the term approximation.
     * @return the unsigned approximated value of the term as a {@link BigDecimal}.
     */
    protected abstract BigDecimal approximateUnsigned(int termIndex, int accuracy);

    /**
     * Approximates the minimal value of a term in the series at the specified index
     * up to the first significant digit, including the sign.
     * <p>
     * This method delegates the calculation of the unsigned value to {@link #approximateUnsignedMinimal(int)}
     * and applies the appropriate sign using {@link #applySign(int, BigDecimal)}.
     *
     * @param termIndex the index of the term to approximate.
     * @return the signed minimally approximated value of the term as a {@link BigDecimal}.
     */
    @Override
    public final BigDecimal approximateMinimal(int termIndex) {
        BigDecimal unsigned = approximateUnsignedMinimal(termIndex);
        return applySign(termIndex, unsigned);
    }

    /**
     * Approximates the unsigned minimal value of a term in the series at the specified index
     * up to the first significant digit.
     *
     * @param termIndex the index of the term to approximate.
     * @return the unsigned minimally approximated value of the term as a {@link BigDecimal}.
     */
    protected abstract BigDecimal approximateUnsignedMinimal(int termIndex);

    /**
     * Applies the sign to an unsigned term value based on its index.
     *
     * @param termIndex the index of the term.
     * @param unsigned  the unsigned value of the term as a {@link BigDecimal}.
     * @return the signed value of the term.
     */
    protected final BigDecimal applySign(int termIndex, BigDecimal unsigned) {
        return signMapper.applySign(termIndex, unsigned);
    }
}
