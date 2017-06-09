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
import com.example.aghil.tobeortohave.adapter.BarChartAdapter;
import com.example.aghil.tobeortohave.model.DBHelper;
import com.example.aghil.tobeortohave.model.DetailItem;
import com.example.aghil.tobeortohave.model.Graph;
import com.example.aghil.tobeortohave.model.Item;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aghil on 11/05/2017.
 */

public class BarGraphFragment extends Fragment implements FragmentInterface {

    private Item mItem;

    public BarGraphFragment() {
    }
    public BarGraphFragment clone(){
        return new BarGraphFragment();
    }
    public void setItem(Item item){
        this.mItem =item;
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
        DBHelper db = new DBHelper(getContext());
        try {
            db.createDataBase();
            db.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Graph> list = db.getAllInfoGraph();
        BarChartAdapter adapter = new BarChartAdapter(getContext(),getResources(),list);

        ListView listView = (ListView)rootView.findViewById(R.id.listViewEvent);
        listView.setAdapter(adapter);

        return rootView;
    }
}
