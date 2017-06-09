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
import com.example.aghil.tobeortohave.adapter.LivAdapter;
import com.example.aghil.tobeortohave.model.DBHelper;
import com.example.aghil.tobeortohave.model.Graph;
import com.example.aghil.tobeortohave.model.Item;
import com.example.aghil.tobeortohave.model.Livraison;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by aghil on 17/05/2017.
 */

public class ProgressBarFragment extends Fragment implements FragmentInterface{

        private Item mItem;

        public ProgressBarFragment() {
        }
        public ProgressBarFragment clone(){
            return new ProgressBarFragment();
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
            List<Livraison> list = db.getAllLiv();
            LivAdapter adapter = new LivAdapter(getContext(),getResources(),list);

            ListView listView = (ListView)rootView.findViewById(R.id.listViewEvent);
            listView.setAdapter(adapter);

            return rootView;
        }

}

