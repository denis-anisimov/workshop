package com.example.test;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

/**
 * @author denis
 *
 */
public class MyComponent extends VerticalLayout {

    public MyComponent() {
        myButton.addClickListener(new ButtonClickListener());
    }

    private static class ButtonClickListener implements ClickListener {

        @Override
        public void buttonClick(ClickEvent event) {
            // TODO do something

        }

    }

    private Button myButton;
}
