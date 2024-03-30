package com.signs.signsschool.signs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.Home;
import com.signs.signsschool.R;

public class SignsUebersicht extends AppCompatActivity {

    Button Eingang, Gesendet, Erstellen, Fertig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_uebersicht);

        Eingang = (Button)findViewById(R.id.SignsÜbersichtEingang);
        Gesendet = (Button)findViewById(R.id.SignsÜbersichtEinsicht);
        Erstellen = (Button)findViewById(R.id.SignsÜbersichtErstellen);
        Fertig = (Button)findViewById(R.id.FertigSignsÜbersicht);


        Eingang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignsUebersicht.this, Signs.class);
                startActivity(intent);

            }
        });

        Gesendet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignsUebersicht.this, SignsEinsicht.class);
                startActivity(intent);

            }
        });

        Erstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignsUebersicht.this, SignsErstellen.class);
                startActivity(intent);

            }
        });

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignsUebersicht.this, Home.class);
                startActivity(intent);
            }
        });




    }
}