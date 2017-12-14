package com.divi.click_o_meter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int count;
    private int time;

    private TextView counter;
    private TextView tapRate;
    private Button tap;

    private Timer timer = new Timer();
    private TimerTask rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize(){
        count = 0;
        time = 0;
        counter = (TextView)findViewById(R.id.text1);
        tap = (Button)findViewById(R.id.button1);
        tapRate = (TextView)findViewById(R.id.text2);

        rate = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        tapRate.setText("At a rate of " +String.valueOf((int)((count*60)/time))+ "/min");
                    }
                });
            }
        };

    }

    public void reset(View view) {
        count = 0;
        time = 0;
        counter.setText("You have clicked " +String.valueOf(count)+ " times");
        tap.setText("Tap here to start!");
        rate.cancel();
        tapRate.setText("At a rate of 0/min");
    }

    public void click(View view) {
        count++;
        tap.setText("Tap!");
        if(count == 1){
            counter.setText("You have clicked " +String.valueOf(count)+ " time!");

            rate = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            time++;
                            tapRate.setText("At a rate of " +String.valueOf((int)((count*60)/time))+ "/min");
                        }
                    });
                }
            };

            timer.scheduleAtFixedRate(rate, 0 ,1000);
        }
        else{
            counter.setText("You have clicked " +String.valueOf(count)+ " times");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("countKey",count);
        outState.putCharSequence("counterKey",counter.getText());
        outState.putCharSequence("tapKey",tap.getText());
        outState.putInt("timeKey",time);
        outState.putCharSequence("tapRateKey",tapRate.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("countKey");
        counter.setText(savedInstanceState.getCharSequence("counterKey"));
        tap.setText(savedInstanceState.getCharSequence("tapKey"));
        tapRate.setText(savedInstanceState.getCharSequence("tapRateKey"));
        time = savedInstanceState.getInt("timeKey");

        if (count!=0){
            timer.scheduleAtFixedRate(rate, 0 ,1000);
        }
    }

}
