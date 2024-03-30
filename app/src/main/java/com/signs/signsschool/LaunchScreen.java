package com.signs.signsschool;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.onboarding.EinfuehrungAufgaben;

public class LaunchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchScreen.this, EinfuehrungAufgaben.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}