/**
 * Provides factories for creating interval-based validators with custom bounds.
 *
 * <p>This package includes the following components:
 * <ul>
 *     <li>{@link mathTools.validation.interval.factory.IntervalValidatorFactory}:
 *     A factory interface for creating validators based on user-defined intervals.</li>
 *     <li>{@link mathTools.validation.interval.factory.DelegatingIntervalValidatorFactory}:
 *     A default implementation of {@code IntervalValidatorFactory} that delegates
 *     the creation of intervals to a provided {@link mathTools.interval.factory.IntervalFactory}.</li>
 * </ul>
 *
 * <p>For predefined interval validators (e.g., positive, non-negative), see
 * {@link xyz.derivora.mathTools.validation.interval.commons}.
 *
 * <p>Example usage:
 * <pre>{@code
 * IntervalValidatorFactory factory = IntervalValidatorFactory.getDefaultInstance();
 * Validator<BigDecimal> validator = factory.createClosed(BigDecimal.ZERO, BigDecimal.TEN);
 * validator.validate(BigDecimal.valueOf(5)); // Passes validation
 * validator.validate(BigDecimal.valueOf(15)); // Throws IllegalArgumentException
 * }</pre>
 *
 * @see mathTools.validation.interval.IntervalValidator
 * @see mathTools.validation.interval.commons.CommonValidatorFactory
 */
package mathTools.validation.interval.factory;