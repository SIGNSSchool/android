package com.signs.signsschool.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class EinfuehrungSigns extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einfuehrung_signs);


        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(EinfuehrungSigns.this, EinfuehrungStunden.class);
                EinfuehrungSigns.this.startActivity(intent);
                EinfuehrungSigns.this.finish();

            }
        }, 4000);


    }
}