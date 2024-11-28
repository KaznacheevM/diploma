/**
 * Provides classes and interfaces for mapping and transforming index values.
 *
 * <p>This package contains tools for defining and applying index transformations, such as
 * doubling, shifting, or other operations on numerical indices. The core interface
 * {@link mathTools.indexMapping.IndexMapper} defines a functional contract
 * for implementing custom index mapping logic.
 *
 * <p>Key components:
 * <ul>
 *     <li>{@link mathTools.indexMapping.IndexMapper} — the primary functional
 *     interface for index transformation.</li>
 *     <li>{@link mathTools.indexMapping.IntegerIndexMapper} — an enumeration
 *     with predefined mappings for integer indices (e.g., identity, doubling, shifted odd).</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>{@code
 * IndexMapper<Integer> mapper = IntegerIndexMapper.DOUBLE;
 * System.out.println(mapper.map(5)); // Outputs: 10
 * }</pre>
 */
package mathTools.indexMapping;