package mathTools.accuracy;


/**
 * Provides utility methods for transforming numerical accuracy parameters, such as scale,
 * precision, and the position of the least significant digit.
 *
 * <p>This class includes static methods to convert between different representations
 * of accuracy. These transformations are commonly used in mathematical and numerical
 * applications to ensure consistent computations.
 *
 * <p>The "least significant digit position" refers to the position of the least significant
 * digit in a number's representation. For example, for a scale of {@code -2}, the least
 * significant digit is at position {@code 2}.
 *
 * <p>The class is not instantiable and is intended to be used statically.
 */
public class AccuracyTransformations {

    /**
     * Private constructor to prevent instantiation.
     */
    private AccuracyTransformations() {
    }

    /**
     * Converts the scale to the position of the least significant digit.
     *
     * <p>The position is calculated as the negation of the scale:
     * {@code position = -scale}.
     *
     * @param scale the scale to convert.
     * @return the position of the least significant digit.
     */
    public static int getLeastDigitPositionByScale(int scale) {
        return -scale;
    }

    /**
     * Converts the position of the least significant digit to the corresponding scale.
     *
     * <p>The scale is calculated as the negation of the position:
     * {@code scale = -position}.
     *
     * @param position the position of the least significant digit to convert.
     * @return the corresponding scale.
     */
    public static int getScaleFromLeastDigitPosition(int position) {
        return -position;
    }

    /**
     * Converts precision and order of magnitude to the position of the least significant digit.
     *
     * <p>The position is calculated as:
     * {@code position = order + 1 - precision}.
     *
     * @param precision the precision to convert, must be greater than or equal to 1.
     * @param order     the numerical order of magnitude.
     * @return the position of the least significant digit.
     * @throws ArithmeticException if {@code precision} is less than 1.
     */
    public static int getLeastDigitPositionByPrecision(int precision, int order) {
        if (precision < 1) {
            throw new ArithmeticException("Precision cannot be less than one");
        }

        return order + 1 - precision;
    }

    /**
     * Converts the position of the least significant digit and order of magnitude to precision.
     *
     * <p>The precision is calculated as:
     * {@code precision = order + 1 - position}.
     *
     * @param position the position of the least significant digit.
     * @param order the numerical order of magnitude.
     * @return the corresponding precision, which is greater than or equal to 1.
     * @throws ArithmeticException if the resulting precision is less than 1.
     */
    public static int getPrecisionFromLeastDigitPosition(int position, int order) {
        int precision = order + 1 - position;

        if (precision < 1) {
            throw new ArithmeticException("Precision less than one for position = " + position + " and order = " + order);
        }

        return precision;
    }
}
