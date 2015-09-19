package com.parse.starter;

import android.graphics.drawable.Drawable;
import android.widget.CheckBox;

public class RowData {
    String text;
    String slot;
    private Boolean selected = false;


    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getTex() {
        return text;
    }

    public void setTex(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }



}