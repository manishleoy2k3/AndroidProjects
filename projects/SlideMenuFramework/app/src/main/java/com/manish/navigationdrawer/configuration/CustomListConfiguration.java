package com.manish.navigationdrawer.configuration;

import com.manish.navigationdrawer.adapters.ListBaseAdapter;

/**
 * Created by Manish on 1/20/2015.
 */
public class CustomListConfiguration {

    /** The main layout. */
    private int mainBackground;
    private int customItemLayout;
    private boolean swipeRefreshRequired;
    public static int TYPE_TWO_LABELS=1;
    public static int TYPE_TWO_LABELS_IMAGE=2;
    public static int TYPE_ONE_LABELS=3;

    public int getAdapterType() {
        return adapterType;
    }

    private int adapterType;
    public void setAdapterType(int type)
    {
        this.adapterType=type;
    }

    /** The base adapter. */
    private ListBaseAdapter baseAdapter;

    public ListBaseAdapter getBaseAdapter() {
        return baseAdapter;
    }

    public void setBaseAdapter(ListBaseAdapter baseAdapter) {
        this.baseAdapter = baseAdapter;
    }

    public int getMainBackground() {
        return mainBackground;
    }

    public void setMainBackground(int mainBackground) {
        this.mainBackground = mainBackground;
    }

    public int getCustomItemLayout() {
        return customItemLayout;
    }

    public void setCustomItemLayout(int customItemLayout) {
        this.customItemLayout = customItemLayout;
    }

    public boolean isSwipeRefreshRequired() {
        return swipeRefreshRequired;
    }

    public void setSwipeRefreshRequired(boolean swipeRefreshRequired) {
        this.swipeRefreshRequired = swipeRefreshRequired;
    }
}
