package com.example.ocmdb.holidayapp;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.home_in, R.anim.christmas_out);
    }

    public void goToChristmas(View view)
    {
        Intent christmasIntent = new Intent(MainActivity.this, ChristmasActivity.class);
        startActivity(christmasIntent);
    }
}
