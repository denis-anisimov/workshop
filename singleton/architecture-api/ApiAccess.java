package api;

/**
 * @author denis
 *
 */
public final class ApiAccess {

    private ApiAccess() {
    }

    private static final ApiAccess INSTANCE = new ApiAccess();

    public static ApiAccess getInstance() {
        return INSTANCE;
    }

    public ApiClass getApi(String id) {
        return ApiClassFactory.INSTANCE.create(id);
    }

    /**
     * WARNING: test only method
     */
    public static void main(String[] args) {
        ApiClass api = ApiAccess.getInstance().getApi("my-id");
        System.out.println("Id is " + api.getId());
    }

    public static abstract class ApiClassFactory {

        protected static ApiClassFactory INSTANCE;

        static {
            loadImpl();
        }

        protected abstract ApiClass create(String id);

        private static void loadImpl() {
            try {
                Class.forName(ApiClass.class.getName() + "$ApiClassFactoryImpl");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
