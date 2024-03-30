package com.signs.signsschool;

import androidx.appcompat.app.AppCompatActivity;

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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rooms extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RequestQueue requestQueue;

    ArrayList<String> rooms = new ArrayList<>();
    ArrayList<String> days = new ArrayList<>();
    ArrayList<String> hours = new ArrayList<>();

    Spinner roomSpinner, daySpinner, hourSpinner;
    ArrayAdapter<String> roomsadapter, hoursadapter, daysadapter;

    ArrayList<String> allhours = new ArrayList<>();

    JSONArray subArray, searchArray;
    JSONObject subObject, roomObject;

    Integer firstIndex = 0;
    String dayIndex;
    String room;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        Button Fertig = findViewById(R.id.FertigButtonRooms);
        Button MyRooms = findViewById(R.id.myroomsButtonRooms);
        Button reserve = findViewById(R.id.reserveButtonRooms);


        roomSpinner = findViewById(R.id.roomSpinnerRooms);
        daySpinner = findViewById(R.id.daySpinnerRooms);
        hourSpinner = findViewById(R.id.hourSpinnerRooms);

        days.addAll(Arrays.asList("Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"));


         roomsadapter =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, rooms);
        roomsadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        roomSpinner.setAdapter(roomsadapter);
        roomSpinner.setOnItemSelectedListener(this);


         daysadapter =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, days);
        daysadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        daySpinner.setAdapter(daysadapter);
        daySpinner.setOnItemSelectedListener(this);


        hoursadapter =
                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, hours);
        hoursadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hourSpinner.setAdapter(hoursadapter);
        hourSpinner.setOnItemSelectedListener(this);




        requestQueue = Volley.newRequestQueue(getApplicationContext());


        String url = "https://signs.school/GetMyRooms.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);


                    JSONArray jArray = jObject.getJSONArray("response");





                    for (int i=0; i < jArray.length(); i++) {

                        JSONObject oneObject = jArray.getJSONObject(i);


                        searchArray = jObject.getJSONArray("response");
                        // hour
                        subArray = jObject.getJSONArray("response").getJSONObject(firstIndex).getJSONObject("array").getJSONArray(dayIndex);
                        subObject = jObject.getJSONArray("response").getJSONObject(firstIndex).getJSONObject("array");
                        roomObject = jObject.getJSONArray("response").getJSONObject(firstIndex);



                        // allhours

                        // make one single array with all days and corresponding hours and change the data with the spinners


                        System.out.println(subArray);
                        System.out.println("-----");
                        System.out.println(subObject);




                        Log.i("ROOM", oneObject.getString("room"));

                        rooms.add(oneObject.getString("room"));



                    }


                    for (int x=0; x < subArray.length(); x++) {

                        hours.add(String.valueOf(subArray.getInt(x)));
                    }

                    roomsadapter.notifyDataSetChanged();
                    hoursadapter.notifyDataSetChanged();
                    daysadapter.notifyDataSetChanged();


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
                params.put("user",  prefs.getString("user_id", "0"));
                params.put("school_id", prefs.getString("school_id", "0"));
                params.put("status", String.valueOf(2));

                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);




        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Rooms.this, Home.class));
            }
        });

        MyRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Rooms.this, myRooms.class));
            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest request = new StringRequest(Request.Method.POST, "https://signs.school/StoreRoomReservation.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("response", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);

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
                        params.put("user_id", prefs.getString("user_id", "0"));
                        params.put("hour", hourSpinner.getSelectedItem().toString());
                        params.put("day", daySpinner.getSelectedItem().toString());
                        params.put("room", roomSpinner.getSelectedItem().toString());

                        Log.d("params", String.valueOf(params));


                        return params;
                    }
                };
                request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
                requestQueue.add(request);




            }
        });
        System.out.println("BELOW");
        System.out.println(allhours);
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            switch (adapterView.getId()) {

                case R.id.roomSpinnerRooms:

                    System.out.println("CHANGING SPINNER");

                    room = adapterView.getSelectedItem().toString();

                    try {

                        // try to use searchArray, without firstIndex!

                        for (int f = 0; f < searchArray.length(); f++) {

                            Log.i("loop", String.valueOf(f));
                            System.out.println(searchArray.getJSONObject(f).getString("room")+"<--->"+roomSpinner.getSelectedItem().toString());

                            if (searchArray.getJSONObject(f).getString("room").equals(roomSpinner.getSelectedItem().toString())) {

                                System.out.println("EQUALS");
                                firstIndex = f;
                                hours.clear();
                                for (int n = 0; n < searchArray.getJSONObject(f).getJSONObject("array").getJSONArray(dayIndex).length(); n++) {
                                    hours.add(String.valueOf(searchArray.getJSONObject(f).getJSONObject("array").getJSONArray(dayIndex).getInt(n)));
                                }

                                Log.i("index", String.valueOf(firstIndex));
                            }
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    hoursadapter.notifyDataSetChanged();

                    break;
                case R.id.daySpinnerRooms:

                    hours.clear();

                        switch (adapterView.getSelectedItem().toString()) {

                            case "Montag":
                                dayIndex = "M";
                                setHoursArray(dayIndex, firstIndex);



                                break;
                            case "Dienstag":
                                dayIndex = "T";
                                setHoursArray(dayIndex, firstIndex);




                                break;
                            case "Mittwoch":
                                dayIndex = "W";
                                setHoursArray(dayIndex, firstIndex);



                                break;
                            case "Donnerstag":
                                dayIndex = "Th";
                                setHoursArray(dayIndex, firstIndex);



                                break;
                            case "Freitag":
                                dayIndex = "F";
                                setHoursArray(dayIndex, firstIndex);



                                break;


                        }

                    break;



            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setHoursArray(String day, Integer roomIndex) {

        hours.clear();

        try {

            for (int i = 0; i < searchArray.getJSONObject(roomIndex).getJSONObject("array").getJSONArray(day).length(); i++) {

                hours.add(String.valueOf(searchArray.getJSONObject(roomIndex).getJSONObject("array").getJSONArray(day).getInt(i)));
                System.out.println(i);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        hoursadapter.notifyDataSetChanged();

    }



}