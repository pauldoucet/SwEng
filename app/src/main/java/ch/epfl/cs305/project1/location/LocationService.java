package ch.epfl.cs305.project1.location;

import java.io.IOException;

/**
 * @author Paul Doucet (316442)
 */
public interface LocationService {
    Location getUserLocation() throws IOException;
}
