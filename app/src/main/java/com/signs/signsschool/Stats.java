package com.signs.signsschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);


        Button Done = findViewById(R.id.doneButtonStats);
        TextView studentTV = findViewById(R.id.studentsTextViewStats);
        TextView teacherTV = findViewById(R.id.teachersTextViewStats);
        TextView courseTV = findViewById(R.id.coursesTextViewStats);
        TextView signsTV = findViewById(R.id.signsTextViewStats);
        TextView newsTV = findViewById(R.id.newsTextViewStats);

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Stats.this, Home.class));
            }
        });


        String url = "https://signs.school/GetStats.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);


                    studentTV.setText(jsonObject.getString("Students"));
                    teacherTV.setText(jsonObject.getString("Teachers"));
                    courseTV.setText(jsonObject.getString("courses"));
                    signsTV.setText(jsonObject.getString("signs"));
                    newsTV.setText(jsonObject.getString("news"));



                } catch (Exception e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            public Map<String, String> getParams() {


                Map<String, String> params = new HashMap<>();
                params.put("school_id", String.valueOf(prefs.getString("school_id", prefs.getString("user_id", "0"))));

                return params;
            }
        };
        Volley.newRequestQueue(Stats.this).add(request);


    }
}