package com.signs.signsschool.assignments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.R;

import java.util.HashMap;
import java.util.Map;

public class LAufgabenEinsichtAufgabeAntwort extends AppCompatActivity {

    Button Abbrechen, Bestätigen;
    TextView Aufgabe, submissionDate, nameStudent, description;
    EditText Evaluation;
    RequestQueue requestQueue;
    String STORE_URL = "https://signs.school/StoreAufgabeEvaluation.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_aufgaben_einsicht_aufgabe_antwort);

        Intent intentget = getIntent();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Abbrechen = (Button)findViewById(R.id.AbbrechenButtonEvaluation);
        Bestätigen = (Button)findViewById(R.id.BestätigenButtonEvaluation);

        Aufgabe = (TextView)findViewById(R.id.TextAbgegebenAufgabeAntwort);
        submissionDate = (TextView)findViewById(R.id.submissionDateEvaluation);
        nameStudent = (TextView)findViewById(R.id.nameStudentEvaluation);


        Evaluation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }

            }
        });


        nameStudent.setText(prefs.getString("nameAufgabeEinsicht", "Ein Fehler ist aufgetreten"));
        submissionDate.setText(prefs.getString("dateAufgabeEinsicht", "Ein Fehler ist aufgetreten"));
        Aufgabe.setText(prefs.getString("descriptionAufgabeEinsicht", "Ein Fehler ist aufgetreten"));


        Evaluation = (EditText)findViewById(R.id.EvaluationEditTextEvaluation);

        requestQueue = Volley.newRequestQueue(getApplicationContext());



        String user_id = intentget.getStringExtra("userLEvaluation");
        String Date = intentget.getStringExtra("DateLEvaluation");
        String Title = intentget.getStringExtra("TitleLEvaluation");
        String Subject = intentget.getStringExtra("SubjectLEvaluation");
        String course_id = intentget.getStringExtra("course_idLEvaluation");
        String Description = intentget.getStringExtra("DescriptionLEvaluation");

       // Log.i("user", user_id);
       // Log.i("Name", Name);

        Abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LAufgabenEinsichtAufgabeAntwort.this, AufgabenEinsicht.class);

                Evaluation.getText().toString().equals("");
                startActivity(intent);

            }
        });

        Bestätigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest request = new StringRequest(Request.Method.POST, STORE_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("response", response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("user", String.valueOf(prefs.getInt("userAufgabeEinsicht", 0)));
                        parameters.put("message_id", String.valueOf(prefs.getInt("message_idAufgabeEinsicht", 0)));
                        parameters.put("name", nameStudent.getText().toString());
                        parameters.put("school_id", prefs.getString("school_id", String.valueOf(0)));
                        parameters.put("evaluation", Evaluation.getText().toString());

                        return parameters;
            }
        };
                requestQueue.add(request);

                //Intent intent = new Intent(LAufgabenEinsichtAufgabeAntwort.this, LAufgabeEinsicht.class);
                //startActivity(intent);

    }
});

    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}