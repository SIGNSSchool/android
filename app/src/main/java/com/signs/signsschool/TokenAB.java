package com.signs.signsschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.assignments.AufgabeBearbeitenWeb;
import com.signs.signsschool.assignments.Fach;

public class TokenAB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_ab);

        Button continueButton = findViewById(R.id.continueTokenAB);
        Button backButton = findViewById(R.id.backTokenAB);
        TextView token = findViewById(R.id.tokenTVAB);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Integer message_id = prefs.getInt("message_id", 0);
        Integer user_id = prefs.getInt("user_id", 0);



        token.setText(user_id+"-"+message_id);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(TokenAB.this, AufgabeBearbeitenWeb.class));

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(TokenAB.this, Fach.class));

            }
        });







    }
}