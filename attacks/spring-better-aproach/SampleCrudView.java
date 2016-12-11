package test;

import java.awt.Button;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CssLayout;

/**
 * A view for performing create-read-update-delete operations on products.
 *
 * See also {@link SampleCrudLogic} for fetching the data, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
@SpringView(name = SampleCrudView.VIEW_NAME)
public class SampleCrudView extends CssLayout implements View {

    public static final String VIEW_NAME = "Inventory";

    private SampleCrudLogic viewLogic;

    @Autowired
    private SampleCrudLogicFactory logicFactory;

    private ProductForm form;

    private Button newProduct;

    public void editProduct(Product product) {
        if (product != null) {
            form.addStyleName("visible");
            form.setEnabled(true);
        } else {
            form.removeStyleName("visible");
            // Issue #286
            // form.setEnabled(false);
        }
        form.editProduct(product);
    }

    public void setNewProductEnabled(boolean enabled) {
        newProduct.setEnabled(enabled);
    }

    @PostConstruct
    private void init() {
        viewLogic = logicFactory.createLogic(this);

        viewLogic.init();
    }
}