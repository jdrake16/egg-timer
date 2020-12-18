package com.example.egg_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSeekBar;

    Boolean counterIsActive = false;
    Button goButt;

    CountDownTimer countDownTimer;

    public void resetTimer() {

        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButt.setText("GO BABY!");
        counterIsActive = false;

    }

    public void buttonClicked(View view) {

        if (counterIsActive) {
            resetTimer();
        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButt.setText("ESTOP!");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.atlas_jam_one);
                    mPlayer.start();
                    timerTextView.setText("0:00");
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft) {

        int minutes = secondsLeft/60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         timerSeekBar = findViewById(R.id.timerSeekBar);
         timerTextView = findViewById(R.id.countdownTextView);
         goButt = findViewById(R.id.goButton);

        //60 secs in a min therefore 600 for 10 min max.
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
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