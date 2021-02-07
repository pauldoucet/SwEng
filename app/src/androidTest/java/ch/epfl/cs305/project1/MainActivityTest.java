package ch.epfl.cs305.project1;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import ch.epfl.cs305.project1.activities.GreetingActivity;
import ch.epfl.cs305.project1.activities.MainActivity;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
