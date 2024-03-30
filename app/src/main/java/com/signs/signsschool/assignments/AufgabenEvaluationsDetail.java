package com.signs.signsschool.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.signs.signsschool.R;

public class AufgabenEvaluationsDetail extends AppCompatActivity {

    Button Fertig;
    TextView Aufgabe, Evaluation, Name, Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laufgabe_bewertungen_anzeige);

        Name = findViewById(R.id.NameTVLAufgabenBewertungAnzeige);
        Date = findViewById(R.id.DateTVLAufgabenBewertungAnzeige);
        Aufgabe = findViewById(R.id.AufgabeLAufgabeBewertungAnzeige);
        Evaluation = findViewById(R.id.EvaluationLAufgabeBewertungAnzeige);
        Fertig = findViewById(R.id.FertigButtonBewertungAnzeigeS);


        Intent intentget = getIntent();

        Name.setText(intentget.getStringExtra("name"));
        Date.setText(intentget.getStringExtra("createdAt"));
        Aufgabe.setText(intentget.getStringExtra("submission"));
        Evaluation.setText(intentget.getStringExtra("evaluation"));

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AufgabenEvaluationsDetail.this, AufgabenEvaluations.class));
            }
        });
    }
}