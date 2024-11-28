package mathTools.indexMapping;

/**
 * Represents a function for transforming an indexMapping value.
 *
 * <p>This functional interface defines a mapping operation on an indexMapping of type {@code T}.
 * The transformation logic can be implemented as a lambda or method reference.
 *
 * <p>Example usage:
 * <pre>{@code
 * IndexMapper<Integer> doubleIndex = indexMapping -> indexMapping * 2;
 * System.out.println(doubleIndex.map(5)); // Outputs: 10
 * }</pre>
 *
 * @param <T> the type of the indexMapping value, must extend {@link Number}.
 */
@FunctionalInterface
public interface IndexMapper<T extends Number> {

    /**
     * Transforms the given indexMapping according to the implemented logic.
     *
     * @param index the indexMapping value to transform, must not be {@code null}.
     * @return the transformed indexMapping value.
     * @throws NullPointerException if the input indexMapping is {@code null}.
     */
    T map(T index);
}
