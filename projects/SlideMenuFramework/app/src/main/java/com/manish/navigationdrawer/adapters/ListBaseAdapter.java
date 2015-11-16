package com.manish.navigationdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.manish.navigationdrawer.items.ListItem;

import java.util.List;

/**
 * Created by Manish on 1/20/2015.
 */
public abstract class ListBaseAdapter extends ArrayAdapter<ListItem> {

    /** The inflater used to inflate custom views in list */
    private LayoutInflater inflater;
    public int currentSelection;

    protected abstract View getItemView(LayoutInflater inflater,View convertView, ViewGroup parentView,ListItem item);

    /**
     * Instantiates a new List adapter.
     */
    public ListBaseAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem item = this.getItem(position);
        View view=getItemView(inflater,convertView,parent,item);
        return view;
    }
}
