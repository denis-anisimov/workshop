package test;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ServiceAccessTest {

    @Test
    public void extendsOneService() {
        Assert.assertTrue(hasImpl(Class1.class, ClassImpl1.class));
    }

    private <T> boolean hasImpl(Class<T> service, Class<?> impl) {
        ServiceLoader<T> loader = ServiceLoader.load(service);
        for (Iterator<T> iterator = loader.iterator(); iterator.hasNext();) {
            T next = iterator.next();
            if (next.getClass().equals(impl)) {
                return true;
            }
        }
        return false;
    }
}
