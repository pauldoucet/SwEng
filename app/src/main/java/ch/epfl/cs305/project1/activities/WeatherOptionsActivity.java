package ch.epfl.cs305.project1.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import ch.epfl.cs305.project1.R;

public class WeatherOptionsActivity extends AppCompatActivity {

    private final static int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_options);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, WeatherInfoActivity.class);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        EditText editText = (EditText) findViewById(R.id.addressEditText);
        intent.putExtra("useCurrentLocation", checkBox.isChecked());
        intent.putExtra("address", editText.getText().toString());
        if(checkBox.isChecked()) {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSION_REQUEST_CODE);
                return;
            }
        }
        startActivity(intent);
    }
}