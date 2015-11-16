package com.manish.navigationdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.manish.navigationdrawer.items.ListItem;
import com.example.slidemenuframework.R;

import java.util.List;

/**
 * Created by Manish on 12/30/2014.
 */
public class ListItemAdapter extends ArrayAdapter<ListItem> {

    /** The inflater used to inflate custom views in list */
    private LayoutInflater inflater;
    public int currentSelection;
    private int customLayout;
   // private ArrayList<ListItem> list;

    /**
     * Instantiates a new List adapter.
     */
    public ListItemAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.customLayout=resource;
      //  this.list=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null)
        {
            convertView = inflater.inflate(customLayout, parent,
                    false);
            TextView headingView = (TextView) convertView
                    .findViewById(R.id.headingView);
            TextView smallLabel = (TextView) convertView
                    .findViewById(R.id.smalllabelview);

            holder=new ViewHolder();    //setting the holder objects with current item views//
            holder.heading=headingView;
            holder.smallLabel=smallLabel;
            convertView.setTag(holder);
        }
        if(holder==null)
        {
            holder=(ViewHolder)convertView.getTag();
        }
        ListItem item=getItem(position);
        holder.heading.setText(item.getHeadingText());
        holder.smallLabel.setText(item.getSmallText());
        return convertView;
    }

    /*Holder class for holding object of each item in list*/
    private static class ViewHolder{
        TextView heading;
        TextView smallLabel;
    }
}
