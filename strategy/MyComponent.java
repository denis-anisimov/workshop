package test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;

/**
 * @author denis
 */
public class MyComponent extends CssLayout {

    public MyComponent() {
        myButton = new CheckBox("Change behavior");
        myButton.addValueChangeListener(this::switchBehavior);
    }

    private void switchBehavior( ValueChangeEvent event ) {
        Boolean value = (Boolean) event.getProperty().getValue();
        if (value) {
            doSomethingOneCase();
        }
        else {
            doSomethingAnotherCase();
        }
    }

    private void doSomethingOneCase() {
        if (isSomeConditionTrue()) {
            String data = readData();
            InputStream inputStream = convertOneWay(data);
            Path path = new File("a").toPath();
            try {
                Files.copy(inputStream, path);
            }
            catch (IOException e) {
                Logger.getLogger(MyComponent.class.getName()).log(Level.WARNING,
                        null, e);
            }
        }
    }

    private void doSomethingAnotherCase() {
        if (isAnotherConditionTrue()) {
            String data = readData();
            InputStream inputStream = convertAnotherWay(data);
            Path path = new File("b").toPath();
            try {
                Files.copy(inputStream, path);
            }
            catch (IOException e) {
                Logger.getLogger(MyComponent.class.getName()).log(Level.WARNING,
                        null, e);
            }
        }
    }

    private InputStream convertOneWay( String data ) {
        return null;
    }

    private InputStream convertAnotherWay( String data ) {
        return null;
    }

    private String readData() {
        return ""/* dynamic of course */;
    }

    private boolean isSomeConditionTrue() {
        return true/* dynamic of course*/;
    }

    private boolean isAnotherConditionTrue() {
        return true/* dynamic of course*/;
    }

    private CheckBox myButton;

}
