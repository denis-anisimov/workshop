package com.example.test;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author denis
 *
 */
public class MyComponent extends VerticalLayout {

    public MyComponent() {
        myField = new TextField();
        myButtonListener = new ButtonClickListener(myField);
        myButton.addClickListener(myButtonListener);
    }

    private void someInternalMethod() {
        myButtonListener.getMyComponentRequest();
    }

    private Button myButton;

    private ButtonClickListener myButtonListener;

    private TextField myField;
}
