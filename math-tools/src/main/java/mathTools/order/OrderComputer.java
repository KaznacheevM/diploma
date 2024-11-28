package mathTools.order;

/**
 * Represents a functional interface for computing the numerical order of a value.
 *
 * <p>The "order" typically refers to the magnitude or scale of a number, such as the exponent
 * in scientific notation. Implementations of this interface define the logic for determining
 * the order based on specific criteria.
 *
 * <p>Example usage:
 * <pre>{@code
 * OrderComputer orderComputer = () -> {
 *     BigDecimal number = new BigDecimal("12345");
 *     return number.precision() - 1;
 * };
 * int order = orderComputer.compute(); // Example: 4 for the number 12345
 * }</pre>
 *
 * <p>This interface is functional and can be used in lambda expressions or method references.
 *
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface OrderComputer {

    /**
     * Computes the numerical order of a value.
     *
     * @return the computed order.
     */
    int compute();
}
