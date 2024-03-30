package com.signs.signsschool.assignments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.Home;
import com.signs.signsschool.R;
import com.signs.signsschool.TokenAE;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AufgabenUebersicht extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button Erstellen, Fertig, Erstellte, Einsicht, Bewertungen;
    RequestQueue requestQueue;
    String text;
    Spinner subjectspinner;

    ArrayList<String> subjects = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aufgaben_uebersicht);


        Fertig = (Button)findViewById(R.id.button51);
        Erstellen = (Button)findViewById(R.id.button27);
        Erstellte = (Button)findViewById(R.id.ErstellteAufgabenButton);
        Einsicht = (Button)findViewById(R.id.EinsichtButtonAufgabeUebersicht);
        Bewertungen = (Button)findViewById(R.id.BewertungenAufgabenButton);



        requestQueue = Volley.newRequestQueue(getApplicationContext());

        /*Spinner classspinner = findViewById(R.id.spinner7);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.classes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classspinner.setAdapter(adapter);*/



        subjectspinner = findViewById(R.id.GradeSpinner);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        subjectspinner.setAdapter(adapter);
        subjectspinner.setOnItemSelectedListener(this);


        String url = "https://signs.school/GetLearning.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");



                    for (int i=0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);

                        String subject = oneObject.getString("Subject");
                        Integer status = oneObject.getInt("Status");

                        if (status == 0) {
                            subjects.add(subject);

                        }

                        adapter.notifyDataSetChanged();

                    }



                } catch (Exception e) {

                    Log.i("error", String.valueOf(e));

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }}) {


            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("school_id", prefs.getString("school_id", String.valueOf(0)));


                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AufgabenUebersicht.this, Home.class);
                startActivity(intent);
            }
        });

        Bewertungen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AufgabenUebersicht.this, AufgabenEvaluations.class);
                intent.putExtra("Subject", subjectspinner.getSelectedItem().toString());
                //intent.putExtra("Class", classspinner.getSelectedItem().toString());
                startActivity(intent);

            }
        });

        Erstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AufgabenUebersicht.this, TokenAE.class);
                startActivity(intent);

            }
        });


        Einsicht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AufgabenUebersicht.this, AufgabenEinsicht.class);

                intent.putExtra("Subject", subjectspinner.getSelectedItem().toString());


                startActivity(intent);
            }
        });
        Erstellte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AufgabenUebersicht.this, ErstellteAufgaben.class);
                intent.putExtra("SubjectErstellteAufgaben", subjectspinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }

}