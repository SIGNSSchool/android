package com.signs.signsschool.assignments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class LAufgabeEinsichtAufgabe extends AppCompatActivity {

    Button Fertig, Bewerten, File;
    TextView Name, Description, SubmissionDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_aufgabe_einsicht_aufgabe);

        Intent intent = getIntent();

        Fertig = (Button)findViewById(R.id.button68);

        Name = (TextView)findViewById(R.id.nameTVAufgabeEinsichtAufgabe);
        SubmissionDate = (TextView)findViewById(R.id.textView29);
        Description = (TextView)findViewById(R.id.textView28);
        File = (Button)findViewById(R.id.openfileButtonAufgabenEinsichtAufgabe);

        Button Evaluation = findViewById(R.id.evaluationButtonAbgegebeneAufgabe);


        Name.setText(intent.getStringExtra("nameAufgabeEinsicht"));
        SubmissionDate.setText(intent.getStringExtra("dateAufgabeEinsicht"));
        Description.setText(intent.getStringExtra("descriptionAufgabeEinsicht"));
        String image = intent.getStringExtra("imageAufgabeEinsicht");

        String user_id = intent.getStringExtra("user_idAufgabeEinsicht");
        Integer course_id = intent.getIntExtra("message_idAufgabeEinsicht", 0);
        String subject = intent.getStringExtra("subjectAufgabeEinsicht");



        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LAufgabeEinsichtAufgabe.this, AufgabenEinsicht.class);
                startActivity(intent);
            }
        });

        File.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LAufgabeEinsichtAufgabe.this, AbgegebeneAufgabeImage.class);
                intent.putExtra("imageAbgegebeneAufgabeImage", image);
                intent.putExtra("nameAbgegebeneAufgabeImage", Name.getText().toString());
                startActivity(intent);

            }
        });

        Evaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LAufgabeEinsichtAufgabe.this);


                Intent intent = new Intent(LAufgabeEinsichtAufgabe.this, LAufgabenEinsichtAufgabeAntwort.class);
                intent.putExtra("userLEvaluation", user_id);
                intent.putExtra("DateLEvaluation", SubmissionDate.getText().toString());
                intent.putExtra("SubjectLEvaluation", subject);
                intent.putExtra("course_idLEvaluation", course_id);
                intent.putExtra("DescriptionLEvaluation", Description.getText().toString());


                startActivity(intent);

            }
        });


    }



}