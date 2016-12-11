package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author denis
 *
 */
public class ListenerSupport {

    private List<Listener> listeners = new ArrayList<>();

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
        ListenerSupport support = new ListenerSupport();
        support.addListener(() -> {
            support.addListener(() -> {
            });
        });
        support.fireEvent();
    }
}

interface Listener {

    void onChange();
}
