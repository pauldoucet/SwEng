package ch.epfl.cs305.project1.activities;

import android.content.Intent;
import android.location.Criteria;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ch.epfl.cs305.project1.*;
import ch.epfl.cs305.project1.location.*;
import ch.epfl.cs305.project1.weather.AndroidWeatherService;
import ch.epfl.cs305.project1.weather.Forecast;
import ch.epfl.cs305.project1.weather.WeatherService;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;
import java.io.IOException;

@AndroidEntryPoint
public class WeatherInfoActivity extends AppCompatActivity {

    private TextView tempMinTextView;
    private TextView tempMaxTextView;
    private TextView tempAverageTextView;
    private TextView humidityTextView;
    private TextView windSpeedTextView;
    @Inject GeocodingService geocodingService;
    @Inject LocationService locationService;
    @Inject WeatherService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        setContentView(R.layout.activity_weather_info);

        tempMinTextView = findViewById(R.id.tempMin);
        tempMaxTextView = findViewById(R.id.tempMax);
        tempAverageTextView = findViewById(R.id.tempAverage);
        humidityTextView = findViewById(R.id.humidity);
        windSpeedTextView = findViewById(R.id.windSpeed);

        //geocodingService = AndroidGeocodingService.ofContext(this);

        //Criteria criteria = new Criteria();
        //criteria.setPowerRequirement(Criteria.ACCURACY_MEDIUM);
        //locationService = AndroidLocationService.ofContextCriteria(this, criteria);

        //weatherService = AndroidWeatherService.ofKey(getString(R.string.OWM_KEY));

        Location location = null;
        Intent intent = getIntent();
        boolean isChecked = intent.getBooleanExtra(WeatherOptionsActivity.EXTRA_BOX_CHECKED, false);

        if(isChecked) {
            try {
                location = locationService.getUserLocation();
            }
            catch(IOException e){
                Log.e("WeatherInfoActivity", "failed to retrieve user's current position");
            }
        }
        else {
            String address = intent.getStringExtra(WeatherOptionsActivity.EXTRA_ADDRESS_FIELD);
            location = geocodingService.nameToLocation(address);
        }

        if(location != null) {
            try {
                Forecast forecast = weatherService.getForecastFrom(location);
                displayForecast(forecast);
            }
            catch(IOException e) {
                Log.e("WeatherInfoActivity", "failed to retrieve forecast");
            }
        }
        else {
            Log.e("WeatherInfoActivity", "location is null");
        }
    }

    private void displayForecast(Forecast forecast) {
        tempMinTextView.setText(forecast.getTempMin() + "");
        tempMaxTextView.setText(forecast.getTempMax() + "");
        tempAverageTextView.setText(forecast.getTemp() + "");
        humidityTextView.setText(forecast.getHumidity() + "");
        windSpeedTextView.setText(forecast.getWindSpeed() + "");
    }
}