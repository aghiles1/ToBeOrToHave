package com.example.aghil.tobeortohave.model;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by aghil on 14/05/2017.
 */

public class Graph {
    private List<BarEntry> list;
    private int id;

    public Graph(String[] tab,int id){
        this.id = id;
        this.list =new ArrayList<>();
        init(tab);
    }

    private void init(String[] tab) {
        float j = 01F;
        for (int i =0; i < tab.length;i++){
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            int hour = Integer.valueOf(format.format(calendar.getTime()).split(" ")[1].split(":")[0]);
            if (i<hour-9+3) {
                this.list.add(new BarEntry(j, Integer.valueOf(tab[i].trim())));
                j += 001;
            }
        }
    }

    public List<BarEntry> getList() {
        return list;
    }

    public int getId() {
        return id;
    }
}
