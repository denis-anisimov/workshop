package test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author denis
 *
 */
public class Model {

    public void invokeRead(ModelOperation<ModelAccess> operation) {
        myLock.readLock().lock();
        try {
            operation.accept(myAccess);
        } finally {
            myLock.readLock().unlock();
        }
    }

    public void invokeWrite(ModelOperation<ModelAccess> operation) {
        myLock.writeLock().lock();
        try {
            operation.accept(myAccess);
        } finally {
            myLock.writeLock().unlock();
        }
    }

    public <T> T lookup(Class<T> clazz) {
        return null;
    }

    public static void main(String[] args) {
        // XXXXXXXXXx : примеры - самое важное
    }

    private ModelAccess myAccess;

    private final ReentrantReadWriteLock myLock = new ReentrantReadWriteLock();
}
