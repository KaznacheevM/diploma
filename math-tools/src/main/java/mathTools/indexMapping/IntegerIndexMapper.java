package mathTools.indexMapping;

import java.util.Objects;

/**
 * Provides predefined {@link IndexMapper} implementations for {@link Integer} indices.
 *
 * <p>This enumeration defines factory mappings for integer indices, including:
 * <ul>
 *     <li>{@link #IDENTITY}: Maps an indexMapping to itself.</li>
 *     <li>{@link #DOUBLE}: Maps an indexMapping to its double.</li>
 *     <li>{@link #SHIFTED_ODD}: Maps an indexMapping to the next odd number by shifting upward.</li>
 * </ul>
 *
 * <p>Each mapping is implemented as an enumeration constant, allowing for concise
 * and reusable transformation logic.
 *
 * <p>Example usage:
 * <pre>{@code
 * IntegerIndexMapper mapper = IntegerIndexMapper.DOUBLE;
 * System.out.println(mapper.map(3)); // Outputs: 6
 * }</pre>
 *
 * @see IndexMapper
 */
public enum IntegerIndexMapper implements IndexMapper<Integer> {

    /**
     * Maps an indexMapping to itself (identity mapping).
     *
     * <p>Throws {@link NullPointerException} if the input indexMapping is {@code null}.
     *
     * <p>Example:
     * <pre>{@code
     * IntegerIndexMapper mapper = IntegerIndexMapper.IDENTITY;
     * System.out.println(mapper.map(5)); // Outputs: 5
     * }</pre>
     */
    IDENTITY {
        @Override
        public Integer map(Integer index) {
            Objects.requireNonNull(index, "Index cannot be null");
            return index;
        }
    },

    /**
     * Maps an indexMapping to its double.
     *
     * <p>Throws {@link NullPointerException} if the input indexMapping is {@code null}.
     *
     * <p>This mapping takes an integer indexMapping {@code n} and computes {@code 2 * n}.
     *
     * <p>Example:
     * <pre>{@code
     * IntegerIndexMapper mapper = IntegerIndexMapper.DOUBLE;
     * System.out.println(mapper.map(4)); // Outputs: 8
     * }</pre>
     */
    DOUBLE {
        @Override
        public Integer map(Integer index) {
            Objects.requireNonNull(index, "Index cannot be null");
            return 2 * index;
        }
    },

    /**
     * Maps an indexMapping to the next odd number by shifting upward.
     *
     * <p>Throws {@link NullPointerException} if the input indexMapping is {@code null}.
     *
     * <p>This mapping takes an integer indexMapping {@code n} and computes {@code 2 * n + 1},
     * resulting in the next odd number greater than or equal to {@code 1}.
     *
     * <p>Example:
     * <pre>{@code
     * IntegerIndexMapper mapper = IntegerIndexMapper.SHIFTED_ODD;
     * System.out.println(mapper.map(3)); // Outputs: 7
     * }</pre>
     */
    SHIFTED_ODD {
        @Override
        public Integer map(Integer index) {
            Objects.requireNonNull(index, "Index cannot be null");
            return 2 * index + 1;
        }
    }
}
