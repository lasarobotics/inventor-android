package org.lasarobotics.inventor.views;



import org.lasarobotics.inventor.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class DrawerAdapter extends ArrayAdapter<String> {

    private String[] items;
    private LayoutInflater vi;
    private int viewResourceID;
    private Context context;
    public DrawerAdapter(Context context, int textViewResourceId, String[] items) {
        super(context, textViewResourceId, items);
        this.context = context;
        vi = LayoutInflater.from(context);
        viewResourceID = textViewResourceId;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        String g = items[position];
        if (v == null){
            LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
            v = inflater.inflate(viewResourceID, parent, false);
            TextView title = (TextView) v.findViewById(R.id.drawer_title);
            title.setText(g);
        }
        // v = g.addView(getContext(), position);
        return v;
    }

}