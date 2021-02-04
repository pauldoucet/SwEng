package ch.epfl.cs305.project1.weather;

import ch.epfl.cs305.project1.location.Location;

import java.io.IOException;

/**
 * @author Paul Doucet (316442)
 */
public interface WeatherService {
    Forecast getForecastFrom(Location location) throws IOException;
}
