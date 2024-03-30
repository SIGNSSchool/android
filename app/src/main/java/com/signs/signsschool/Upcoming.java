package com.signs.signsschool;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Upcoming extends AppCompatActivity {


    ListAdapterUpcoming adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        Button menu = findViewById(R.id.buttonUpcoming);
        ListView listview = findViewById(R.id.listviewUpcoming);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Upcoming.this, Menu.class);
                intent.putExtra("sendervalueMenu", "marks_student");
                startActivity(intent);

            }
        });




        String url = "https://signs.school/upcoming.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");


                    List<com.signs.signsschool.models.Upcoming> list = new ArrayList<>();



                    ArrayList<String> dates = new ArrayList<>();
                    ArrayList<String> grades = new ArrayList<>();




                    for (int i=0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);

                        String dateString = oneObject.getString("date");
                        String titleString = oneObject.getString("subject");



                        try {
                            Date today = new Date();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = formatter.parse(dateString);
                            System.out.println(date+"---"+today+"----"+((today.getTime() - date.getTime()) / (1000* 24 * 60 *60)));


                            long diff = Math.abs(date.getTime() - today.getTime());
                            long day = 1000 * 24 * 60 * 60;
                            double rem_days = Math.ceil(diff / day);


                            String days = "noch "+Integer.valueOf((int) rem_days)+" Tage bis";

                            list.add(new com.signs.signsschool.models.Upcoming(titleString, days));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    adapter = new ListAdapterUpcoming(listview.getContext(), list);
                    listview.setAdapter(adapter);

                } catch (Exception e) {

                    Log.i("error", String.valueOf(e));

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }}) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Intent intent = getIntent();
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("school_id", prefs.getString("school_id", "0"));
                params.put("grade", intent.getStringExtra("grade"));
                params.put("status", String.valueOf(1));

                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);




    }
}