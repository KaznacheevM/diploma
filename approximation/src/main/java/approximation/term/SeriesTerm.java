package approximation.term;

/**
 * An interface for representing terms in a mathematical series.
 * <p>
 * Provides methods for calculating approximations of individual terms at a given index
 * with specified accuracy and for estimating the order of the term.
 *
 * @param <T> the type of the numerical result, such as {@link java.math.BigDecimal} or {@link Double}.
 */
public interface SeriesTerm<T extends Number> {

    /**
     * Approximates the value of a term in the series at the specified index with the given accuracy.
     *
     * @param termIndex the index of the term to approximate. Indexing starts from zero or as defined by the implementation.
     * @param accuracy  the required accuracy for the term approximation. Interpretation depends on the implementation.
     * @return the approximated value of the term as an instance of {@code T}.
     * @throws IllegalArgumentException if the provided parameters are invalid, such as negative accuracy or index.
     */
    T approximate(int termIndex, int accuracy);

    /**
     * Approximates the value of a term in the series at the specified index
     * with minimal accuracy up to the first significant digit.
     * <p>
     * Uses {@link java.math.RoundingMode#DOWN} to truncate insignificant digits.
     *
     * @param termIndex the index of the term to approximate. Indexing starts from zero or as defined by the implementation.
     * @return the minimally approximated value of the term as an instance of {@code T}.
     * @throws IllegalArgumentException if the term index is invalid (e.g., negative).
     */
    T approximateMinimal(int termIndex);

    /**
     * Provides an overestimated order of the term at the specified index.
     * <p>
     * This method returns an upper bound for the order, which can be used
     * in calculations requiring safe approximations of series behavior.
     *
     * @param termIndex the index of the term. Indexing starts from zero or as defined by the implementation.
     * @return the overestimated order of the term as an integer.
     * @throws IllegalArgumentException if the term index is invalid (e.g., negative).
     */
    int overestimateOrder(int termIndex);
}
