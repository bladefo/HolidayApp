package com.example.ocmdb.holidayapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.io.InputStream;
import java.util.Date;
import java.util.Random;

public class ChristmasActivity extends AppCompatActivity
{
    TextView txtDaysRemain, txtLongString;
    ImageView santa;
    BackgroundSound bgSound = new BackgroundSound();

    @Override
    protected void onResume()
    {
        super.onResume();
        bgSound.execute();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.christmas_activity);

        //control

        txtDaysRemain = findViewById(R.id.txtDaysRemain);
        txtLongString = findViewById(R.id.txtLongString);
        santa = (ImageView)findViewById(R.id.imageView5);
        startTimer();
    }

    public void animateSanta(View v){
        ObjectAnimator rotate = ObjectAnimator.ofFloat(santa, "rotation", 0f, 360f);
        AnimatorSet anSet = new AnimatorSet();
        anSet.playTogether(rotate);
        anSet.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.christmas_in, R.anim.home_out);
    }

    private void startTimer()
    {
        long difference = getRemainDays();
        new CountDownTimer(difference,1000)
        {
            public void onTick(long millisUntilFinished)
            {
                int days = (int)(millisUntilFinished/(1000*60*60*24));
                int hours = (int)(millisUntilFinished/(1000*60*60)%24);
                int mins = (int)(millisUntilFinished/(1000*60)%60);
                int sec = (int)(millisUntilFinished/(1000)%60);

                txtDaysRemain.setText(String.format("%d", days));
                txtLongString.setText(String.format("%d DAYS %02d: %02d: %02d", days, hours, mins, sec));
            }
            public void onFinish()
            {

            }
        }.start();
    }

    private long getRemainDays()
    {
        Date currentDate = new Date();
        Date endDate;
        if(currentDate.getMonth() <= 11)
        {
            endDate = new Date(currentDate.getYear(), 11, 25);
        }
        else
        {
            endDate = new Date(currentDate.getYear() + 1, 11, 25);
        }
        return endDate.getTime() - currentDate.getTime();
    }


    public void goToMain(View view)
    {
        Intent mainIntent = new Intent(ChristmasActivity.this, MainActivity.class);
        startActivity(mainIntent);
        bgSound.killMusic();
    }

    class BackgroundSound extends AsyncTask<Void, Void, Void>
    {
        MediaPlayer player;
        @Override
        protected Void doInBackground(Void... voids)
        {
            Random random = new Random(System.currentTimeMillis());
            int rd = random.nextInt(4-1) + 1;
            if(rd == 1)
            {
                player = MediaPlayer.create(ChristmasActivity.this, R.raw.anglhi);
            }
            else if(rd == 2)
            {
                player = MediaPlayer.create(ChristmasActivity.this, R.raw.faithful);
            }
            else if(rd == 3)
            {
                player = MediaPlayer.create(ChristmasActivity.this, R.raw.joywrld1);
            }
            player.setLooping(true);
            player.setVolume(1.0f, 1.0f);
            player.start();

            return null;
        }
        protected Void killMusic()
        {
            player.stop();
            return null;
        }
    }
}
