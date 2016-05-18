package com.example.root.habbits.model;

import android.widget.Button;

/**
 * Created by root on 2016/5/10.
 */
public class ContentBean {
    private String name;
    private String number;
    private Button mButton;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Button getmButton() {
        return mButton;
    }

    public void setmButton(Button mButton) {
        this.mButton = mButton;
    }
}
