package com.signs.signsschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class StundenVertretung extends AppCompatActivity {

    Button Abbrechen, Fertig;
    String STORE_URL = "https://signs.school/StoreStundenVertretung.php";
    String CancelledString, DayNumber;
    EditText Alternative, Room, Teacher;
    RequestQueue requestQueue;
    Switch Cancelled;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stunden_vertretung);


        Spinner TagSpinner = findViewById(R.id.spinnerTagSV);
        Spinner StundeSpinner = findViewById(R.id.spinnerStundeSV);
        Fertig = (Button)findViewById(R.id.FertigButtonSV);
        Abbrechen = (Button)findViewById(R.id.AbbrechenButtonSV);
        ArrayAdapter<CharSequence> TagAdapter = ArrayAdapter.createFromResource(this, R.array.DayDates, android.R.layout.simple_spinner_item);
        TagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TagSpinner.setAdapter(TagAdapter);

        Cancelled = findViewById(R.id.switchStundenVertretung);

        DayNumber = "";

        if (Cancelled.isChecked()) {

            CancelledString = "YES";

        } else {
            CancelledString = "NO";
        }


        ArrayAdapter<CharSequence> StundeAdapter = ArrayAdapter.createFromResource(this, R.array.Stunden, android.R.layout.simple_spinner_item);
        StundeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        StundeSpinner.setAdapter(StundeAdapter);


        Abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Room.getText().toString().equals("");
                Teacher.getText().toString().equals("");
                Alternative.getText().toString().equals("");

                Intent intent = new Intent(StundenVertretung.this, Home.class);
                startActivity(intent);

            }
        });


        Alternative = (EditText)findViewById(R.id.MessageEditTextStundenV);
        Room = (EditText)findViewById(R.id.RoomEditTextStundenV);
        Teacher = (EditText)findViewById(R.id.TeacherEditTextStundenV2);


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Fertig.setOnClickListener(new View.OnClickListener() {
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

                        Intent intentget = getIntent();

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("alternative", Alternative.getText().toString());
                            parameters.put("Hour", StundeSpinner.getSelectedItem().toString());
                            parameters.put("newTeacher", Teacher.getText().toString());
                            parameters.put("newRoom", Room.getText().toString());
                            parameters.put("day", TagSpinner.getSelectedItem().toString());
                            parameters.put("schoolID", prefs.getString("school_id", String.valueOf(0)));
                            parameters.put("courseID", String.valueOf(intentget.getStringExtra("courseID")));
                            parameters.put("Class", intentget.getStringExtra("Class"));
                            parameters.put("Cancelled", CancelledString);
                            parameters.put("Subject", intentget.getStringExtra("Subject"));


                            return parameters;
                        }
                    };
                    requestQueue.add(request);
                }

        });



    }
}