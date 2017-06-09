package com.example.aghil.tobeortohave.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.aghil.tobeortohave.R;
import com.example.aghil.tobeortohave.model.DetailItem;
import com.example.aghil.tobeortohave.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aghil on 03/05/2017.
 */

public class ContactFragment extends Fragment implements FragmentInterface{

    /**
     * The dummy content this fragment is presenting.
     */

    public ContactFragment() {
    }
    public ContactFragment clone(){
        return new ContactFragment();
    }
    public void setItem(Item item){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact, container, false);
        return rootView;
    }
}
