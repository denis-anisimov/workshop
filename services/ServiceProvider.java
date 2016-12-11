package test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ServiceLoader;

/**
 * Registers annotated class as a service implementation.
 * <p>
 * Annotated class must implement or extend all classes declared by the
 * annotation value and have default public constructor.
 * <p>
 * Class will be registered in META-INF/services and can be loaded by
 * {@link ServiceLoader#load(Class)}.
 *
 * @author Vaadin Ltd
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface ServiceProvider {

    /**
     * Gets service provider classes.
     *
     * @return service provider classes
     */
    Class<?>[] value();
}