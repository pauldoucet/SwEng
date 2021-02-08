package ch.epfl.cs305.project1;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import ch.epfl.cs305.project1.activities.WeatherInfoActivity;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author Paul Doucet (316442)
 */
@HiltAndroidTest
public final class WeatherActivityTest {
    @Rule public RuleChain rule = RuleChain.outerRule(new HiltAndroidRule(this)).around(new ActivityScenarioRule<>(WeatherInfoActivity.class));
    HttpsURLConnection connection = Mockito.mock(HttpsURLConnection.class);

}
