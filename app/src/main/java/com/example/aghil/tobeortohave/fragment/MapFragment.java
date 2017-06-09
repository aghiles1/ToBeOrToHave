package com.example.aghil.tobeortohave.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aghil.tobeortohave.R;
import com.example.aghil.tobeortohave.model.DBHelper;
import com.example.aghil.tobeortohave.model.DetailItem;
import com.example.aghil.tobeortohave.model.Item;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by aghil on 10/05/2017.
 */

public class MapFragment extends Fragment implements FragmentInterface, OnMapReadyCallback {
    private GoogleMap mMapa;
    private View view;
    private static Dialog builder;
    public MapFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        builder = new Dialog(getContext());
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
        View rootView = inflater.inflate(R.layout.map, container, false);
        this.view = inflater.inflate(R.layout.info_event, container, false);
            if( mMapa == null ){
                SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);

                if( smf != null ){
                    Toast.makeText(getActivity(), "Let's go!!", Toast.LENGTH_SHORT).show();
                    smf.getMapAsync(this);
                }else{
                    Toast.makeText(getActivity(), "SMF is null...", Toast.LENGTH_SHORT).show();
                }
            }
        return rootView;
    }
    private void setUpMap() {
        Toast.makeText(getActivity(), "Configure the map", Toast.LENGTH_SHORT).show();
        final DBHelper db = new DBHelper(getContext());
        try {
            db.createDataBase();
            db.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final List<DetailItem> list = db.getAllEvents();
        LatLng sydney = new LatLng(43.658772, 7.1974950000000035);;
        for (int i=0; i< list.size();i++){
            sydney= list.get(i).getLatLng();
            mMapa.addMarker(new MarkerOptions().position(sydney).title(list.get(i).getTitre()));
        }
        mMapa.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker arg0) {
                // call an activity(xml file)
                builder.setTitle("Localisation des evenements");
                ImageView imageView = (ImageView)view.findViewById(R.id.eventImage);
                TextView textView = (TextView)view.findViewById(R.id.eventText);
                List<DetailItem> list1 =db.getAllEvents();
                DetailItem detailItem =list1.get(list1.indexOf(new DetailItem("",arg0.getTitle(),0.,0.,"")));
                imageView.setImageResource(getResources().getIdentifier("event"+detailItem.getImage(),"drawable",getContext().getPackageName()));
                textView.setText(detailItem.getText());
                builder.setContentView(view);
                builder.setCancelable(true);
                builder.show();
            }

        });

        mMapa.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void setItem(Item item) {

    }

    public MapFragment clone(){
        return new MapFragment();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMapa = googleMap;

        setUpMap();
    }
}