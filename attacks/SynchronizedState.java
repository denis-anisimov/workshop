package test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author denis
 *
 */
public class SynchronizedState {

    private Map<String, Data> dataById = new HashMap<>();
    private Map<Data, String> idByData = new HashMap<>();

    public void addData(String id, Data data) {
        dataById.put(id, data);
        idByData.put(data, id);
    }
}

class Data {

}
