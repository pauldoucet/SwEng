package ch.epfl.cs305.project1.location;

import dagger.hilt.DefineComponent;

/**
 * @author Paul Doucet (316442)
 */
public interface GeocodingService {
    Location nameToLocation(String name);
}
