package com.signs.signsschool.signs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class SignsErstellenDetails extends AppCompatActivity implements View.OnFocusChangeListener {

    Button Add, Back;
    EditText FirstQuestionET, fOption1ET, fOption2ET, fOption3ET, SecondQuestionET, sOption1ET, sOption2ET, sOption3ET;
    EditText Question1ET, Question2ET, Question3ET, Question4ET, Question5ET, Question6ET, Question7ET, Question8ET, Question9ET, Question10ET, Question11ET, Question12ET;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_erstellen_details);

        Add = (Button)findViewById(R.id.AddButtonSIGNSErstellenDetail);
        Back = (Button)findViewById(R.id.BackButtonSIGNSErstellenDetail);
        FirstQuestionET = (EditText)findViewById(R.id.FirstQuestionSED);
        fOption1ET = (EditText)findViewById(R.id.fOption1SED);
        fOption2ET = (EditText)findViewById(R.id.fOption2SED);
        fOption3ET = (EditText)findViewById(R.id.fOption3SED);
        SecondQuestionET = (EditText)findViewById(R.id.SecondQuestionSED);
        sOption1ET = (EditText)findViewById(R.id.sOption1SED);
        sOption2ET = (EditText)findViewById(R.id.sOption2SED);
        sOption3ET = (EditText)findViewById(R.id.sOption3SED);

        Question1ET = (EditText)findViewById(R.id.Question1SED);
        Question2ET = (EditText)findViewById(R.id.Question2SED);
        Question3ET = (EditText)findViewById(R.id.Question3SED);
        Question4ET = (EditText)findViewById(R.id.Question4SED);
        Question5ET = (EditText)findViewById(R.id.Question5SED);
        Question6ET = (EditText)findViewById(R.id.Question6SED);
        Question7ET = (EditText)findViewById(R.id.Question7SED);
        Question8ET = (EditText)findViewById(R.id.Question8SED);
        Question9ET = (EditText)findViewById(R.id.Question9SED);
        Question10ET = (EditText)findViewById(R.id.Question10SED);
        Question11ET = (EditText)findViewById(R.id.Question11SED);
        Question12ET = (EditText)findViewById(R.id.Question12SED);


        FirstQuestionET.setOnFocusChangeListener(this);
        fOption1ET.setOnFocusChangeListener(this);
        fOption2ET.setOnFocusChangeListener(this);
        fOption3ET.setOnFocusChangeListener(this);
        SecondQuestionET.setOnFocusChangeListener(this);
        sOption1ET.setOnFocusChangeListener(this);
        sOption1ET.setOnFocusChangeListener(this);
        sOption1ET.setOnFocusChangeListener(this);

        Question1ET.setOnFocusChangeListener(this);
        Question2ET.setOnFocusChangeListener(this);
        Question3ET.setOnFocusChangeListener(this);
        Question4ET.setOnFocusChangeListener(this);
        Question5ET.setOnFocusChangeListener(this);
        Question6ET.setOnFocusChangeListener(this);
        Question7ET.setOnFocusChangeListener(this);
        Question8ET.setOnFocusChangeListener(this);
        Question9ET.setOnFocusChangeListener(this);
        Question10ET.setOnFocusChangeListener(this);
        Question11ET.setOnFocusChangeListener(this);
        Question12ET.setOnFocusChangeListener(this);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignsErstellenDetails.this, SignsErstellen.class);
                editor.putString("firstQuestion", FirstQuestionET.getText().toString());
                editor.putString("fOption1", fOption1ET.getText().toString());
                editor.putString("fOption2", fOption2ET.getText().toString());
                editor.putString("fOption3", fOption3ET.getText().toString());
                editor.putString("secondQuestion", SecondQuestionET.getText().toString());
                editor.putString("sOption1", sOption1ET.getText().toString());
                editor.putString("sOption2", sOption2ET.getText().toString());
                editor.putString("sOption3", sOption3ET.getText().toString());
                editor.putString("Question1", Question1ET.getText().toString());
                editor.putString("Question2", Question2ET.getText().toString());
                editor.putString("Question3", Question3ET.getText().toString());
                editor.putString("Question4", Question4ET.getText().toString());
                editor.putString("Question5", Question5ET.getText().toString());
                editor.putString("Question6", Question6ET.getText().toString());
                editor.putString("Question7", Question7ET.getText().toString());
                editor.putString("Question8", Question8ET.getText().toString());
                editor.putString("Question9", Question9ET.getText().toString());
                editor.putString("Question10", Question10ET.getText().toString());
                editor.putString("Question11", Question11ET.getText().toString());
                editor.putString("Question12", Question12ET.getText().toString());

                editor.commit();

                startActivity(intent);

            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirstQuestionET.getText().toString().equals("");
                fOption1ET.getText().toString().equals("");
                fOption2ET.getText().toString().equals("");
                fOption3ET.getText().toString().equals("");

                SecondQuestionET.getText().toString().equals("");
                sOption1ET.getText().toString().equals("");
                sOption2ET.getText().toString().equals("");
                sOption3ET.getText().toString().equals("");

                Question1ET.getText().toString().equals("");
                Question2ET.getText().toString().equals("");
                Question3ET.getText().toString().equals("");
                Question4ET.getText().toString().equals("");
                Question5ET.getText().toString().equals("");
                Question6ET.getText().toString().equals("");
                Question7ET.getText().toString().equals("");
                Question8ET.getText().toString().equals("");
                Question9ET.getText().toString().equals("");
                Question10ET.getText().toString().equals("");
                Question11ET.getText().toString().equals("");
                Question12ET.getText().toString().equals("");


                Intent intent = new Intent(SignsErstellenDetails.this, SignsErstellen.class);
                startActivity(intent);

            }
        });





    }

    @Override
    public void onFocusChange(View view, boolean b) {

        if (!b) {
            hideKeyboard(view);
        }


    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}