package api;

import api.ApiAccess.ApiClassFactory;

/**
 * @author denis
 *
 */
public final class ApiClass {

    private ApiClass(String id) {
        myId = id;
    }

    public final String getId() {
        return myId;
    }

    private static class ApiClassFactoryImpl extends ApiClassFactory {

        static {
            INSTANCE = new ApiClassFactoryImpl();
        }

        @Override
        protected ApiClass create(String id) {
            return new ApiClass(id);
        }

    }

    private final String myId;
}
