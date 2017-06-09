package com.example.aghil.tobeortohave.fragment;

import android.support.v4.app.Fragment;

import com.example.aghil.tobeortohave.model.Item;

/**
 * Created by aghil on 07/05/2017.
 */

public interface FragmentInterface {
    public void setItem(Item item);
    public FragmentInterface clone();
}
