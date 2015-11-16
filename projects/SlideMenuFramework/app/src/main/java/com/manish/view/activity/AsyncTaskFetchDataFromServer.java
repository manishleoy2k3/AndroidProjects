package com.manish.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.manish.helper.JSONParserClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Manish on 12/31/2014.
 */
public class AsyncTaskFetchDataFromServer extends AsyncTask<String, String, String> {

    public onDataFromServerListener delegate;
    ProgressDialog pDialog;
    Context context;
    private boolean progressVisible;
    private String url,urlEncodedParameters;

    public AsyncTaskFetchDataFromServer(Context context,boolean progressVisible,String url,String urlencodedParameters)
    {
        this.progressVisible=progressVisible;
        this.context=context;
        this.url=url;
        this.urlEncodedParameters=urlencodedParameters;
    }

    JSONObject mainObj = null;
    Exception exception;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(progressVisible) {
            pDialog=new ProgressDialog(context);
            pDialog.setMessage("Fetching Data..");
            pDialog.show();
        }
    }

    @Override
    protected String doInBackground(String... params) {

//        //setting test data//
//        ArrayList<ListItem> itemList=new ArrayList<ListItem>();
//
//        try {
//
//            JSONArray arrayObj = new JSONArray();
//            for(int i=1;i<=15;i++) {
//
//                JSONObject obj = new JSONObject();
//                obj.put("id", i);
//                obj.put("label", "Test Data " + i);
//
//                arrayObj.put(obj);
//            }
//
//            mainObj.putOpt("data", arrayObj);
//        }
//        catch(JSONException e)
//        {
//
//        }
//        try
//        {
//            Thread.sleep(1000);
//        }catch(Exception e){
//
//        }

        try {
            mainObj = JSONParserClient.makeHTTPRequest(url, urlEncodedParameters);

        }catch(IOException e)
        {
            exception=e;
        }catch(JSONException e)
        {
            exception=e;
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(progressVisible)
        pDialog.dismiss();
        if(mainObj==null)
        delegate.OnDataFetchError(exception);
        else
        delegate.OnDataFetchSuccess(mainObj);
    }
}