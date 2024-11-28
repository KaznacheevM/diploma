/**
 * Provides classes and interfaces for computing the order of approximations.
 * <p>
 * This package defines abstractions and utilities for determining the order or magnitude
 * of approximations produced by {@link approximation.Approximator} instances. The order is
 * computed by analyzing coarse approximations and adjusting their precision using an
 * {@link mathTools.accuracy.AccuracyStrategy}.
 * <p>
 * Key components:
 * <ul>
 *   <li>{@link approximation.order.ApproximationOrderComputer} - An abstract base class for implementing order computation logic.</li>
 * </ul>
 * Example usage:
 * <pre>
 *     Approximator<BigDecimal> approximator = ...;
 *     ApproximationOrderComputer<BigDecimal> orderComputer = new CustomOrderComputer(approximator, accuracyStrategy);
 *     int order = orderComputer.compute();
 * </pre>
 */
package approximation.order;