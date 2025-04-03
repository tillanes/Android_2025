package fi.arcada.codechallenge;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    public ArrayList<Double> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }



    public void test() {
        numbers.add(1.0);
        numbers.add(2.0);
        numbers.add(3.0);
        numbers.add(4.0);
        numbers.add(5.0);
    }



    public void calculate(View view) {
        test();
       // System.out.println("toimii");



        System.out.println(numbers);


        statisctics stats = new statisctics();

        stats.calcMean();


    }

    public class statisctics {
        public void calcMean() {

            double sum = 0;

            for (Double num : numbers) {
                sum += num;
            }

            double mean = sum / numbers.size();

            System.out.println("The mean is: " + mean);

           // System.out.println("class tomii");

            String StrMean = Double.toString(mean);
            TextView field = (TextView) findViewById(R.id.textView);
            field.setText(StrMean);

        }


    }


}