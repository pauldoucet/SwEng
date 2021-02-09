package ch.epfl.cs305.project1.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;

/**
 * @author Paul Doucet (316442)
 */
@Singleton
public class AndroidGeocodingService implements GeocodingService {
    private final Geocoder geocoder;

    @Inject
    AndroidGeocodingService(Geocoder geocoder) {
        this.geocoder = geocoder;
    }

    /**
     * Constructs a Geocoding service for android
     */
    public static AndroidGeocodingService ofContext(Context context) {
        return new AndroidGeocodingService(new Geocoder(context));
    }

    /**
     * convert location name to geographic coordinates
     * @param name: location name
     * @return geographic coordinates
     */
    @Override
    public Location nameToLocation(String name) {
        try {
            List<Address> addresses = geocoder.getFromLocationName(name, 1);
            if(addresses.size() == 0)
                return Location.of(0f, 0f);
            else {
                Address address = addresses.get(0);
                return Location.of((float) address.getLongitude(), (float) address.getLatitude());
            }
        }
        catch(IOException e) {
            return null;
        }
    }
}
