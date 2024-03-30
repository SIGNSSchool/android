package com.signs.signsschool.signs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

import java.util.ArrayList;

public class SignBestaetigenDetails extends AppCompatActivity {

    Button Continue, Back;
    Spinner FirstQuestion, SecondQuestion;
    TextView FirstQuestionTV, SecondQuestionTV;
    EditText Question1, Question2, Question3, Question4, Question5, Question6, Question7, Question8, Question9, Question10, Question11, Question12;
    String firstQuestionS, fOption1S, fOption2S, fOption3S, secondQuestionS, sOption1S, sOption2S, sOption3S;
    String Question1S, Question2S, Question3S, Question4S, Question5S, Question6S, Question7S, Question8S, Question9S, Question10S, Question11S, Question12S;
    String finalQuestion1, finalQuestion2, finalQuestion3, finalQuestion4, finalQuestion5, finalQuestion6, finalQuestion7, finalQuestion8, finalQuestion9, finalQuestion10, finalQuestion11, finalQuestion12;
    String finalAnswer1, finalAnswer2, finalAnswer3, finalAnswer4, finalAnswer5, finalAnswer6, finalAnswer7, finalAnswer8, finalAnswer9, finalAnswer10, finalAnswer11, finalAnswer12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_bestaetigen_details);



        Continue = findViewById(R.id.ContinueButtonSED);
        Back = findViewById(R.id.BackButtoSED);
        FirstQuestionTV = findViewById(R.id.FirstQuestionTVSED);
        SecondQuestionTV = findViewById(R.id.SecondQuestionTVSED);
        FirstQuestion = findViewById(R.id.FirstQuestionSpinnerSIGNBestätigenDetails);
        SecondQuestion = findViewById(R.id.SecondQuestionSpinnerSIGNBestätigenDetails);

        Question1 = findViewById(R.id.Question1SEDET);
        Question2 = findViewById(R.id.Question2SEDET);
        Question3 = findViewById(R.id.Question3SEDET);
        Question4 = findViewById(R.id.Question4SEDET);
        Question5 = findViewById(R.id.Question5SEDET);
        Question6 = findViewById(R.id.Question6SEDET);
        Question7 = findViewById(R.id.Question7SEDET);
        Question8 = findViewById(R.id.Question8SEDET);
        Question9 = findViewById(R.id.Question9SEDET);
        Question10 = findViewById(R.id.Question10SEDET);
        Question11 = findViewById(R.id.Question11SEDET);
        Question12 = findViewById(R.id.Question12SEDET);


        Question1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {

                    hideKeyboard(view);
                }
            }
        });



        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Question1S = prefs.getString("Question1SB", "N/A");
        Question2S = prefs.getString("Question2SB", "N/A");
        Question3S = prefs.getString("Question3SB", "N/A");
        Question4S = prefs.getString("Question4SB", "N/A");
        Question5S = prefs.getString("Question5SB", "N/A");
        Question6S = prefs.getString("Question6SB", "N/A");
        Question7S = prefs.getString("Question7SB", "N/A");
        Question8S = prefs.getString("Question8SB", "N/A");
        Question9S = prefs.getString("Question9SB", "N/A");
        Question10S = prefs.getString("Question10SB", "N/A");
        Question11S = prefs.getString("Question11SB", "N/A");
        Question12S = prefs.getString("Question12SB", "N/A");




        firstQuestionS = prefs.getString("firstQuestionSB", "N/A");
        secondQuestionS = prefs.getString("secondQuestionSB", "N/A");

        FirstQuestionTV.setText(firstQuestionS);
        SecondQuestionTV.setText(secondQuestionS);


        fOption1S = prefs.getString("fOption1SB", "N/A");
        fOption2S = prefs.getString("fOption2SB", "N/A");
        fOption3S = prefs.getString("fOption3SB", "N/A");


        sOption1S = prefs.getString("sOption1SB", "N/A");
        sOption2S = prefs.getString("sOption2SB", "N/A");
        sOption3S = prefs.getString("sOption3SB", "N/A");


        ArrayList<String> questionsONE = new ArrayList<>();
        questionsONE.add(fOption1S);
        questionsONE.add(fOption2S);
        questionsONE.add(fOption3S);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, questionsONE);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            FirstQuestion.setAdapter(adapter);

            ArrayList<String> questionsTWO = new ArrayList<>();
            questionsTWO.add(sOption1S);
            questionsTWO.add(sOption2S);
            questionsTWO.add(sOption3S);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, questionsTWO);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            SecondQuestion.setAdapter(adapter2);


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Log.i("real", Question1.getText().toString());
                }
            }, 5000);





            Question1.setHint(Question1S);
            Question2.setHint(Question2S);
            Question3.setHint(Question3S);
            Question4.setHint(Question4S);
            Question5.setHint(Question5S);
            Question6.setHint(Question6S);
            Question7.setHint(Question7S);
            Question8.setHint(Question8S);
            Question9.setHint(Question9S);
            Question10.setHint(Question10S);
            Question11.setHint(Question11S);
            Question12.setHint(Question12S);



        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (!Question1.getText().toString().equals("")) {

                    finalQuestion1 = Question1S;
                    finalAnswer1 = Question1.getText().toString();
                    Log.i("Test", finalAnswer1);

                } else if (Question1.getText().toString().equals("")) {

                    finalQuestion1 = Question1S;
                    finalAnswer1 = "N/A";
                }

                if (!Question2.getText().toString().equals("")) {

                    finalQuestion2 = Question2S;
                    finalAnswer2 = Question2.getText().toString();

                } else if (Question2.getText().toString().equals("")) {

                    finalQuestion2 = Question2S;
                    finalAnswer2 = "N/A";
                }
                if (!Question3.getText().toString().equals("")) {

                    finalQuestion3 = Question3S;
                    finalAnswer3 = Question3.getText().toString();

                } else if (Question3.getText().toString().equals("")) {

                    finalQuestion3 = Question3S;
                    finalAnswer3 = "N/A";
                }
                if (!Question4.getText().toString().equals("")) {

                    finalQuestion4 = Question4S;
                    finalAnswer4 = Question4.getText().toString();

                } else if (Question4.getText().toString().equals("")) {

                    finalQuestion4 = Question4S;
                    finalAnswer4 = "N/A";
                }
                if (!Question5.getText().toString().equals("")) {

                    finalQuestion5 = Question5S;
                    finalAnswer5 = Question5.getText().toString();

                } else if (Question5.getText().toString().equals("")) {

                    finalQuestion5 = Question5S;
                    finalAnswer5 = "N/A";
                }
                if (!Question6.getText().toString().equals("")) {

                    finalQuestion6 = Question6S;
                    finalAnswer6 = Question6.getText().toString();

                } else if (Question6.getText().toString().equals("")) {

                    finalQuestion6 = Question6S;
                    finalAnswer6 = "N/A";
                }

                if (!Question7.getText().toString().equals("")) {

                    finalQuestion7 = Question7S;
                    finalAnswer7 = Question7.getText().toString();

                } else if (Question7.getText().toString().equals("")) {

                    finalQuestion7 = Question7S;
                    finalAnswer7 = "N/A";
                }

                if (!Question8.getText().toString().equals("")) {

                    finalQuestion8 = Question8S;
                    finalAnswer8 = Question8.getText().toString();

                } else if (Question8.getText().toString().equals("")) {

                    finalQuestion8 = Question8S;
                    finalAnswer8 = "N/A";
                }
                if (!Question9.getText().toString().equals("")) {

                    finalQuestion9 = Question9S;
                    finalAnswer9 = Question9.getText().toString();

                } else if (Question9.getText().toString().equals("")) {

                    finalQuestion9 = Question9S;
                    finalAnswer9 = "N/A";
                }
                if (!Question10.getText().toString().equals("")) {

                    finalQuestion10 = Question10S;
                    finalAnswer10 = Question10.getText().toString();

                } else if (Question10.getText().toString().equals("")) {

                    finalQuestion10 = Question10S;
                    finalAnswer10 = "N/A";
                }
                if (!Question11.getText().toString().equals("")) {

                    finalQuestion11 = Question11S;
                    finalAnswer11 = Question11.getText().toString();

                } else if (Question11.getText().toString().equals("")) {

                    finalQuestion11 = Question11S;
                    finalAnswer11 = "N/A";
                }
                if (!Question12.getText().toString().equals("")) {

                    finalQuestion12 = Question12S;
                    finalAnswer12 = Question12.getText().toString();

                } else if (Question12.getText().toString().equals("")) {

                    finalQuestion12 = Question12S;
                    finalAnswer12 = "N/A";
                }


                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SignBestaetigenDetails.this);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("FirstQuestionSBD", FirstQuestionTV.getText().toString());
                editor.putString("FirstQuestionSelectedSBD", FirstQuestion.getSelectedItem().toString());
                editor.putString("SecondQuestionSelectedSBD", SecondQuestion.getSelectedItem().toString());
                editor.putString("SecondQuestionSBD", SecondQuestionTV.getText().toString());
                editor.putString("Question1SBD", finalQuestion1);
                editor.putString("Answer1SBD", finalAnswer1);
                editor.putString("Question2SBD", finalQuestion2);
                editor.putString("Answer2SBD", finalAnswer2);
                editor.putString("Question3SBD", finalQuestion3);
                editor.putString("Answer3SBD", finalAnswer3);
                editor.putString("Question4SBD", finalQuestion4);
                editor.putString("Answer4SBD", finalAnswer4);
                editor.putString("Question5SBD", finalQuestion5);
                editor.putString("Answer5SBD", finalAnswer5);
                editor.putString("Question6SBD", finalQuestion6);
                editor.putString("Answer6SBD", finalAnswer6);
                editor.putString("Question7SBD", finalQuestion7);
                editor.putString("Answer7SBD", finalAnswer7);
                editor.putString("Question8SBD", finalQuestion8);
                editor.putString("Answer8SBD", finalAnswer8);
                editor.putString("Question9SBD", finalQuestion9);
                editor.putString("Answer9SBD", finalAnswer9);
                editor.putString("Question10SBD", finalQuestion10);
                editor.putString("Answer10SBD", finalAnswer10);
                editor.putString("Question11SBD", finalQuestion11);
                editor.putString("Answer11SBD", finalAnswer11);
                editor.putString("Question12SBD", finalQuestion12);
                editor.putString("Answer12SBD", finalAnswer12);
                editor.commit();

                Intent intent = new Intent(SignBestaetigenDetails.this, SignBestaetigen.class);
                intent.putExtra("Activated", "activated");

                startActivity(intent);

            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignBestaetigenDetails.this, SignBestaetigen.class);
                startActivity(intent);

            }
        });



    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}