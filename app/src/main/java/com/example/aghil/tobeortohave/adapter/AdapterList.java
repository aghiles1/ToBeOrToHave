package com.example.aghil.tobeortohave.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aghil.tobeortohave.R;
import com.example.aghil.tobeortohave.fragment.MyDialogFragment;
import com.example.aghil.tobeortohave.model.DetailItem;
import java.util.List;

/**
 * Created by aghil on 02/05/2017.
 */

public class AdapterList extends BaseAdapter  {
    private List<DetailItem> event;
    private Context context;
    private LayoutInflater layoutInflater;
    private Resources resources;
    public AdapterList (Context context,Resources resources,List<DetailItem> event){
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
        ViewHolder viewHolder;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.info_event, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)v.findViewById(R.id.eventText);
            viewHolder.imageView = (ImageView)v.findViewById(R.id.eventImage);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)v.getTag();
        }
        DetailItem item = (DetailItem)getItem(position);
        viewHolder.textView.setText(item.getText());
        viewHolder.imageView.setImageResource(context.getResources().getIdentifier("event"+item.getImage(),"drawable",context.getPackageName()));
        return v;
    }


    private class ViewHolder{
        public ImageView imageView;
        public TextView textView;
    }

}
