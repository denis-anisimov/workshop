package test;

import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.xml.bind.Validator;

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
 * @author Petri Hakala
 * @author Vaadin Ltd.
 *
 * @since 8.0
 */
public class BeanValidatorFirst implements Validator<Object> {

    private static Boolean beanValidationAvailable;
    private static ValidatorFactory factory;

    private String propertyName;
    private Class<?> beanType;
    private Locale locale;

    /**
     * Returns whether an implementation of JSR-303 version 1.0 or 1.1 is
     * present on the classpath. If this method returns false, trying to create
     * a {@code BeanValidator} instance will throw an
     * {@code IllegalStateException}. If an implementation is not found, logs a
     * level {@code FINE} message the first time it is run.
     *
     * @return {@code true} if bean validation is available, {@code false}
     *         otherwise.
     */
    public static boolean checkBeanValidationAvailable() {
        if (beanValidationAvailable == null) {
            try {
                Class.forName(Validation.class.getName());
                beanValidationAvailable = true;
            } catch (ClassNotFoundException e) {
                Logger.getLogger(BeanValidatorFirst.class.getName())
                        .fine("A JSR-303 bean validation implementation not found on the classpath. "
                                + BeanValidatorFirst.class.getSimpleName() + " cannot be used.");
                beanValidationAvailable = false;
            }
        }
        return beanValidationAvailable;
    }

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
     *             if {@link #checkBeanValidationAvailable()} returns false
     */
    public BeanValidatorFirst(Class<?> beanType, String propertyName) {
        this(beanType, propertyName, Locale.getDefault());
    }

    /**
     * Creates a new JSR-303 {@code BeanValidator} that validates values of the
     * specified property. Localizes validation messages using the given locale.
     *
     * @param beanType
     *            the bean class declaring the property, not null
     * @param propertyName
     *            the property to validate, not null
     * @param locale
     *            the locale to use, not null
     * @throws IllegalStateException
     *             if {@link #checkBeanValidationAvailable()} returns false
     */
    public BeanValidatorFirst(Class<?> beanType, String propertyName, Locale locale) {
        if (!checkBeanValidationAvailable()) {
            throw new IllegalStateException("Cannot create a " + BeanValidatorFirst.class.getSimpleName()
                    + ": a JSR-303 Bean Validation implementation not found on theclasspath");
        }
        Objects.requireNonNull(beanType, "bean class cannot be null");
        Objects.requireNonNull(propertyName, "property name cannot be null");

        this.beanType = beanType;
        this.propertyName = propertyName;
        setLocale(locale);
    }
}