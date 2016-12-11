package test;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author denis
 *
 */
public class DistionaryState {

    private Map<Object, String> immutableKeys = new HashMap<>();
    private Map<Object, String> mutableKeys = new IdentityHashMap<>();

    public void addData(String id, Object data) {
        immutableKeys.put(data, id);
        mutableKeys.put(data, id);
    }

    public String getId(Object data) {
        String id = immutableKeys.get(data);
        if (id == null) {
            return mutableKeys.get(data);
        }
        return id;
    }

    public static void main(String[] args) {
        Data data1 = new Data("foo");
        Object data2 = "bar";

        BadDistionaryState example = new BadDistionaryState();
        example.addData("id1", data1);
        example.addData("id2", data2);

        System.out.println("Mutable object id :" + example.getId(data1));
        System.out.println("Immutable object id :" + example.getId("bar"));

        data1.setName("newname");

        System.out.println("Mutable object id :" + example.getId(data1));
        System.out.println("Immutable object id :" + example.getId("bar"));
    }
}

class Data {

    Data(String name) {
        myName = name;
    }

    public void setName(String name) {
        myName = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(Data.class)) {
            return Objects.equals(myName, ((Data) obj).myName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myName);
    }

    private String myName;
}
