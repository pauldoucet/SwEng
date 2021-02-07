package ch.epfl.cs305.project1;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.ContentView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import ch.epfl.cs305.project1.activities.GreetingActivity;
import ch.epfl.cs305.project1.activities.MainActivity;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class GreetingActivityTest {

    private final static String TEST_NAME = "Paul";

    @Test
    public void greetingMessageIsCorrect() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, GreetingActivity.class);
        intent.putExtra(MainActivity.EXTRA_MESSAGE, TEST_NAME);
        try(ActivityScenario<GreetingActivity> scenario = ActivityScenario.launch(intent)) {
            onView(withId(R.id.greetingTextView)).check(ViewAssertions.matches(withText(Matchers.containsString("Greetings Paul!"))));
        }
    }
}
