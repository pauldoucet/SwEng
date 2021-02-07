package ch.epfl.cs305.project1;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.widget.EditText;
import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;

import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import ch.epfl.cs305.project1.activities.GreetingActivity;
import ch.epfl.cs305.project1.activities.MainActivity;
import ch.epfl.cs305.project1.activities.WeatherOptionsActivity;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Paul Doucet (316442)
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> testRule = new ActivityScenarioRule<>(MainActivity.class);
    private final static String TEST_NAME = "Paul";

    @Test
    public void clickingGreetingButtonSendsCorrectIntent() {
        Intents.init();
        onView(withId(R.id.mainName)).perform(ViewActions.clearText(), typeText("Paul"));
        closeSoftKeyboard();
        onView(withId(R.id.mainGoButton)).perform(ViewActions.click());
        Intents.intended(Matchers.allOf(IntentMatchers.hasComponent(GreetingActivity.class.getName()), IntentMatchers.hasExtra(MainActivity.EXTRA_MESSAGE, TEST_NAME)));
        Intents.release();
    }

}
