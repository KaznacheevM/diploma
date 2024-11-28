/**
 * Contains implementations and factories for predefined interval-based validators.
 *
 * <p>The main components of this package include:
 * <ul>
 *     <li>{@link mathTools.validation.interval.commons.CommonValidatorFactory}:
 *          Defines factory methods for standard interval-based validators.</li>
 *     <li>{@link mathTools.validation.interval.commons.DelegatingCommonValidatorFactory}:
 *          Provides a default implementation of {@link mathTools.validation.interval.commons.CommonValidatorFactory}
 *     using predefined intervals.</li>
 * </ul>
 *
 * <p>These classes simplify the process of creating validators for commonly used intervals,
 * such as positive, non-negative, negative, and non-positive intervals.
 */
package mathTools.validation.interval.commons;