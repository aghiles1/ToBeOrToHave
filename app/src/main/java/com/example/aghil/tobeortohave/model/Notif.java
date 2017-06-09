package com.example.aghil.tobeortohave.model;

/**
 * Created by aghil on 10/05/2017.
 */

public class Notif {
    private String text;
    private int image;
    private boolean isChecked;

    public Notif(String text, int image) {
        this.text = text;
        this.image = image;
        this.isChecked =true;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

}
