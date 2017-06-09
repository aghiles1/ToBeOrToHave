package com.example.aghil.tobeortohave.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.aghil.tobeortohave.R;
import com.example.aghil.tobeortohave.model.DetailItem;
import com.example.aghil.tobeortohave.model.Notif;

import java.util.List;

/**
 * Created by aghil on 10/05/2017.
 */

public class NotifAdapter extends BaseAdapter {

    private List<Notif> notifs;
    private Context context;
    private LayoutInflater layoutInflater;
    private Resources resources;

    public NotifAdapter (Context context,Resources resources,List<Notif> notifs){
        this.notifs = notifs;
        this.context = context;
        this.resources = resources;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return notifs.size();
    }

    @Override
    public Object getItem(int position) {
        return notifs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final NotifAdapter.ViewHolder viewHolder;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.notification, parent, false);
            viewHolder = new NotifAdapter.ViewHolder();
            viewHolder.textView = (TextView)v.findViewById(R.id.notif_text);
            viewHolder.imageView = (ImageView)v.findViewById(R.id.notif_image);
            viewHolder.aSwitch = (Switch) v.findViewById(R.id.aswitch);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (NotifAdapter.ViewHolder)v.getTag();
        }
        final Notif news = (Notif) getItem(position);
        viewHolder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                news.setChecked(isChecked);
            }
        });
        viewHolder.aSwitch.setChecked(news.getIsChecked());
        viewHolder.textView.setText(news.getText());
        viewHolder.imageView.setImageResource(news.getImage());
        return v;
    }


    private class ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public Switch aSwitch;
    }
}
