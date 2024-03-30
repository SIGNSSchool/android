package com.signs.signsschool.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class EinfuehrungHOURSColours extends AppCompatActivity {

    Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einfuehrung_hourscolours);


        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(EinfuehrungHOURSColours.this, EinfuehungId.class);
                EinfuehrungHOURSColours.this.startActivity(intent);
                EinfuehrungHOURSColours.this.finish();
            }
        }, 4000);


    }
}