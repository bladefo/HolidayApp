package com.example.ocmdb.holidayapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SplashScreen extends AppCompatActivity {

    public static final int[] IMAGES;

    static {
        IMAGES = new int[]
                {
                        R.drawable.christmas3,
                        R.drawable.christmas4,
                        R.drawable.christmas5,
                };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView3);

        Random random = new Random(System.currentTimeMillis());
        for (int image : IMAGES) {
            imageView.setImageResource(IMAGES[random.nextInt(IMAGES.length - 1)]);
        }
        new Handler().postDelayed(new MyHandler(), 3000);

    }

    class MyHandler implements Runnable {
        public MyHandler() {

        }

        public void run() {
            goToMain();
        }
    }

    private void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.finish();
    }
}

