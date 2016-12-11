package test;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * @author denis
 *
 */
public class GridTask<T> {

    /*
     * TODO: Do whatever you want with this enum
     */
    public enum SelectionMode {
        SINGLE, MULTI, NONE
    }

    /*
     * TODO : have no idea which methods are needed, which signature they have.
     *
     * I want to be able to set selection model via SelectionMode enum value and
     * ANY custom SelectionModel (even my own implementation).
     *
     * Which contract to use ?
     *
     */

}

class SingleSelectionModel<T> implements SelectionModel<T> {
    /*
     * I have my own selection model impl
     */
}

class MultiSelectionModel<T> implements SelectionModel<T> {
    /*
     * I have my own selection model impl
     */
}

class NoneSelectionModel<T> implements SelectionModel<T> {
    /*
     * No custom impl. Just use default impl in the interface
     */
}

interface SelectionModel<T> extends Serializable {
    /**
     * Returns an immutable set of the currently selected items. It is safe to
     * invoke other {@code SelectionModel} methods while iterating over the set.
     * <p>
     * <em>Implementation note:</em> the iteration order of the items in the
     * returned set should be well-defined and documented by the implementing
     * class.
     *
     * @return the items in the current selection, not null
     */
    public default Set<T> getSelectedItems() {
        return Collections.emptySet();
    }

    /**
     * Selects the given item. Depending on the implementation, may cause other
     * items to be deselected. If the item is already selected, does nothing.
     *
     * @param item
     *            the item to select, not null
     */
    public default void select(T item) {
    }
}
