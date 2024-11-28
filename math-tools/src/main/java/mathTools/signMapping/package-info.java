/**
 * Provides classes and interfaces for computing and applying numerical signs.
 *
 * <p>This package contains tools for defining custom sign computation and application
 * based on an index value. The core interface {@link mathTools.signMapping.SignMapper}
 * defines a functional contract for implementing custom sign logic, while predefined
 * implementations like {@link mathTools.signMapping.IntegerSignMapper}
 * offer standard behavior for integer indices.
 *
 * <p>Key components:
 * <ul>
 *     <li>{@link mathTools.signMapping.SignMapper} — the primary functional
 *     interface for sign computation and application.</li>
 *     <li>{@link mathTools.signMapping.IntegerSignMapper} — an enumeration
 *     with predefined sign mappings for integer indices (e.g., alternating, always positive,
 *     always negative).</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>{@code
 * SignMapper<Integer> mapper = IntegerSignMapper.ALTERNATING;
 * System.out.println(mapper.applySign(3, 10.0)); // Outputs: -10.0
 * }</pre>
 */
package mathTools.signMapping;