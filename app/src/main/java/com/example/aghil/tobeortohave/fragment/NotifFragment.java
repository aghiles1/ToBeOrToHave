package com.example.aghil.tobeortohave.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aghil.tobeortohave.R;

import com.example.aghil.tobeortohave.adapter.AdapterList;
import com.example.aghil.tobeortohave.adapter.NotifAdapter;
import com.example.aghil.tobeortohave.model.DetailItem;
import com.example.aghil.tobeortohave.model.Item;
import com.example.aghil.tobeortohave.model.Notif;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aghil on 10/05/2017.
 */

public class NotifFragment extends Fragment implements FragmentInterface{

    /**
     * The dummy content this fragment is presenting.
     */
    private Item mItem;

    public NotifFragment clone(){
        return new NotifFragment();
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
        View rootView = inflater.inflate(R.layout.events, container, false);

        final List<Notif> list = new ArrayList<>();
        NotifAdapter notifAdapter = new NotifAdapter(getContext(),getResources(),list);

        ListView listView = (ListView)rootView.findViewById(R.id.listViewEvent);
        listView.setAdapter(notifAdapter);

        list.add(new Notif(getResources().getString(R.string.evenement),R.drawable.cloche));
        list.add(new Notif(getResources().getString(R.string.livraison),R.drawable.truck));
        return rootView;
    }

}
