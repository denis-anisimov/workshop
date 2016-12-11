package test;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * @author denis
 *
 */
public class Grid<T> {

    public enum SelectionMode implements ModelFactory {
        SINGLE {
            @Override
            public <T> SelectionModel<T> createModel() {
                return new SingleSelectionModel<>();
            }
        },
        MULTI {
            @Override
            public <T> SelectionModel<T> createModel() {
                return new MultiSelectionModel<>();
            }
        },
        NONE {
            @Override
            public <T> SelectionModel<T> createModel() {
                return new NoneSelectionModel<>();
            }
        };
    }

    public void setSelectionModelFactory(ModelFactory factory) {
        myFactory = factory;
        setSelectionModel(factory.createModel());
    }

    public ModelFactory getSelectionModelFactory() {
        return myFactory;
    }

    public SelectionModel<T> getSelectionModel() {
        return myModel;
    }

    private void setSelectionModel(SelectionModel<T> model) {
        myModel = model;
    }

    public static void main(String[] args) {
        Grid<String> grid = new Grid<>();
        grid.setSelectionModelFactory(SelectionMode.SINGLE);
        System.out.println("Model is : " + grid.getSelectionModel());
    }

    private ModelFactory myFactory;
    private SelectionModel<T> myModel;
}

interface ModelFactory {
    <T> SelectionModel<T> createModel();
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
