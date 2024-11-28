/**
 * Provides tools for managing and transforming numerical accuracy in computations.
 *
 * <p>This package includes classes and utilities to handle various aspects of numerical
 * accuracy, such as precision, scale, and the position of significant digits. These tools
 * are designed to ensure consistency and safety in numerical calculations, preventing issues
 * like arithmetic overflows and maintaining precision across different contexts.
 *
 * <p>Key components:
 * <ul>
 *     <li>{@link mathTools.accuracy.AccuracyStrategy}:
 *     Defines strategies for adjusting and interpreting numerical accuracy,
 *     such as positional accuracy, decimal places, and significant figures.</li>
 *     <li>{@link mathTools.accuracy.AccuracyTransformations}:
 *     Provides utility methods for converting between precision, scale, and the position of
 *     significant digits.</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>{@code
 * int position = AccuracyTransformations.getLeastDigitPositionByScale(-2); // Outputs: 2
 * int adjustedAccuracy = AccuracyStrategy.POSITIONAL.adjustAccuracy(5);   // Outputs: 4
 * }</pre>
 *
 * <p>The package is useful in scenarios requiring high precision or specific numerical accuracy
 * handling, such as scientific computations or financial calculations.
 */
package mathTools.accuracy;