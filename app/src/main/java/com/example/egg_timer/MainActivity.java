package com.example.egg_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timerSeekBar = findViewById(R.id.timerSeekBar);
        TextView timerTextView = findViewById(R.id.countdownTextView);

        //60 secs in a min therefore 600 for 10 min max.
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int minutes = progress/60;
                int seconds = progress - (minutes * 60);

                String secondString = Integer.toString(seconds);

                if (secondString.equals("0")) {
                    secondString = "00";
                }

                timerTextView.setText(Integer.toString(minutes) + ":" + secondString);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}