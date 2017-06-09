package com.example.aghil.tobeortohave.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aghil.tobeortohave.R;
import com.example.aghil.tobeortohave.model.DetailItem;
import com.example.aghil.tobeortohave.model.Graph;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aghil on 14/05/2017.
 */

public class BarChartAdapter extends BaseAdapter {
    private List<Graph> graphList;
    private Context context;
    private LayoutInflater layoutInflater;
    private Resources resources;
    private Map<Integer,String> map =new HashMap<>();

    public BarChartAdapter (Context context,Resources resources,List<Graph> event){
        this.graphList = event;
        this.context = context;
        this.resources = resources;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        map.put(1,"entrée :");
        map.put(2,"sortie :");
        map.put(3,"Le nombre de personne dans le centre commercial :");
        map.put(4,"#00FF00");
        map.put(5,"#FF0000");
        map.put(6,"#0000ff");
    }

    @Override
    public int getCount() {
        return graphList.size();
    }

    @Override
    public Object getItem(int position) {
        return graphList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;
            BarChartAdapter.ViewHolder viewHolder;
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.graph, parent, false);
                viewHolder = new BarChartAdapter.ViewHolder();
                viewHolder.barChart = (BarChart) v.findViewById(R.id.bargraph);
                viewHolder.textgraph = (TextView) v.findViewById(R.id.textgraph);
                v.setTag(viewHolder);
            } else {
                viewHolder = (BarChartAdapter.ViewHolder) v.getTag();
            }
            Graph news = (Graph) getItem(position);
            BarDataSet barDataSet = new BarDataSet(news.getList(), "Dates");
            barDataSet.setColor(Color.parseColor(map.get(news.getId() + 3)));
            barDataSet.setValueTextSize(20);
            BarData barData = new BarData(barDataSet);
            viewHolder.textgraph.setText(map.get(news.getId()));
            viewHolder.barChart.setData(barData);
            return v;
    }


    private class ViewHolder{
        public BarChart barChart;
        public TextView textgraph;
    }
}
