package api;

/**
 * @author denis
 *
 */
public abstract class AbstractSingleton {

    private static AbstractSingleton INSTANCE;

    static {
        loadImpl();
    }

    protected static void setInstance(AbstractSingleton instance) {
        INSTANCE = instance;
    }

    public static AbstractSingleton getInstance() {
        return INSTANCE;
    }

    public abstract Model getModel();

    /*
     * WARNING : just for testing purpose. Must not be here ever !
     */
    public static void main(String[] args) {
        AbstractSingleton singleton = AbstractSingleton.getInstance();
        Model model = singleton.getModel();
        System.out.println("Model instance : " + model);
    }

    private static void loadImpl() {
        try {
            Class.forName("api.impl.SingletonImpl");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
