package api;

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

    private final String myId;
}
