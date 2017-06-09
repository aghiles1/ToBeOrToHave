package com.example.aghil.tobeortohave.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aghil.tobeortohave.R;
import com.example.aghil.tobeortohave.model.DetailItem;
import com.example.aghil.tobeortohave.model.Livraison;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by aghil on 17/05/2017.
 */

public class LivAdapter extends BaseAdapter {
    private List<Livraison> event;
    private Context context;
    private LayoutInflater layoutInflater;
    private Resources resources;
    public LivAdapter (Context context,Resources resources,List<Livraison> event){
        this.event = event;
        this.context = context;
        this.resources = resources;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return event.size();
    }

    @Override
    public Object getItem(int position) {
        return event.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LivAdapter.ViewHolder viewHolder;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.progress_bar, parent, false);
            viewHolder = new LivAdapter.ViewHolder();
            viewHolder.textView = (TextView)v.findViewById(R.id.livtext);
            viewHolder.imageView = (ImageView)v.findViewById(R.id.livimage);
            viewHolder.progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (LivAdapter.ViewHolder)v.getTag();
        }
        Livraison item = (Livraison) getItem(position);
        viewHolder.textView.setText( LoadTime.getTime().doInBackground(item));
        viewHolder.imageView.setImageResource(R.drawable.truck);
        viewHolder.progressBar.setMax(item.getTemps()*60);
        if((item.getTemps()-(item.getTemps()-((System.currentTimeMillis()/60000.0)-item.getCurrentTime())))>=0)
            viewHolder.progressBar.setProgress((int) ((System.currentTimeMillis()/1000.0)-item.getCurrentTime()*60));
        return v;
    }


    private class ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public ProgressBar progressBar;
    }

    private static class  LoadTime extends AsyncTask<Livraison, Void, String> {
        private ImageView imageView;

        public LoadTime() {
            super();
        }
        public static LoadTime getTime(){
            return new LoadTime();
        }


        @Override
        protected  String doInBackground(Livraison... item) {
            if ((item[0].getTemps()-((System.currentTimeMillis()/60000.0)-item[0].getCurrentTime()))>=0)
                return item[0].getLiv()+" arrive dans "+(int)(item[0].getTemps()-((System.currentTimeMillis()/60000.0)-item[0].getCurrentTime()))+" min";

            return item[0].getLiv()+" est arrivée";
        }
    }
}
