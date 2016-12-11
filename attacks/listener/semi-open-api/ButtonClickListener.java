package com.example.test;

import java.util.UUID;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

class ButtonClickListener implements ClickListener {

    ButtonClickListener(MyComponent component) {
        myComponent = component;
    }

    @Override
    public void buttonClick(ClickEvent event) {
        // update caption dynamically
        myComponent.updateCaption(UUID.randomUUID().toString());
    }

    private final MyComponent myComponent;

}