package test;

import java.io.Serializable;

import com.vaadin.framework8.samples.SampleUI;
import com.vaadin.spring.annotation.SpringComponent;

/**
 * This class provides an interface for the logical operations between the CRUD
 * view, its parts like the product editor form and the data provider, including
 * fetching and saving products.
 *
 * Having this separate from the view makes it easier to test various parts of
 * the system separately, and to e.g. provide alternative views for the same
 * data.
 */
@SpringComponent
public class SampleCrudLogic implements Serializable {

    private SampleCrudView view;

    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @SpringComponent
    public static class SampleCrudLogicFactory {

        @Autowired
        private ApplicationContext context;

        public SampleCrudLogic createLogic(SampleCrudView view) {
            SampleCrudLogic logic = context.getBean(SampleCrudLogic.class);
            logic.init(view);
            return logic;
        }
    }

    private SampleCrudLogic() {
    }

    public void init() {
        editProduct(null);
        // Hide and disable if not admin
        if (!SampleUI.get().getAccessControl().isUserInRole("admin")) {
            view.setNewProductEnabled(false);
        }
    }

    public void editProduct(Product product) {
        if (product == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(product.getId() + "");
        }
        view.editProduct(product);
    }

    private void init(SampleCrudView view) {
        this.view = view;
    }
}