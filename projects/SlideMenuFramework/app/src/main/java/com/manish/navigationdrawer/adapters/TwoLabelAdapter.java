package com.manish.navigationdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manish.navigationdrawer.items.ListItem;
import com.example.slidemenuframework.R;

import java.util.List;

/**
 * Created by Manish on 1/20/2015.
 */
public class TwoLabelAdapter extends ListBaseAdapter{

    private int customLayout;
    /**
     * Instantiates a new List adapter.
     */
    public TwoLabelAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.customLayout=resource;
    }

    /**
     * Gets the item view.
     *
     * @param convertView
     *            the convert view
     * @param parentView
     *            the parent view
     * @param item
     *            the ListItem item object whose values are to be shown
     * @return the item view
     */
    public View getItemView(LayoutInflater inflater,View convertView, ViewGroup parentView,
                            ListItem item)
    {
        ViewHolder holder=null;
        if(convertView==null)
        {
            convertView = inflater.inflate(customLayout, parentView,
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
