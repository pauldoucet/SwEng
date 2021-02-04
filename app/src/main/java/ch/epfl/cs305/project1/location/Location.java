package ch.epfl.cs305.project1.location;

import java.util.Locale;

/**
 * @author Paul Doucet (316442)
 */
public class Location {
    private final float lon;
    private final float lat;

    private Location(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public static Location of(float lon, float lat) {
        return new Location(lon, lat);
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "(lon=%f, lat=%f)", lon, lat);
    }
}
