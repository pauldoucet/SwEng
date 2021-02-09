package ch.epfl.cs305.project1.location;

import android.content.Context;
import android.location.Geocoder;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class GeocodingServiceModule {
    @Binds public abstract GeocodingService bindGeocodingService(AndroidGeocodingService geocodingServiceImpl);

    @Provides
    public static Geocoder provideGeocoder(@ApplicationContext Context context) {
        return new Geocoder(context);
    }
}
