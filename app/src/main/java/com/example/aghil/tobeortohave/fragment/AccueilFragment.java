package com.example.aghil.tobeortohave.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aghil.tobeortohave.R;
/**
 * Created by aghil on 04/05/2017.
 */

public class AccueilFragment extends Fragment {

    /**
     * The dummy content this fragment is presenting.
     */

    public AccueilFragment() {

    }

    public AccueilFragment clone(){
        return new AccueilFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle("Accueil");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fond, container, false);
        return rootView;
    }
}
