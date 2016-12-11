package com.example.test;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 * @author denis
 *
 */
public class MyComponent extends VerticalLayout {

    public MyComponent() {
        myButton.addClickListener(new ButtonClickListener(this));
    }

    void updateCaption(String caption) {
        setCaption(caption);
    }

    private Button myButton;
}
