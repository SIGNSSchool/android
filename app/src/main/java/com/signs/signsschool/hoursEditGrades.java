package com.signs.signsschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class hoursEditGrades extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Integer setgradeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_edit_grades);


        Spinner gradeSpinner = findViewById(R.id.gradeSpinnerHoursEdit);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Grade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(adapter);
        gradeSpinner.setOnItemSelectedListener(this);


         Button Continue = findViewById(R.id.ContinueButtonHoursEditGrades);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

         Continue.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 startActivity(new Intent(hoursEditGrades.this, hoursEdit.class));

                 editor.putInt("gradeHoursEdit", setgradeString);
                 editor.commit();


             }
         });




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getSelectedItem().toString()) {

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
    public void onNothingSelected(AdapterView<?> parent) {

    }


}