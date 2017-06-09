package com.example.aghil.tobeortohave.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.aghil.tobeortohave.R;
import com.example.aghil.tobeortohave.adapter.AdapterList;
import com.example.aghil.tobeortohave.model.DBHelper;
import com.example.aghil.tobeortohave.model.DetailItem;
import com.example.aghil.tobeortohave.model.Item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aghil on 03/05/2017.
 */

public class EventFragment extends Fragment implements FragmentInterface{
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Item mItem;

    public EventFragment() {
    }
    public EventFragment clone(){
        return new EventFragment();
    }

    public void setItem(Item item){
        mItem = item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(mItem.getContent());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DBHelper db = new DBHelper(getContext());
        try {
            db.createDataBase();
            db.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final List<DetailItem> list = db.getAllEvents();
        AdapterList adapterList = new AdapterList(getContext(),getResources(),list);
        View rootView = inflater.inflate(R.layout.events, container, false);

        ListView listView = (ListView)rootView.findViewById(R.id.listViewEvent);
        listView.setAdapter(adapterList);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                MyDialogFragment dialog = MyDialogFragment.newInstance( list.get(position).getLatLng(),list.get(position).getTitre());
                dialog.show(ft, "Tag");
            }
        });
        return rootView;
    }

}
