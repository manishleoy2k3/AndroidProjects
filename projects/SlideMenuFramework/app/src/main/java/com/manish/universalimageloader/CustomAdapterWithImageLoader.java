package com.manish.universalimageloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slidemenuframework.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Manish on 1/15/2015.
 */
public class CustomAdapterWithImageLoader extends ArrayAdapter<ImageListItemInterface> {

    /**
     * The inflater.
     */
    private LayoutInflater inflater;
    private DisplayImageOptions options;

    /**
     * Instantiates a new nav drawer adapter.
     *
     * @param context            the context
     * @param textViewResourceId the text view resource id
     * @param objects            the objects
     */
    public CustomAdapterWithImageLoader(Context context, int textViewResourceId,
                                        ImageListItemInterface[] objects, DisplayImageOptions options) {
        super(context, textViewResourceId, objects);
        this.options=options;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ImageListItemInterface item = this.getItem(position);
            view = getItemView(position,convertView, parent, item);
        return view;
    }

    /**
     * Gets the item view.
     *
     * @param convertView   the convert view
     * @param parentView    the parent view
     * @param item the nav drawer item
     * @return the item view
     */
    public View getItemView(int position,View convertView, ViewGroup parentView,
                            ImageListItemInterface item) {

        ImageListItem listItem = (ImageListItem) item;
        ImageListItemHolder imagelistItemHolder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.imagelistlayout, parentView,
                    false);
            TextView labelView = (TextView) convertView
                    .findViewById(R.id.labelitem);
            ImageView iconView = (ImageView) convertView
                    .findViewById(R.id.imageViewCustom);

            imagelistItemHolder = new ImageListItemHolder();
            imagelistItemHolder.labelView = labelView;
            imagelistItemHolder.iconView = iconView;

            convertView.setTag(imagelistItemHolder);
        }

        if (imagelistItemHolder == null) {
            imagelistItemHolder = (ImageListItemHolder) convertView.getTag();
        }

        ImageLoader.getInstance()
                .displayImage(listItem.getUrl(), imagelistItemHolder.iconView, options);
        imagelistItemHolder.labelView.setText("Image "+(position+1));
        return convertView;
    }


    /**
     * The Class NavMenuItemHolder.
     */
    private static class ImageListItemHolder {

        /**
         * The label view.
         */
        private TextView labelView;

        /**
         * The icon view.
         */
        private ImageView iconView;
    }
}
