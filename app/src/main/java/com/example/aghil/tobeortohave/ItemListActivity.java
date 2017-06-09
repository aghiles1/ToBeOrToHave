package com.example.aghil.tobeortohave;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aghil.tobeortohave.alarm.AutoStart;
import com.example.aghil.tobeortohave.fragment.AccueilFragment;
import com.example.aghil.tobeortohave.fragment.EventFragment;
import com.example.aghil.tobeortohave.fragment.FragmentInterface;
import com.example.aghil.tobeortohave.model.DBHelper;
import com.example.aghil.tobeortohave.model.Item;
import com.example.aghil.tobeortohave.model.Livraison;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link } representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private  DBHelper db ;
    private static List<Livraison> list;
    private final static double currentTime = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DBHelper(getBaseContext());
        try {
            db.createDataBase();
            db.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list = db.getAllLiv();
        toolbar.setTitle(getTitle());
        new AutoStart().onReceive(getBaseContext(),new Intent(this, ItemListActivity.class));
        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(Item.values()));
    }

    public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final Item[] mValues;

        public SimpleItemRecyclerViewAdapter(Item[] items) {
            mValues = items;
            Bundle arguments = new Bundle();
            Fragment fragment = new AccueilFragment() ;
            arguments.putString("item_id", String.valueOf(R.id.fond));
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mItem = mValues[position];
            holder.mIdView.setImageResource(holder.mItem.getId() );
            holder.mContentView.setText(holder.mItem.getContent() );

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        FragmentInterface fragment ;
                        arguments.putString(EventFragment.ARG_ITEM_ID, String.valueOf(holder.mItem.getId()));

                        fragment =holder.mItem.getFragment().clone();

                        fragment.setItem(holder.mItem);

                        ((Fragment)fragment).setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, (Fragment) fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ItemListActivity.class);
                        intent.putExtra("item_id", holder.mItem.getId());
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView mIdView;
            public final TextView mContentView;
            public Item mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (ImageView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

    public void envoie(View target){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"dziriaghiles1@gmail.com"});
        EditText edit=(EditText) findViewById(R.id.edit_text);
        EditText b=(EditText) findViewById(R.id.object);
        i.putExtra(Intent.EXTRA_SUBJECT, "CapSophia - "+b.getText());
        i.putExtra(Intent.EXTRA_TEXT, edit.getText());
        i.setType("message/rfc822");
        startActivity(i);
    }

    public static double getCurrentTime() {
        return currentTime;
    }
    public static List getLiv(){
       return list;
    }

}
