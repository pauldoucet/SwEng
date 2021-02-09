package ch.epfl.cs305.project1;

import android.content.Context;
import android.content.Intent;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import ch.epfl.cs305.project1.activities.WeatherInfoActivity;
import ch.epfl.cs305.project1.activities.WeatherOptionsActivity;
import ch.epfl.cs305.project1.location.*;
import ch.epfl.cs305.project1.weather.AndroidWeatherService;
import ch.epfl.cs305.project1.weather.Forecast;
import ch.epfl.cs305.project1.weather.WeatherService;
import ch.epfl.cs305.project1.weather.WeatherServiceModule;
import dagger.hilt.android.testing.BindValue;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author Paul Doucet (316442)
 */
@HiltAndroidTest
@UninstallModules({WeatherServiceModule.class, LocationServiceModule.class, GeocodingServiceModule.class})
public final class WeatherActivityTest {
    //private HiltAndroidRule hiltRule = new HiltAndroidRule(this);
    //private ActivityScenarioRule<WeatherInfoActivity> scenario = new ActivityScenarioRule(WeatherInfoActivity.class);

    //@Rule public RuleChain ruleChain = RuleChain.outerRule(hiltRule).around(scenario);
    @Rule public HiltAndroidRule rule = new HiltAndroidRule(this);

    @BindValue public WeatherService weatherService = Mockito.mock(WeatherService.class);
    @BindValue public LocationService locationService = Mockito.mock(LocationService.class);
    @BindValue public GeocodingService geocodingService = Mockito.mock(GeocodingService.class);

    public final static Location TEST_LOCATION = Location.of(3,3);
    public final static Forecast TEST_FORECAST = Forecast.of(50, 40, 55, 30, "cloudy", 24);
    public final static String TEST_ADDRESS = "24 chemin des grandes bruyeres";

    @Test
    public void WeatherDisplayedCorrectlyWhenCheckingUseCurrentLocation() throws IOException {
        Mockito.when(weatherService.getForecastFrom(TEST_LOCATION)).thenReturn(TEST_FORECAST);
        Mockito.when(locationService.getUserLocation()).thenReturn(TEST_LOCATION);
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, WeatherInfoActivity.class);
        intent.putExtra(WeatherOptionsActivity.EXTRA_BOX_CHECKED, true);
        intent.putExtra(WeatherOptionsActivity.EXTRA_ADDRESS_FIELD, TEST_ADDRESS);

        try(ActivityScenario<WeatherInfoActivity> scenario = ActivityScenario.launch(intent)) {
            weatherDisplayedCorrectly(TEST_FORECAST);
            Mockito.verify(weatherService).getForecastFrom(TEST_LOCATION);
            Mockito.verify(locationService).getUserLocation();
        }



    }


    private static void weatherDisplayedCorrectly(Forecast forecast) {
        onView(withId(R.id.tempAverage)).check(matches(withText(forecast.getTemp() + "")));
        onView(withId(R.id.tempMin)).check(matches(withText(forecast.getTempMin() + "")));
        onView(withId(R.id.tempMax)).check(matches(withText(forecast.getTempMax() + "")));
        onView(withId(R.id.humidity)).check(matches(withText(forecast.getHumidity() + "")));
        onView(withId(R.id.windSpeed)).check(matches(withText(forecast.getWindSpeed() + "")));
    }
}