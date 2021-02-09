package ch.epfl.cs305.project1.location;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

import javax.inject.Singleton;

@Module
@InstallIn(ApplicationComponent.class)
public abstract class LocationServiceModule {
    @Binds
    public abstract LocationService bindLocationService(AndroidLocationService locationServiceImpl);

    @Singleton
    @Provides
    public static LocationManager provideLocationManager(@ApplicationContext Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Singleton
    @Provides
    public static Criteria provideCriteria() {
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.ACCURACY_MEDIUM);
        return criteria;
    }
}
