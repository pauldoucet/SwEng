package ch.epfl.cs305.project1.activities;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ch.epfl.cs305.project1.R;
import ch.epfl.cs305.project1.activities.GreetingActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "ch.epfl.cs305.project1.message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Remove when I learn about asynchronous operations
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
    }

    public void goMessage(View view) {
        Intent intent = new Intent(this, GreetingActivity.class);
        EditText editText = findViewById(R.id.mainName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void startWeatherActivity(View view) {
        Intent intent = new Intent(this, WeatherOptionsActivity.class);
        startActivity(intent);
    }

}