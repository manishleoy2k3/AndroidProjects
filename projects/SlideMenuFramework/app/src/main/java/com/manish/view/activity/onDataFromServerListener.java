package com.manish.view.activity;

import org.json.JSONObject;

/**
 * Created by Manish on 12/31/2014.
 */
public interface onDataFromServerListener {
    /* Data from server on success */
    public void OnDataFetchSuccess(JSONObject responseObj);
    /* Data from server on failure */
    public void OnDataFetchFailed(JSONObject responseObj);
    /* If some error occur */
    public void OnDataFetchError(Exception e);
}
