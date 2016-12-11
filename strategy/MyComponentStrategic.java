package test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;

/**
 * @author denis
 */
public class MyComponentStrategic extends CssLayout {

    public MyComponentStrategic() {
        myButton = new CheckBox("Change behavior");
        myButton.addValueChangeListener(this::switchBehavior);
    }

    private void switchBehavior( ValueChangeEvent event ) {
        Boolean value = (Boolean) event.getProperty().getValue();
        if (value) {
            handle(isSomeConditionTrue(), "a", new OneStrategy());
        }
        else {
            handle(isAnotherConditionTrue(), "b", new AnotherStrategy());
        }
    }

    private void handle( boolean condition, String filePath,
            Function<String, InputStream> converter )
    {
        if (condition) { // <- can be also some strategy e.g. Supplier<Boolean>
            InputStream inputStream = converter.apply(readData());
            Path path = new File(filePath).toPath();
            try {
                Files.copy(inputStream, path);
            }
            catch (IOException e) {
                Logger.getLogger(MyComponentStrategic.class.getName())
                        .log(Level.WARNING, null, e);
            }
        }
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

    private static class OneStrategy implements Function<String, InputStream> {

        @Override
        public InputStream apply( String data ) {
            return convertOneWay(data);
        }

        private InputStream convertOneWay( String data ) {
            return null;
        }

    }

    private static class AnotherStrategy
            implements Function<String, InputStream>
    {

        private InputStream convertAnotherWay( String data ) {
            return null;
        }

        @Override
        public InputStream apply( String data ) {
            return convertAnotherWay(data);
        }

    }

    private CheckBox myButton;

}
