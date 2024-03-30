package com.signs.signsschool.absences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.Home;
import com.signs.signsschool.R;

public class krankmeldenlaview extends AppCompatActivity {


    Integer setgradeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krankmeldenlaview);

        Button Fertig = findViewById(R.id.FertigKrankmeldenLAview);
        Button show = findViewById(R.id.fehlstundenButtonKrankmeldenLAview);

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(krankmeldenlaview.this, Home.class));
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(krankmeldenlaview.this, Absences.class);
                intent.putExtra("Grade", setgradeString);
                startActivity(intent);
            }
        });


        Spinner Grade = findViewById(R.id.spinnerEntschuldigenLA);


        ArrayAdapter<CharSequence> gradeadapter = ArrayAdapter.createFromResource(this, R.array.Grade, android.R.layout.simple_spinner_item);
        gradeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Grade.setAdapter(gradeadapter);


        Grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getSelectedItem().toString()) {

                    case "Fünfter":

                        setgradeString = 5;
                        break;
                    case "Sechster":

                        setgradeString = 6;
                        break;
                    case "Siebter":

                        setgradeString = 7;
                        break;
                    case "Achter":

                        setgradeString = 8;
                        break;
                    case "Neunter":

                        setgradeString = 9;
                        break;
                    case "Zehnter":

                        setgradeString = 10;
                        break;
                    case "EF":

                        setgradeString = 11;
                        break;
                    case "Q1":

                        setgradeString = 12;
                        break;
                    case "Q2":

                        setgradeString = 13;
                        break;


                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}