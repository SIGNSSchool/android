package com.signs.signsschool.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class EinfuehrungNews extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einfuehrung_news);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(EinfuehrungNews.this, EinfuehrungSigns.class);
                EinfuehrungNews.this.startActivity(intent);
                EinfuehrungNews.this.finish();
            }
        }, 4000);

    }
}