package com.signs.signsschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.assignments.AufgabeErstellenWeb;

public class TokenAE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_ae);

        Button backButton = findViewById(R.id.backTokenAE);
        Button continueButton = findViewById(R.id.continueTokenAE);
        TextView token = findViewById(R.id.tokenTVAE);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        token.setText(prefs.getString("user_id", "error"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TokenAE.this, Menu.class);
                intent.putExtra("sendervalueMenu", "aufgaben_teacher");
                startActivity(intent);

            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(TokenAE.this, AufgabeErstellenWeb.class));

            }
        });



    }
}