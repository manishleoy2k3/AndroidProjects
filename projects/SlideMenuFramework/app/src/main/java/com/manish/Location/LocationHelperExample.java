package com.manish.Location;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.slidemenuframework.R;

public class LocationHelperExample extends ActionBarActivity implements LocationListenerInterface {

    LocationHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_helper_example);
        helper=new LocationHelper(this);
        helper.setDelegate(this);       //setting location listener to get location and location updates//
        helper.findCurrentLocation();
        helper.startLocationUpdates(10000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        helper.stopLocationUpdates();
        helper.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location_helper_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void currentLocation(Location location) {
        Toast.makeText(this,"Location is "+location.getLatitude(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void updateLocation(Location location) {
        Toast.makeText(this,"Location update "+location.getLatitude(),Toast.LENGTH_LONG).show();
    }
}
