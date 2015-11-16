package com.manish.Location;

import android.location.Location;

/**
 * Created by Manish on 1/8/2015.
 */
public interface LocationListenerInterface {

    /**This method is called when location is found*/
    public void currentLocation(Location location);

    /**This method is called when location is updated*/
    public void updateLocation(Location location);
}
