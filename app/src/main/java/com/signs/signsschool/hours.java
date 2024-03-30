package com.signs.signsschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.signs.signsschool.models.ModelDate;
import com.signs.signsschool.models.Hours;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class hours extends AppCompatActivity {

    RecyclerView recyclerView;
    String sub;
    HoursAdapter adapter;
    TextView hour1, hour2, hour3, hour4, hour5, hour6, hour7, hour8, hour9, hour10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);

        recyclerView =  findViewById(R.id.gridViewhours);
        
        hour1 = findViewById(R.id.textView53);
        hour2 = findViewById(R.id.textView63);
        hour3 = findViewById(R.id.textView76);
        hour4 = findViewById(R.id.textView77);
        hour5 = findViewById(R.id.textView78);
        hour6 = findViewById(R.id.textView79);
        hour7 = findViewById(R.id.textView80);
        hour8 = findViewById(R.id.textView81);
        hour9 = findViewById(R.id.textView82);
        hour10 = findViewById(R.id.textView83);



        final int[] overallXScroll = {0};
        final int[] scrollPosition = new int[1];

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                overallXScroll[0] = overallXScroll[0] +dx;

                if ((0 <= overallXScroll[0]) && (overallXScroll[0] < 320)) {

                    scrollPosition[0] = 1;
                    System.out.println("position"+dx);
                } else if ((overallXScroll[0] > 320) && (overallXScroll[0] < 640)) {

                    scrollPosition[0] = 51;
                    System.out.println("position"+dx);
                } else if (overallXScroll[0] > 640) {

                    scrollPosition[0] = 101;
                    System.out.println("position"+dx);
                }


            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        System.out.println("The RecyclerView is not scrolling");
                        recyclerView.scrollToPosition(scrollPosition[0]);
                        System.out.println("scrollingTo"+scrollPosition[0]);

                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        System.out.println("Scrolling now");
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        System.out.println("Scroll Settling");

                        //recyclerView.scrollToPosition(scrollPosition[0]);

                        System.out.println("Setteled=scrollingTo"+scrollPosition[0]);

                        break;

                }

            }

        });




        Button done = findViewById(R.id.doneButtonhours);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(hours.this, Home.class));
            }
        });

        String timeurl = "https://signs.school/GetTimes.php";

        StringRequest timerequest = new StringRequest(Request.Method.POST, timeurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");

                    System.out.println("Hours Array"+jArray);

                    for (int i=0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);

                        String first_hour = oneObject.getString("row_1");
                        String second_hour = oneObject.getString("row_2");
                        String third_hour = oneObject.getString("row_3");
                        String fourfth_hour = oneObject.getString("row_4");
                        String fifth_hour = oneObject.getString("row_5");
                        String sixth_hour = oneObject.getString("row_6");
                        String seventh_hour = oneObject.getString("row_7");
                        String eighth_hour = oneObject.getString("row_8");
                        String ninth_hour = oneObject.getString("row_9");
                        String tenth_hour = oneObject.getString("row_10");


                        hour1.setText(first_hour);
                        hour2.setText(second_hour);
                        hour3.setText(third_hour);
                        hour4.setText(fourfth_hour);
                        hour5.setText(fifth_hour);
                        hour6.setText(sixth_hour);
                        hour7.setText(seventh_hour);
                        hour8.setText(eighth_hour);
                        hour9.setText(ninth_hour);
                        hour10.setText(tenth_hour);


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
        Volley.newRequestQueue(this).add(timerequest);



        String url = "https://signs.school/GetHOURS.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");
                    JSONArray dateArray = jObject.getJSONArray("dates");




                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 11, GridLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);


                    List<Hours> list = new ArrayList<>();
                    List<ModelDate> dateList = new ArrayList<>();



                    for (int i=0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);

                        String date = oneObject.getString("Date");
                        String day = oneObject.getString("Day");
                        Integer hour = oneObject.getInt("Hour");
                        Integer teacher = oneObject.getInt("Teacher");
                        String teachername = oneObject.getString("Teachername");
                        String subject = oneObject.getString("Subject");
                        String room = oneObject.getString("Room");
                        Integer status = oneObject.getInt("Status");
                        String cancelled = oneObject.getString("Cancelled");


                        switch(subject) {

                            case "Mathematik":

                                sub = "M";
                                break;
                            case "Deutsch":

                                sub = "D";
                                break;
                            case "Englisch":

                                sub = "E";
                                break;

                            case "Biologie":

                                sub = "B";
                                break;
                            case "Physik":

                                sub = "Ph";
                                break;

                            case "Chemie":

                                sub = "C";
                                break;

                            case "Politik":

                                sub = "Po";
                                break;

                            case "Wirtschaftslehre":
                            case "Wirtschaft" :

                                sub = "W";
                                break;

                            case "Hauswirtschaft":

                                sub = "HW";
                                break;

                            case "Religion":

                                sub = "R";
                                break;

                            case "Philosophie":

                                sub = "P";
                                break;

                            case "Sozialwissenschaften":
                                sub = "Sw";
                                break;

                            case "Geschichte":
                                sub = "Ge";
                                break;

                            case "Technik":
                                sub = "K";
                                break;

                            case "Französich":
                                sub = "F";
                                break;

                            case "Spanisch":
                                sub = "Sp";
                                break;

                            case "Sport":
                                sub = "S";
                                break;

                            case "Latein":
                                sub = "L";
                                break;

                            case "Türkisch":
                                sub = "T";
                                break;

                            case "Arbeitslehre":
                                sub = "Al";
                                break;

                            case "Musik":
                                sub = "M";
                                break;

                            case "Literatur":
                                sub = "Li";
                                break;

                            case "Algorithmen":
                                sub = "Al";
                                break;

                            case "blank":
                                sub = " ";
                                break;

                        }


                        list.add(new Hours(date, day, teachername, sub, room, cancelled, hour, teacher, status));

                        if (day.equals("H")) {

                            String subdate = date.substring(8, 10);

                            for (int j=0; j<11; j++) {
                                dateList.add(new ModelDate(subdate));

                            }
                           Log.i("Date", date);
                        }


                    }
                    adapter = new HoursAdapter(recyclerView.getContext(), list, dateList);
                    recyclerView.setAdapter(adapter);

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
                params.put("usermail", prefs.getString("user_id", "d"));  // prefs.getString(user_id, String.valueOf(0));


                Log.i("user_id", prefs.getString("user_id", "d"));
                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

    }
}