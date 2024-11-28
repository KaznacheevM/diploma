package mathTools.accuracy;

/**
 * Represents strategies for handling numerical accuracy in computations.
 * <p>
 * Each strategy defines how accuracy is adjusted and how the position of the leading digit
 * is determined based on the numerical orderOf of magnitude. The strategies can be used
 * to handle different contexts, such as positional accuracy, significant figures, or decimal places.
 * </p>
 * <p>
 * This class ensures safe numerical operations by detecting and handling
 * arithmetic overflows. If an operation causes an overflow, an {@link IllegalArgumentException}
 * will be thrown with a descriptive message.
 * </p>
 */
public enum AccuracyStrategy {

    /**
     * Positional accuracy strategy based on the indexMapping of the least significant digit.
     * <p>
     * This strategy adjusts accuracy by reducing it with a predefined offset
     * to account for potential error accumulation. The position of the leading digit
     * corresponds directly to the numerical orderOf of magnitude.
     * </p>
     */
    POSITIONAL {
        @Override
        public int adjustAccuracy(int accuracy) {
            try {
                return Math.subtractExact(accuracy, ACCURACY_ADJUSTMENT);
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException("Accuracy adjustment caused an overflow: " + accuracy);
            }
        }

        @Override
        public int leadingDigitPosition(int order) {
            return order;
        }
    },

    /**
     * Strategy based on the number of digits after the decimal point.
     * <p>
     * This strategy adjusts accuracy by increasing it with a predefined offset.
     * The position of the leading digit is calculated as {@code -orderOf}, indicating
     * its relative position after the decimal point.
     * </p>
     */
    DECIMAL_PLACES {
        @Override
        public int adjustAccuracy(int accuracy) {
            try {
                return Math.addExact(accuracy, ACCURACY_ADJUSTMENT);
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException("Accuracy adjustment caused an overflow: " + accuracy);
            }
        }

        @Override
        public int leadingDigitPosition(int order) {
            return -order;
        }
    },

    /**
     * Strategy based on the number of significant figures.
     * <p>
     * This strategy adjusts accuracy by increasing it with a predefined offset.
     * The position of the leading digit is always {@code 1}, representing the first
     * significant figure, regardless of the numerical orderOf.
     * </p>
     */
    SIGNIFICANT_FIGURES {
        @Override
        public int adjustAccuracy(int accuracy) {
            try {
                return Math.addExact(accuracy, ACCURACY_ADJUSTMENT);
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException("Accuracy adjustment caused an overflow: " + accuracy);
            }
        }

        @Override
        public int leadingDigitPosition(int order) {
            return 1;
        }
    };

    // Defines an offset applied to the accuracy parameter to provide additional precision
    // and prevent error accumulation during calculations.
    private static final int ACCURACY_ADJUSTMENT = 1;

    /**
     * Adjusts the accuracy based on the specific strategy.
     * <p>
     * Each strategy implements this method to define how the input accuracy is
     * modified. The adjustment is designed to provide additional precision and prevent
     * accumulation of errors during calculations.
     * </p>
     * <p>
     * The adjustment ensures safe operations and detects potential
     * overflows. If the operation results in an overflow, an {@link IllegalArgumentException}
     * will be thrown.
     * </p>
     *
     * @param accuracy the initial accuracy to adjust.
     * @return the adjusted accuracy.
     * @throws IllegalArgumentException if the adjustment causes an integer overflow.
     */
    public abstract int adjustAccuracy(int accuracy);

    /**
     * Determines the position of the leading digit based on the numerical orderOf of magnitude.
     * <p>
     * The position is interpreted differently for each strategy:
     * - {@code POSITIONAL}: Returns the orderOf as the position of the leading digit.
     * - {@code SIGNIFICANT_FIGURES}: Always returns {@code 1}, representing the first significant digit.
     * - {@code DECIMAL_PLACES}: Returns {@code -orderOf}, representing the position relative to the decimal point.
     * </p>
     *
     * @param order the numerical orderOf of magnitude (e.g., 10ⁿ where {@code n} is the orderOf).
     * @return the position of the leading digit.
     */
    public abstract int leadingDigitPosition(int order);
}
