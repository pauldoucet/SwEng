package ch.epfl.cs305.project1.location;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * An implementation of a location service for android
 * @author Paul Doucet (316442)
 */
public class AndroidLocationService implements LocationService {
    private final LocationManager manager;
    private final Criteria criteria;

    private AndroidLocationService(LocationManager manager, Criteria criteria) {
        this.manager = manager;
        this.criteria = criteria;
    }


    private static LocationManager buildLocationManager(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * Instantiate the user location service
     * @param context: app's context
     * @param criteria: criteria for choosing the GPS location provider
     * @return GPS location service
     */
    public static AndroidLocationService ofContextCriteria(Context context, Criteria criteria) {
        return new AndroidLocationService(buildLocationManager(context), criteria);
    }

    private String bestProvider() {
        return manager.getBestProvider(criteria, false);
    }

    /**
     * @return current user's location with respect ot the service criteria
     */
    @Override
    public Location getLocation() throws IOException {
        try {
            android.location.Location loc = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(loc == null)
                return Location.of(0f, 0f);
            else
                return Location.of((float) loc.getLongitude(), (float) loc.getLatitude());
        }
        catch (SecurityException e) {
            throw new IOException();
        }
    }
}