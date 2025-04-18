package fi.arcada.codechallenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        welcomeTextView.setText("Hej på dig!");

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
        // onResume kallas varje gång denna Activity kommer i fokus
        // Vi kan använda vår boolean för att se till att saker funkar när vi kommer från settings
        if (!isInitialLaunch) {
            welcomeTextView.setText("Hej igen!");
        } else {
            isInitialLaunch = false;
        }

    }

    private void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}






