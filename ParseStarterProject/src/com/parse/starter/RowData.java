package com.parse.starter;

import com.parse.ParseObject;

public class RowData {
    private  String text;
    private String slot;
    private ParseObject obj;
  /*  public RowData(String text){
        this.setTex(text);
    }*/
    public String getSlot(){return slot;}
    public void setSlot(String slot){this.slot=slot;}

    public  String getTex() {
        return text;
    }

    public void setTex(String text) {
        this.text = text;
    }



}