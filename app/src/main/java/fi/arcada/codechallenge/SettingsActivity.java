package fi.arcada.codechallenge;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.content.SharedPreferences;

public class SettingsActivity extends AppCompatActivity {
    private Button resetButton;
    private SharedPreferences sharedPreferences;

    // Återanvänd koden i MainActivity, så vi använder samma sharedPreference fil och "räknare"
    private static final String PREFS_NAME = "AppCounterPrefs";
    private static final String APP_COUNTER_KEY = "appCounter";
    private static final String MESSAGE_KEY = "Message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // Helt vanlig knapp som vi hittar
        // På knappen sätter vi funktionen att nolla vår räknare
        // - Samma sätt som vi uppdatera den, kan vi sätta den till noll
        // --- Man kan göra om denna knapp till Spara knappen!

        resetButton = findViewById(R.id.resetButton);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        resetButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(APP_COUNTER_KEY, 0);
            editor.apply();
        });

    }
    public static void runMessage() {
        Log.d("TAG", "This is a debug message");

    }

}
