package ch.epfl.cs305.project1.weather;

import android.content.Context;
import ch.epfl.cs305.project1.R;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

import static android.provider.Settings.System.getString;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class WeatherServiceModule {
    @Binds
    public abstract WeatherService bindWeatherService(AndroidWeatherService weatherServiceImpl);

    @Provides
    public static String provideKey(@ApplicationContext Context context) {
        return context.getString(R.string.OWM_KEY);
    }
}
