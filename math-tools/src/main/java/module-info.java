/**
 * Math Tools Module
 *
 * <p>The {@code math-tools} module provides a versatile library for performing advanced
 * mathematical operations with precision and scalability. It is designed to handle a wide
 * range of numerical tasks, including large number arithmetic, order computations, accuracy
 * transformations, and interval validation.
 *
 * <p>Core features:
 * <ul>
 *     <li><b>Big Number Operations:</b>
 *     Utilities for working with {@link java.math.BigDecimal} and {@link java.math.BigInteger},
 *     including transformations, factorials, precision handling, and more.</li>
 *     <li><b>Numerical Order Handling:</b>
 *     Tools for calculating the magnitude or scale of numbers, such as their scientific
 *     notation exponents.</li>
 *     <li><b>Accuracy Management:</b>
 *     Strategies and transformations for managing numerical accuracy across different
 *     contexts, such as positional accuracy, significant figures, and decimal places.</li>
 *     <li><b>Normalization:</b>
 *     Interfaces and implementations for normalizing numerical values to consistent scales
 *     or ranges.</li>
 *     <li><b>Index and Sign Mappings:</b>
 *     Utilities for transforming indices and applying sign patterns to values, enabling
 *     flexible and modular computations.</li>
 *     <li><b>Validation:</b>
 *     Tools for validating numerical values against intervals or other constraints, ensuring
 *     correctness and robustness in computations.</li>
 * </ul>
 *
 * <p>Examples of usage:
 * <pre>{@code
 * import bigMath.mathTools.BigMath;
 * import order.mathTools.OrderUtil;
 * import interval.validation.mathTools.IntervalValidator;
 * import java.math.BigDecimal;
 * import java.math.RoundingMode;
 *
 * BigDecimal number = new BigDecimal("123.456");
 *
 * // Compute order of the number
 * int order = OrderUtil.orderOf(number);
 *
 * // Normalize the number
 * BigDecimal normalized = BigMath.significand(number);
 *
 * // Validate the number within an interval
 * IntervalValidator<BigDecimal> validator = ...;
 * validator.validate(number);
 * }</pre>
 *
 * <p>The {@code math-tools} module is structured for modular use, allowing developers
 * to include only the components they need while maintaining high performance and flexibility.
 *
 * <p>Targeted for applications requiring precise numerical computations, such as:
 * <ul>
 *     <li>Scientific modeling and simulations</li>
 *     <li>Financial calculations</li>
 *     <li>Engineering analyses</li>
 * </ul>
 */
module math.tools {
    requires transitive toolbox;

    exports mathTools.accuracy;
    exports mathTools.bigMath;
    exports mathTools.indexMapping;
    exports mathTools.interval;
    exports mathTools.interval.factory;
    exports mathTools.interval.bound;
    exports mathTools.interval.bound.factory;
    exports mathTools.interval.bound.finite;
    exports mathTools.interval.bound.infinite;
    exports mathTools.normalization;
    exports mathTools.order;
    exports mathTools.signMapping;
    exports mathTools.validation.interval;
    exports mathTools.validation.interval.factory;
    exports mathTools.interval.commons;
    exports mathTools.validation.interval.commons;
}