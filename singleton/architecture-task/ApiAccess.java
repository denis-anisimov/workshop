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
        return null/* TODO : implement me ! */;
    }

}
