package com.example.test;

import java.util.UUID;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;

class ButtonClickListener implements ClickListener {

    ButtonClickListener(TextField field) {
        myField = field;
    }

    @Override
    public void buttonClick(ClickEvent event) {
        // update part of MyComponent's state
        myField.setValue(UUID.randomUUID().toString());
    }

    public void getMyComponentRequest() {
        // TODO : do something
    }

    private final TextField myField;

}