/**
 * Provides classes for computing the order of high-precision approximations using {@link java.math.BigDecimal}.
 * <p>
 * This package defines abstract implementations and utilities for determining the order of approximations
 * in mathematical series or functions. The order is calculated based on the magnitude of coarse approximations.
 * <p>
 * Key components:
 * <ul>
 *   <li>{@link approximation.order.big.BigOrderComputer} - An abstract base class for computing the order of {@link java.math.BigDecimal} approximations.</li>
 * </ul>
 * Example usage:
 * <pre>
 *     Approximator<BigDecimal> approximator = ...;
 *     BigOrderComputer orderComputer = new CustomBigOrderComputer(approximator);
 *     int order = orderComputer.compute();
 * </pre>
 */
package approximation.order.big;