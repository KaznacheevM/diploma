/**
 * Provides classes and interfaces for accumulating the sum of series terms.
 * <p>
 * This package defines the {@link approximation.series.accumulation.SeriesAccumulator}
 * interface, which serves as the foundation for summing terms in a mathematical series.
 * <p>
 * Implementations in this package, such as {@link approximation.series.accumulation.BigSeriesAccumulator},
 * leverage multithreading and high-precision arithmetic for efficient and accurate series summation.
 * <p>
 * Key classes and interfaces:
 * <ul>
 *   <li>{@link approximation.series.accumulation.SeriesAccumulator} - A functional interface for defining accumulation strategies.</li>
 *   <li>{@link approximation.series.accumulation.BigSeriesAccumulator} - A high-precision implementation of {@code SeriesAccumulator} for summing terms with {@link java.math.BigDecimal}.</li>
 * </ul>
 */
package approximation.series.accumulation;