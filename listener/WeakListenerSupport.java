package test;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * @author denis
 *
 */
public class WeakListenerSupport {

    private Set<Listener> listeners = Collections.newSetFromMap(new WeakHashMap<Listener, Boolean>());

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void fireEvent() {
        listeners.stream().forEach(Listener::onChange);
    }

    public static void main(String[] args) {
        WeakListenerSupport support = new WeakListenerSupport();
        support.addListener(new Listener() {

            @Override
            public void onChange() {
                System.out.println("Fired !");
            }
        });

        support.fireEvent();
    }
}

interface Listener {

    void onChange();
}
