package test;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author denis
 *
 */
public class ApiClass {

    /**
     * Might be that this method is not available at all.
     *
     * As an alternative to it : some declarative or API way to register cookies
     * (adapters) in the instance.
     */
    public void setCookie(Object object) {
        myCookies.put(object.getClass(), object);
    }

    public <T> T getCookie(Class<T> clazz) {
        return clazz.cast(myCookies.get(clazz));
    }

    private Map<Class<?>, Object> myCookies = new IdentityHashMap<>();
}
