package com.manish.Location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by manish on 1/19/2015.
 */
public class LocationHelper implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener{

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient=null;
    LocationListenerInterface delegate;
    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */
    protected LocationRequest mLocationRequest;
    /**
     * Represents a geographical location.
     */

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    private long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS;
    protected Boolean mRequestingLocationUpdates=false;

    private Context mContext;


    public LocationHelper(Context context)
    {
        this.mContext=context;
    }

    public void setDelegate(LocationListenerInterface delegate)
    {
        this.delegate=delegate;
    }

    /**Method called to find current location
     */
    public void findCurrentLocation()
    {
        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        // createLocationRequest();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        delegate.currentLocation(mCurrentLocation);
        if(mRequestingLocationUpdates)
        {
            startLocationUpdates( UPDATE_INTERVAL_IN_MILLISECONDS);
        }
        Log.e("TAG","Connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        delegate.updateLocation(location);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.e("TAG", "Connection Failed");
    }

    /**Call this method to disconnect location services.
     * Recommended to call this method otherwise open connection will be left at background*/
    public void disconnect()
    {
        if((mGoogleApiClient!=null)&&(mGoogleApiClient.isConnected()))
            mGoogleApiClient.disconnect();
    }


    /** This method is called to start periodic location updates. Recommended should be called after <code>findCurrentLocation method.
     * @param updateIntervalInMilliseconds The time in milliseconds for required interval between location updated
     */
    protected void startLocationUpdates(long updateIntervalInMilliseconds) {
        // The final argument to {@code requestLocationUpdates()} is a LocationListener
        // (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
        if(mGoogleApiClient==null)//if directly called for update without first call to last location//
        {
            findCurrentLocation();
        }
        mRequestingLocationUpdates = true;
        UPDATE_INTERVAL_IN_MILLISECONDS=updateIntervalInMilliseconds;
        if(mGoogleApiClient.isConnected()) {
            mRequestingLocationUpdates = true;
            createLocationRequest(updateIntervalInMilliseconds);
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
        }
    }

    /**
     * call this method to stop location updates.
     */
    protected void stopLocationUpdates() {
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.

        // The final argument to {@code requestLocationUpdates()} is a LocationListener
        // (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
        if(mRequestingLocationUpdates) {
            mRequestingLocationUpdates = false;
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    /**
     * Sets up the location request. Android has two location request settings:
     * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These settings control
     * the accuracy of the current location. This sample uses ACCESS_FINE_LOCATION, as defined in
     * the AndroidManifest.xml.
     * <p/>
     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
     * interval (5 seconds), the Fused Location Provider API returns location updates that are
     * accurate to within a few feet.
     * <p/>
     * These settings are appropriate for mapping applications that show real-time location
     * updates.
     */
    protected void createLocationRequest(long updateIntervalInMilliseconds) {
        mLocationRequest = new LocationRequest();

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.

        UPDATE_INTERVAL_IN_MILLISECONDS=updateIntervalInMilliseconds;
        FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
}
