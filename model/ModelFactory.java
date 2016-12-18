package test;

/**
 * @author denis
 *
 */
public class ModelFactory {

    private static final ModelFactory INSTANCE = new ModelFactory();

    public static ModelFactory getInstance() {
        return INSTANCE;
    }

    public Model getModel(ModelSource source) {
        return null;
    }
}
