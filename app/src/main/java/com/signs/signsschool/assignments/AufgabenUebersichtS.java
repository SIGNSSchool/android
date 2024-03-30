package com.signs.signsschool.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.Home;
import com.signs.signsschool.R;

public class AufgabenUebersichtS extends AppCompatActivity {

    Button Fertig, Unterricht, Bewertungen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aufgaben_uebersicht_s);

        Fertig = (Button)findViewById(R.id.FertigButtonAufgabenUebersichtS);
        Unterricht = (Button)findViewById(R.id.UnterrichtButtonAufgabeUebersichtS);
        Bewertungen = (Button)findViewById(R.id.BewertungenButtonAufgabeUebersichtS);


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AufgabenUebersichtS.this, Home.class);
                startActivity(intent);

            }
        });
        Unterricht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AufgabenUebersichtS.this, Learning.class);
                startActivity(intent);
            }
        });
        Bewertungen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AufgabenUebersichtS.this, AufgabenBewertungenS.class);
                startActivity(intent);

            }
        });

    }
}