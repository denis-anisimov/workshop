package test;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.xml.bind.Validator;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.util.converter.ValueContext;

/**
 * A {@code Validator} using the JSR-303 (javax.validation) annotation-based
 * bean validation mechanism. Values passed to this validator are compared
 * against the constraints, if any, specified by annotations on the
 * corresponding bean property.
 * <p>
 * Note that a JSR-303 implementation (for instance
 * <a href="http://hibernate.org/validator/">Hibernate Validator</a> or
 * <a href="http://bval.apache.org/">Apache BVal</a>) must be present on the
 * project classpath when using bean validation. Specification versions 1.0 and
 * 1.1 are supported.
 *
 * @author Vaadin Ltd.
 *
 * @since 8.0
 */
public class BeanValidator implements Validator<Object> {

    private String propertyName;
    private Class<?> beanType;

    /**
     * Creates a new JSR-303 {@code BeanValidator} that validates values of the
     * specified property. Localizes validation messages using the
     * {@linkplain Locale#getDefault() default locale}.
     *
     * @param beanType
     *            the bean type declaring the property, not null
     * @param propertyName
     *            the property to validate, not null
     * @throws IllegalStateException
     *             if {@link BeanUtil#checkBeanValidationAvailable()} returns
     *             false
     */
    public BeanValidator(Class<?> beanType, String propertyName) {
        if (!BeanUtil.checkBeanValidationAvailable()) {
            throw new IllegalStateException("Cannot create a " + BeanValidator.class.getSimpleName()
                    + ": a JSR-303 Bean Validation implementation not found on theclasspath");
        }
        Objects.requireNonNull(beanType, "bean class cannot be null");
        Objects.requireNonNull(propertyName, "property name cannot be null");

        this.beanType = beanType;
        this.propertyName = propertyName;
    }

    /**
     * Validates the given value as if it were the value of the bean property
     * configured for this validator. Returns {@code Result.ok} if there are no
     * JSR-303 constraint violations, a {@code Result.error} of chained
     * constraint violation messages otherwise.
     * <p>
     * Null values are accepted unless the property has an {@code @NotNull}
     * annotation or equivalent.
     *
     * @param value
     *            the input value to validate
     * @param context
     *            the value context for validation
     * @return the validation result
     */
    @Override
    public ValidationResult apply(final Object value, ValueContext context) {
        Set<? extends ConstraintViolation<?>> violations = getJavaxBeanValidator().validateValue(beanType, propertyName,
                value);

        Locale locale = context.getLocale().orElse(Locale.getDefault());

        Optional<ValidationResult> result = violations.stream()
                .map(violation -> ValidationResult.error(getMessage(violation, locale))).findFirst();
        return result.orElse(ValidationResult.ok());
    }

    /**
     * Returns the underlying JSR-303 bean validator factory used. A factory is
     * created using {@link Validation} if necessary.
     *
     * @return the validator factory to use
     */
    protected static ValidatorFactory getJavaxBeanValidatorFactory() {
        return LazyFactoryInitializer.FACTORY;
    }

    /**
     * Returns a shared JSR-303 validator instance to use.
     *
     * @return the validator to use
     */
    protected javax.validation.Validator getJavaxBeanValidator() {
        return getJavaxBeanValidatorFactory().getValidator();
    }

    private static class LazyFactoryInitializer implements Serializable {
        private static final ValidatorFactory FACTORY = getFactory();

        private static ValidatorFactory getFactory() {
            return Validation.buildDefaultValidatorFactory();
        }

        private LazyFactoryInitializer() {
        }
    }
}