package com.example.aghil.tobeortohave.fragment;

/**
 * Created by aghil on 15/05/2017.
 */


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;

import com.example.aghil.tobeortohave.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyDialogFragment extends DialogFragment {
    private final LatLng latLng;
    private final String title;

    public MyDialogFragment(LatLng latLng,String title) {
        this.title = title;
        this.latLng =latLng;
    }
    @Override
    public void onStart(){
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(windowParams);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog builder = new Dialog(getActivity());
        builder.setTitle("Localisation des evenements");
        builder.setContentView(R.layout.activity_maps);
        builder.setCancelable(true);
        MapView mapView = (MapView) builder.findViewById(R.id.map);
        MapsInitializer.initialize(getActivity());
        mapView.onCreate(builder.onSaveInstanceState());
        mapView.onResume();
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                LatLng posisition = latLng; ////your lat lng
                googleMap.addMarker(new MarkerOptions().position(posisition).title(title));
            }
        });
        setStyle(DialogFragment.STYLE_NORMAL,0);
        return builder;
    }

    public static MyDialogFragment newInstance(LatLng latLng,String title) {
        MyDialogFragment f = new MyDialogFragment(latLng,title);
        return f;
    }

}