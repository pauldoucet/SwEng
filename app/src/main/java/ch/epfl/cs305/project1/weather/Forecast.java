package ch.epfl.cs305.project1.weather;

/**
 * weather Forecast
 * @author Paul Doucet (316442)
 */
public class Forecast {
    private final float temp;
    private final float tempMin;
    private final float tempMax;
    private final int humidity;
    private final String weather;
    private final float windSpeed;

    private Forecast(float temp, float tempMin, float tempMax, int humidity,
                     String weather, float windSpeed) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.humidity = humidity;
        this.weather = weather;
        this.windSpeed = windSpeed;
    }

    /**
     * Construct a full weather Forecast
     * @param temp: current temperature
     * @param tempMin: minimum temperature through the day
     * @param tempMax: maximum temperature through the day
     * @param humidity: current air humidity in percentage
     * @param weather: current weather type
     * @param windSpeed: current wind speed
     * @return full weather Forecast
     */
    public static Forecast of(float temp, float tempMin, float tempMax, int humidity,
                       String weather, float windSpeed) {
        return new Forecast(temp, tempMin, tempMax, humidity, weather, windSpeed);
    }

    public float getTemp() {
        return temp;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getWeather() {
        return weather;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

}
