package com.signs.signsschool.onboarding;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.Login;
import com.signs.signsschool.R;

public class EinfuehungId extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einfuehung_id);

        getWindow().getDecorView().setBackgroundColor(Color.WHITE);


        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(EinfuehungId.this, Login.class);
                EinfuehungId.this.startActivity(intent);
                EinfuehungId.this.finish();

            }
        }, 4000);

    }
}