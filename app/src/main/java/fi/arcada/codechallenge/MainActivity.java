package fi.arcada.codechallenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

// Denna MainActivity håller koll på hur många gånger man öppnat appen
// Den säger även två olika meddelanden - En vid start och en efter man varit i settings
public class MainActivity extends AppCompatActivity {
    // En boolean för att hålla koll på om vi just starta appen
    private boolean isInitialLaunch = true;
    private TextView appCountTextView;
    private TextView welcomeTextView;
    private FloatingActionButton settingsButton;

    // Vi gör en instans av SharedPreferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences something;


    // En sharedPrefence är som cookies i webdev
    // Man spara sen nyckel-värd par (lite som HashMaps från tidigare under lektionen)
    // Vi gör en sharedPreferences för våra "counters" (räknare)
    // Vi gör sen också vår "räknare"
    private static final String PREFS_NAME = "AppCounterPrefs";
    private static final String APP_COUNTER_KEY = "appCounter";
    private static final String MESSAGE_KEY = "Message";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appCountTextView = findViewById(R.id.appCount);
        welcomeTextView = findViewById(R.id.welcomeView);

        // Vår settings button - Med snygg icon
        settingsButton = findViewById(R.id.myFloatingActionButton);

        // Här öppnar vi den specifika sharedPreferences filen vi definerat (PREFS_NAME dvs. AppCounterPrefs)
        // Vi läser den sedan i privat läge (Default) så vår klass har tillgång till den
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // En enkel intro text


        // Hämta värdet som just nu finns i vår nyckel APP_COUNTER_KEY
        int appCounter = sharedPreferences.getInt(APP_COUNTER_KEY, 0);


        // Öka värdet (vi har ju just öppnat appen och denna onCreate() körs)
        appCounter++;

        // Spara det nya värdet med edit & apply
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(APP_COUNTER_KEY, appCounter);
        editor.apply();

        // Vi visar vår fina counter i vår textView
        appCountTextView.setText(String.valueOf(appCounter));

        // Vår clickListener för settings
        settingsButton.setOnClickListener(v -> openSettings());
    }

    @Override
    protected void onResume() {
        super.onResume();


        something = getSharedPreferences("AppCounterPrefs", MODE_PRIVATE);


        String savedMessage = something.getString("Message", "No message found");


        Log.d("SharedPrefsDebug", "Saved message in MainActivity: " + savedMessage);
        welcomeTextView.setText(savedMessage);




        if (!isInitialLaunch) {
            welcomeTextView.setText("Hej igen!");
        } else {
            isInitialLaunch = false;
        }

        ArrayList<Double> temperatures = new ArrayList<>();
        temperatures.add(17.5);
        temperatures.add(16.0);
        temperatures.add(16.5);
        temperatures.add(15.0);
        temperatures.add(17.5);
        temperatures.add(18.0);
        temperatures.add(15.5);
        temperatures.add(20.0);
        temperatures.add(19.5);
        temperatures.add(16.0);


        movingAvg(temperatures);
        Log.d("ArrayList", temperatures.toString());


    }

    public Double movingAvg(ArrayList<Double> temperatures) {
        Log.d("ArrayList", "Original temperatures: " + temperatures.toString());


        for (int i = 0; i <= temperatures.size() - 3; i++) {
            double sum = temperatures.get(i) + temperatures.get(i + 1) + temperatures.get(i + 2);
            double avg = sum / 3.0;

            Log.d("SMA", "Average of [" + temperatures.get(i) + ", " +
                    temperatures.get(i + 1) + ", " + temperatures.get(i + 2) + "] = " + avg);
        }

        return null;
    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}






