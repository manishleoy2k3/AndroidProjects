package com.manish.navigationdrawer.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.manish.navigationdrawer.configuration.CustomListConfiguration;
import com.manish.navigationdrawer.items.ListItem;
import com.example.slidemenuframework.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * The Class FragmentTwo.
 */
@SuppressLint("InflateParams")
public class FragmentTwo extends CustomListSwipeRefresh {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String urlEncodedParameters=null;
        try {
            urlEncodedParameters = "tablename=" + URLEncoder.encode("events", "UTF-8");  //tablename is key here and "events" is value
        }
        catch(UnsupportedEncodingException e1)
        {

        }
        fetchDataFromServerAsync("http://event.infowiz.in/webapi/get_data",urlEncodedParameters);
    }


    @Override
    public void onRefresh() {

//        new Handler().postDelayed(new Runnable() {
//            @Override public void run() {
//                swipeLayout.setRefreshing(false);
//               // adp.notifyDataSetChanged();
//            }
//        }, 5000);
        String urlEncodedParameters=null;
        try {
            urlEncodedParameters = "tablename=" + URLEncoder.encode("events", "UTF-8");  //tablename is key here and "events" is value
        }
        catch(UnsupportedEncodingException e1)
        {

        }
        fetchDataFromServerAsync("http://event.infowiz.in/webapi/get_data",urlEncodedParameters);
    }

    @Override
    protected void deleteItem(int id) {

    }

    @Override
    protected void onInternetConnectionFailed() {

        Toast.makeText(getActivity(),getResources().getString(R.string.internetnotprsent),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected CustomListConfiguration getDerivedListConfig() {
        CustomListConfiguration config=new CustomListConfiguration();
        config.setSwipeRefreshRequired(false);
        config.setMainBackground(R.drawable.ic_launcher);
        config.setCustomItemLayout(R.layout.custom_layout_list);
        config.setAdapterType(CustomListConfiguration.TYPE_TWO_LABELS);
      //  config.setBaseAdapter(new T);
        return config;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void OnDataFetchSuccess(JSONObject responseObj) {
        try {
            JSONArray respArray = responseObj.getJSONArray("data");
            for(int i=0;i<respArray.length(); i++)
            {
                JSONObject obj=respArray.getJSONObject(i);
                itemList.add(ListItem.createItem(i, obj.getString("event"),"Start Date : "+obj.getString("start_date")));
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        Log.e("TAG DATA",responseObj.toString());
        swipeLayout.setRefreshing(false);
        setData(); //Calling method in Base Class to set data to adapter//
    }

    @Override
    public void OnDataFetchFailed(JSONObject responseObj) {
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void OnDataFetchError(Exception e) {
        swipeLayout.setRefreshing(false);
        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
        setEmptyText("No Data");
    }
}