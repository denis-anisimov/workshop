package test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author denis
 *
 */
public class TransactionLock {

    public void exampleOne() {
        lock.lock();

        // now this piece of the code is protected
        oneMethod();
        anotherMethod();

        lock.unlock();
    }

    public void exampleTwo() {
        startTransaction();

        // now this piece of the code is protected
        oneMethod();
        anotherMethod();

        endTransation();
    }

    private void startTransaction() {
        lock.lock();
    }

    private void endTransation() {
        lock.unlock();
    }

    private void oneMethod() {
    }

    private void anotherMethod() {
    }

    private Lock lock = new ReentrantLock();
}
