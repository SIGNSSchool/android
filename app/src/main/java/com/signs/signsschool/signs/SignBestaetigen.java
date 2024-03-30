package com.signs.signsschool.signs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignBestaetigen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Button Bestätigen, Abbrechen, Details;
    TextView Bestätigt, ErrorMessageOneTV, ErrorMessageTwoTV;
    EditText PasswordEditText;
    String finalName, finalSubject, finalDate, finalMessage;
    String FirstQuestion, FirstSelected, SecondQuestion, SecondSelected, Question1, Answer1, Question2, Answer2, Question3, Answer3, Question4, Answer4, Question5, Answer5, Question6, Answer6, Question7, Answer7, Question8, Answer8, Question9, Answer9, Question10, Answer10, Question11, Answer11, Question12, Answer12;

    Integer status;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_bestaetigen);

        Intent intent = getIntent();

        Bestätigen = (Button)findViewById(R.id.BestätigenButtonSIGNBestätigen);
        Abbrechen = (Button)findViewById(R.id.AbbrechenButtonSIGNBestätigen);
        Details = (Button)findViewById(R.id.detailsButtonSIGNBestätigen);
        PasswordEditText = (EditText)findViewById(R.id.PasswordTVSIGNBestätigen);
        ErrorMessageOneTV = (TextView)findViewById(R.id.ErrorMessageOneTV);
        ErrorMessageTwoTV = (TextView)findViewById(R.id.ErrorMessageTwoTV);

        Bestätigt.setVisibility(TextView.INVISIBLE);
        ErrorMessageTwoTV.setVisibility(TextView.INVISIBLE);
        ErrorMessageOneTV.setVisibility(TextView.INVISIBLE);

        PasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }
            }
        });



        Spinner statusSpinner = findViewById(R.id.statusSpinnerSB);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);
        statusSpinner.setOnItemSelectedListener(this);


        requestQueue =  Volley.newRequestQueue(getApplicationContext());




        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String answer = prefs.getString("Answer1SBD", "N/A");

        finalName = prefs.getString("signs_name", "N/A");
        finalSubject = prefs.getString("signs_subject", "N/A");
        finalDate = prefs.getString("signs_createdAt", "N/A");
        finalMessage = prefs.getString("signs_message", "N/A");


        FirstQuestion = prefs.getString("FirstQuestionSBD", "N/A");
        SecondQuestion = prefs.getString("SecondQuestionSBD", "N/A");

        Question1 = prefs.getString("Question1SBD", "N/A");
        Question2 = prefs.getString("Question2SBD", "N/A");
        Question3 = prefs.getString("Question3SBD", "N/A");
        Question4 = prefs.getString("Question4SBD", "N/A");
        Question5 = prefs.getString("Question5SBD", "N/A");
        Question6 = prefs.getString("Question6SBD", "N/A");
        Question7 = prefs.getString("Question7SBD", "N/A");
        Question8 = prefs.getString("Question8SBD", "N/A");
        Question9 = prefs.getString("Question9SBD", "N/A");
        Question10 = prefs.getString("Question10SBD", "N/A");
        Question11 = prefs.getString("Question11SBD", "N/A");
        Question12 = prefs.getString("Question12SBD", "N/A");



        FirstSelected = prefs.getString("FirstQuestionSelectedSBD", "N/A");
        SecondSelected = prefs.getString("SecondQuestionSelectedSBD", "N/A");
        Answer1 = prefs.getString("Answer1SBD", "N/A");
        Answer2 = prefs.getString("Answer2SBD", "N/A");
        Answer3 = prefs.getString("Answer3SBD", "N/A");
        Answer4 = prefs.getString("Answer4SBD", "N/A");
        Answer5 = prefs.getString("Answer5SBD", "N/A");
        Answer6 = prefs.getString("Answer6SBD", "N/A");
        Answer7 = prefs.getString("Answer7SBD", "N/A");
        Answer8 = prefs.getString("Answer8SBD", "N/A");
        Answer9 = prefs.getString("Answer9SBD", "N/A");
        Answer10 = prefs.getString("Answer10SBD", "N/A");
        Answer11 = prefs.getString("Answer11SBD", "N/A");
        Answer12 = prefs.getString("Answer12SBD", "N/A");


        String Buttonactive = intent.getStringExtra("Activated");

        if (Buttonactive == "activated") {

            Bestätigen.setEnabled(true);
        } else if (Buttonactive == "deactivated"){

            Bestätigen.setEnabled(false);
        }


        Abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignBestaetigen.this, Signs.class);
                startActivity(intent);

            }
        });

        Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bestätigen.setEnabled(true);
                Intent intent = new Intent(SignBestaetigen.this, SignBestaetigenDetails.class);
                startActivity(intent);

            }
        });

        String school_id = intent.getStringExtra("school_id");

        Bestätigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String STORE_URL = "https://signs.school/ConfirmSIGNSAndroidAPI.php";

                if (PasswordEditText.getText().toString().matches("")) {

                    ErrorMessageOneTV.setText("Bitte füllen Sie beide Felder aus");
                    return;
                }


                StringRequest request = new StringRequest(Request.Method.POST, STORE_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            Log.i("response", response);

                            if (Objects.equals(response, "GOOD")) {

                                ErrorMessageOneTV.setVisibility(TextView.VISIBLE);
                                ErrorMessageOneTV.setTextColor(Color.BLACK);
                                ErrorMessageOneTV.setText("Erledigt!");

                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                startActivity(new Intent(SignBestaetigen.this, Signs.class));

                                            }
                                        },
                                        1000
                                );

                            } else if (Objects.equals(response, "BAD")) {

                                ErrorMessageOneTV.setVisibility(TextView.VISIBLE);
                                ErrorMessageOneTV.setText("Mail oder passwort falsch");
                            }

                        } catch (Exception e) {

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }){

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    public Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("usermail", prefs.getString("user_id", String.valueOf(0)));
                        params.put("username", finalName);
                        params.put("teacher_id", prefs.getString("teacher_idSB", "0"));
                        params.put("Subject", finalSubject);
                        params.put("Date", finalDate);
                        params.put("Message", finalMessage);
                        params.put("status", String.valueOf(status));
                        params.put("password", PasswordEditText.getText().toString());
                        params.put("FirstQuestion", FirstQuestion);
                        params.put("FirstAnswer", FirstSelected);
                        params.put("SecondQuestion", SecondQuestion);
                        params.put("SecondAnswer", SecondSelected);

                        params.put("Question1", Question1);
                        params.put("Answer1", Answer1);
                        params.put("Question2", Question2);
                        params.put("Answer2", Answer2);
                        params.put("Question3", Question3);
                        params.put("Answer3", Answer3);
                        params.put("Question4", Question4);
                        params.put("Answer4", Answer4);
                        params.put("Question5", Question5);
                        params.put("Answer5", Answer5);
                        params.put("Question6", Question6);
                        params.put("Answer6", Answer6);
                        params.put("Question7", Question7);
                        params.put("Answer7", Answer7);
                        params.put("Question8", Question8);
                        params.put("Answer8", Answer8);
                        params.put("Question9", Question9);
                        params.put("Answer9", Answer9);
                        params.put("Question10", Question10);
                        params.put("Answer10", Answer10);
                        params.put("Question11", Question11);
                        params.put("Answer11", Answer11);
                        params.put("Question12", Question12);
                        params.put("Answer12", Answer12);

                        Log.d("params", String.valueOf(params));


                        return params;
                    }
                };

                requestQueue.add(request);

            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getSelectedItem().toString()) {


            case "Ich akzeptiere":

                status = 1;

                break;

            case "Ich lehne ab":

                status = 2;

                break;

        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}