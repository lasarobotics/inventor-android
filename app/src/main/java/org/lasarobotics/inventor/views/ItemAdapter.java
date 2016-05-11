package org.lasarobotics.inventor.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.lasarobotics.inventor.R;
import org.lasarobotics.inventor.util.Item;

import java.util.ArrayList;

/**
 * Created by ehsan on 9/15/15.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    ArrayList<Item> items;
    Context context;
    int resource;
    public ItemAdapter(Context context, int resource, ArrayList<Item> items) {
        super(context, resource);
        this.items = items;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item i = items.get(position);
        if (convertView == null){
            convertView = View.inflate(context, resource,null);
        }
        TextView name = (TextView) convertView.findViewById(R.id.title);
        name.setText(i.name);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        quantity.setText("x" +i.quantity);
        return convertView;
    }
    @Override
    public int getCount() {
        return items.size();
    }

}