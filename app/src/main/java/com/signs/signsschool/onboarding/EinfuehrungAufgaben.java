package com.signs.signsschool.onboarding;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class EinfuehrungAufgaben extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einfuehrung_aufgaben);

        getWindow().getDecorView().setBackgroundColor(Color.WHITE);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mInHome = new Intent(EinfuehrungAufgaben.this, EinfuehrungNews.class);
                EinfuehrungAufgaben.this.startActivity(mInHome);
                EinfuehrungAufgaben.this.finish();
            }
        }, 4000);
    }
}