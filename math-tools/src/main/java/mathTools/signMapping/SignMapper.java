package mathTools.signMapping;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Represents a function for computing and applying a signMapping to numbers based on an indexMapping.
 *
 * <p>This functional interface allows defining custom logic for computing the signMapping of a number
 * based on an indexMapping of type {@code T}. The computed signMapping can then be applied to various
 * numeric types, including {@link BigDecimal}, {@link BigInteger}, and {@code double}.
 *
 * <p>Example usage:
 * <pre>{@code
 * SignMapper<Integer> alternatingSign = indexMapping -> (indexMapping % 2 == 0) ? 1 : -1;
 * System.out.println(alternatingSign.applySign(3, 10.0)); // Outputs: -10.0
 * }</pre>
 *
 * @param <T> the type of the indexMapping used for computing the signMapping, must extend {@link Number}.
 */
@FunctionalInterface
public interface SignMapper<T extends Number> {

    /**
     * Computes the signMapping for the given indexMapping.
     *
     * <p>The returned value should typically be positive (e.g., {@code 1}) or negative (e.g., {@code -1}),
     * depending on the implementation.
     *
     * @param index the indexMapping used to compute the signMapping, must not be {@code null}.
     * @return the computed signMapping as an integer.
     * @throws NullPointerException if {@code indexMapping} is {@code null}.
     */
    int computeSign(T index);

    /**
     * Applies the computed signMapping to a {@link BigDecimal}.
     *
     * @param index  the indexMapping used to compute the signMapping, must not be {@code null}.
     * @param number the number to which the signMapping will be applied, must not be {@code null}.
     * @return the number with the applied signMapping.
     * @throws NullPointerException if {@code indexMapping} or {@code number} is {@code null}.
     */
    default BigDecimal applySign(T index, BigDecimal number) {
        Objects.requireNonNull(index, "Index cannot be null");
        Objects.requireNonNull(number, "Number cannot be null");

        int sign = computeSign(index);
        return sign > 0 ? number : number.negate();
    }

    /**
     * Applies the computed signMapping to a {@link BigInteger}.
     *
     * @param index  the indexMapping used to compute the signMapping, must not be {@code null}.
     * @param number the number to which the signMapping will be applied, must not be {@code null}.
     * @return the number with the applied signMapping.
     * @throws NullPointerException if {@code indexMapping} or {@code number} is {@code null}.
     */
    default BigInteger applySign(T index, BigInteger number) {
        Objects.requireNonNull(index, "Index cannot be null");
        Objects.requireNonNull(number, "Number cannot be null");

        int sign = computeSign(index);
        return sign > 0 ? number : number.negate();
    }

    /**
     * Applies the computed signMapping to a {@code double}.
     *
     * @param index  the indexMapping used to compute the signMapping, must not be {@code null}.
     * @param number the number to which the signMapping will be applied.
     * @return the number with the applied signMapping.
     * @throws NullPointerException if {@code indexMapping} is {@code null}.
     */
    default double applySign(T index, double number) {
        Objects.requireNonNull(index, "Index cannot be null");

        int sign = computeSign(index);
        return sign > 0 ? number : -number;
    }
}
