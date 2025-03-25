package fi.arcada.codechallenge;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
    public void test() {
        ArrayList<Double> numbers = new ArrayList<Double>();

        numbers.add(1.0);
        numbers.add(2.0);
        numbers.add(3.0);
        numbers.add(4.0);
        numbers.add(5.0);
    }




    public void calculate(View view) {
        System.out.println("toimii");
        numbers.add(15.0);
    }
}