package mathTools.order;

/**
 * Provides access to an {@link OrderComputer} for computing the numerical order of a value.
 *
 * <p>This interface defines a method to retrieve an {@link OrderComputer}, which
 * encapsulates the logic for determining the order of a value. The "order" typically
 * refers to the magnitude or scale of a number, such as the exponent in scientific notation.
 *
 * <p>Example usage:
 * <pre>{@code
 * OrderProvider provider = () -> () -> {
 *     BigDecimal number = new BigDecimal("12345");
 *     return number.precision() - 1;
 * };
 * int order = provider.getOrderComputer().compute(); // Example: 4 for the number 12345
 * }</pre>
 *
 * <p>This interface enables modular and flexible computation of numerical orders,
 * decoupling the logic from the rest of the application.
 *
 * @see OrderComputer
 */
@FunctionalInterface
public interface OrderProvider {

    /**
     * Returns an {@link OrderComputer} for computing the numerical order.
     *
     * @return the {@link OrderComputer} instance.
     */
    OrderComputer getOrderComputer();
}
