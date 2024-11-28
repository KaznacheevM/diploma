package mathTools.signMapping;

import java.util.Objects;

/**
 * Provides predefined {@link SignMapper} implementations for {@link Integer} indices.
 *
 * <p>This enumeration defines factory strategies for determining the signMapping of a number
 * based on an {@link Integer} indexMapping. The available strategies include:
 * <ul>
 *     <li>{@link #ALTERNATING}: Alternates the signMapping based on the indexMapping (positive for even, negative for odd).</li>
 *     <li>{@link #POSITIVE}: Always returns a positive signMapping.</li>
 *     <li>{@link #NEGATIVE}: Always returns a negative signMapping.</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>{@code
 * IntegerSignMapper mapper = IntegerSignMapper.ALTERNATING;
 * System.out.println(mapper.computeSign(3)); // Outputs: -1
 * System.out.println(mapper.computeSign(4)); // Outputs: 1
 * }</pre>
 *
 * @see SignMapper
 */
public enum IntegerSignMapper implements SignMapper<Integer> {

    /**
     * Alternates the signMapping based on the indexMapping.
     *
     * <p>The signMapping is positive for even indices and negative for odd indices.
     *
     * <p>Example:
     * <pre>{@code
     * IntegerSignMapper mapper = IntegerSignMapper.ALTERNATING;
     * System.out.println(mapper.computeSign(3)); // Outputs: -1
     * System.out.println(mapper.computeSign(4)); // Outputs: 1
     * }</pre>
     */
    ALTERNATING {
        @Override
        public int computeSign(Integer index) {
            Objects.requireNonNull(index, "Index cannot be null");
            return (index % 2 == 0) ? 1 : -1;
        }
    },

    /**
     * Always returns a positive signMapping.
     *
     * <p>This mapper ignores the indexMapping and always returns {@code 1}.
     *
     * <p>Example:
     * <pre>{@code
     * IntegerSignMapper mapper = IntegerSignMapper.POSITIVE;
     * System.out.println(mapper.computeSign(3)); // Outputs: 1
     * }</pre>
     */
    POSITIVE {
        @Override
        public int computeSign(Integer index) {
            Objects.requireNonNull(index, "Index cannot be null");
            return 1;
        }
    },

    /**
     * Always returns a negative signMapping.
     *
     * <p>This mapper ignores the indexMapping and always returns {@code -1}.
     *
     * <p>Example:
     * <pre>{@code
     * IntegerSignMapper mapper = IntegerSignMapper.NEGATIVE;
     * System.out.println(mapper.computeSign(3)); // Outputs: -1
     * }</pre>
     */
    NEGATIVE {
        @Override
        public int computeSign(Integer index) {
            Objects.requireNonNull(index, "Index cannot be null");
            return -1;
        }
    }
}
