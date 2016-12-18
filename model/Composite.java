package test;

import java.util.List;

/**
 * @author denis
 *
 */
public interface Composite extends Component {

    List<Component> getChildren();

    void add(Component child);

    void remove(Component child);

    <T extends Component> List<T> getComponents(Class<T> clazz);
}
