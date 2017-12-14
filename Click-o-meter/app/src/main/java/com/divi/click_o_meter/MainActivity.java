package com.divi.click_o_meter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int count;
    private TextView counter =(TextView)findViewById(R.id.text1);
    private Button tap = (Button)findViewById(R.id.button1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void reset(View view) {
        count = 0;
        counter.setText("You have clicked " +String.valueOf(count)+ " times");
        tap.setText("Tap here to start!");
    }

    public void click(View view) {
        count++;
        tap.setText("Tap!");
        if(count == 1){
            counter.setText("You have clicked " +String.valueOf(count)+ " time!");
        }
        else{
            counter.setText("You have clicked " +String.valueOf(count)+ " times");
        }
    }
}
