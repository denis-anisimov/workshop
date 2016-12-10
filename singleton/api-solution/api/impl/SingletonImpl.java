package api.impl;

import api.AbstractSingleton;
import api.Model;

/**
 * @author denis
 *
 */
final class SingletonImpl extends AbstractSingleton {

    static {
        setInstance(new SingletonImpl());
    }

    private final ModelImpl myModel;

    private SingletonImpl() {
        myModel = new ModelImpl();
    }

    @Override
    public Model getModel() {
        return myModel;
    }
}
