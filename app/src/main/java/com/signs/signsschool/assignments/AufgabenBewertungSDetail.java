package com.signs.signsschool.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class AufgabenBewertungSDetail extends AppCompatActivity {



    TextView Description, Evaluation, Date, Subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aufgaben_bewertung_sdetail);

        Intent intentget = getIntent();

        Description = (TextView) findViewById(R.id.AufgabeAufgabeBewertungSAnzeige);
        Evaluation = (TextView) findViewById(R.id.EvaluationAufgabeBewertungSAnzeige);
        Date = (TextView)findViewById(R.id.DateTVAufgabenBewertungSAnzeige);
        Subject = (TextView)findViewById(R.id.SubjectTVAufgabenBewertungSAnzeige);
        Button Fertig = (Button)findViewById(R.id.FertigButtonAufgabenBewertungSDetail);


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AufgabenBewertungSDetail.this, AufgabenBewertungenS.class);
                startActivity(intent);
            }
        });



        Subject.setText(intentget.getStringExtra("titleABS"));
        Date.setText(intentget.getStringExtra("dateABS"));
        Description.setText(intentget.getStringExtra("descriptionABS"));
        Evaluation.setText(intentget.getStringExtra("evaluationABS"));



    }
}