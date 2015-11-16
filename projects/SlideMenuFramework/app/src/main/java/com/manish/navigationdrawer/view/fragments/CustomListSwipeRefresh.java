package com.manish.navigationdrawer.view.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.manish.helper.ConnectionDetector;
import com.manish.navigationdrawer.adapters.ListBaseAdapter;
import com.manish.navigationdrawer.adapters.TwoLabelAdapter;
import com.manish.navigationdrawer.configuration.CustomListConfiguration;
import com.manish.navigationdrawer.items.ListItem;
import com.manish.view.activity.AsyncTaskFetchDataFromServer;
import com.manish.view.activity.onDataFromServerListener;
import com.example.slidemenuframework.R;

import java.util.ArrayList;

/**
 * Created by Manish on 1/19/2015.
 */
public abstract class CustomListSwipeRefresh extends ListFragment implements onDataFromServerListener,SwipeRefreshLayout.OnRefreshListener {

    /**Method for deleting item from the list*/
    protected abstract void deleteItem(int id);

    /**Method called when internet connection failed  while connecting to sever*/
    protected abstract void onInternetConnectionFailed();

    /**Method called when internet connection failed  while connecting to sever*/
    protected abstract CustomListConfiguration getDerivedListConfig();

    ArrayList<ListItem> itemList;
    private ListBaseAdapter adapter;
    SwipeRefreshLayout swipeLayout;
    CustomListConfiguration conFig;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemList=new ArrayList<ListItem>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.listview_swipe_refresh, null);
        conFig=getDefaultListConfig();
        swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_container);

        if(conFig.isSwipeRefreshRequired()) {
            swipeLayout.setOnRefreshListener(this);
            setAppearance();
        }
        else
        swipeLayout.setEnabled(false);

        return v;
    }

    public CustomListConfiguration getDefaultListConfig()
    {
        CustomListConfiguration config=new CustomListConfiguration();
        config.setSwipeRefreshRequired(true);
        config.setMainBackground(Color.BLACK);
        config.setCustomItemLayout(R.layout.custom_layout_list);

        if(getDerivedListConfig()!=null)
        return getDerivedListConfig();
        else
        return config;
    }

    private void setAppearance() {
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(1);//setting choice mode to CHOICE_MODE_SINGLE with constant 1//
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                getActivity().startActionMode(mCallBack);
                getListView().setItemChecked(position,true);
                view.setSelected(true);
                adapter.currentSelection = position;
                return true;
            }
        });
    }

    private ActionMode.Callback mCallBack=new ActionMode.Callback(){

        public boolean onPrepareActionMode(ActionMode mode, Menu menu){
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
            mode = null;
            getListView().setItemChecked(adapter.currentSelection,false);
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Options");
            mode.getMenuInflater().inflate(R.menu.list_menu, menu);
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id=item.getItemId();
            switch(id)
            {
                case R.id.action_delete:
                    adapter.remove(adapter.getItem(adapter.currentSelection));
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }
    };

    /**Calling async task to download data from the server*/
    public void fetchDataFromServerAsync(String url,String urlencodedParameters)
    {
        ConnectionDetector cd=new ConnectionDetector(getActivity());
        if(cd.isConnectingToInternet())
        {
            if(conFig.isSwipeRefreshRequired())
            swipeLayout.setRefreshing(true);

            AsyncTaskFetchDataFromServer task=new AsyncTaskFetchDataFromServer(getActivity(),false,url,urlencodedParameters);
            task.delegate=this;
            task.execute();
        }
        else
        {
            onInternetConnectionFailed();
        }
    }

    /**setting data to the custom adapter of listview*/
    public void setData()
    {
        if(itemList!=null)
        {
            switch(conFig.getAdapterType())
            {
                case 1:
                    adapter =new TwoLabelAdapter(getActivity(),R.layout.custom_layout_list,itemList);
                    break;
            }

          //  adapter=new ListItemAdapter(getActivity(),conFig.getCustomItemLayout(),itemList);
         //   adapter=conFig.getBaseAdapter();
            setListAdapter(adapter);
            if(conFig.isSwipeRefreshRequired())
            swipeLayout.setRefreshing(false);
        }
        else
        {
          //  setEmptyText("No Data");
        }
    }


    @Override
    public void setEmptyText(CharSequence text) {
      //  setListShown(true);  //setting list shown true because without this text will not be shown//
        super.setEmptyText(text);
    }
}

