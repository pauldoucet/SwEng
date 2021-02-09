package ch.epfl.cs305.project1.weather;

import ch.epfl.cs305.project1.location.Location;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Paul Doucet (316442)
 */
public class AndroidWeatherService implements WeatherService {

    public final static String URL_FROM_GEO = "https://api.openweathermap.org/data/2.5/weather?lat=%,f&lon=%,f&appid=%s";

    private final String key;

    @Inject
    AndroidWeatherService(String key) {
        this.key = key;
    }

    private static HttpsURLConnection buildConnection(URL url) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setReadTimeout(3000);
        connection.setConnectTimeout(3000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        return connection;
    }

    public static AndroidWeatherService ofKey(String key) {
        return new AndroidWeatherService(key);
    }

    /**
     * get weather forecast from location
     * @param location: location to get the forecast from
     * @return complete forecast
     * @throws IOException if JSON parsing fails or connection to the weather server fails
     */
    public Forecast getForecastFrom(Location location) throws IOException {

        JSONObject object = null;
        Forecast forecast = null;
        try {
            String s = getRawForecast(location);
            object = (JSONObject) new JSONTokener(s).nextValue();
            JSONObject mainObject = object.getJSONObject("main");
            float temp = (float) mainObject.getDouble("temp");
            float tempMin = (float) mainObject.getDouble("temp_min");
            float tempMax = (float) mainObject.getDouble("temp_max");
            int humidity = mainObject.getInt("humidity");
            String weather = object.getJSONArray("weather").getJSONObject(0).getString("main");
            float windSpeed = (float) object.getJSONObject("wind").getDouble("speed");
            forecast = Forecast.of(temp, tempMin, tempMax, humidity, weather, windSpeed);
        }
        catch(JSONException e) {
            throw new IOException();
        }
        return forecast;
    }

    private String getRawForecast(Location location) throws IOException {
        String queryUrl = String.format(Locale.ROOT, URL_FROM_GEO, location.getLat(), location.getLon(), key);
        String rawJson = "";

        BufferedReader stream = null;
        HttpsURLConnection connection = null;

        try {
            URL url = new URL(queryUrl);
            connection = buildConnection(url);

            InputStream underlyingStream = connection.getInputStream();

            stream = new BufferedReader(new InputStreamReader(underlyingStream, StandardCharsets.UTF_8));
            if(stream != null) {
                rawJson = stream.lines().collect(Collectors.joining("\n"));
            }
        }
        finally {
            if(connection != null) {
                connection.disconnect();
            }
            if(stream != null) {
                stream.close();
            }
        }
        return rawJson;
    }
}
