package com.signs.signsschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.signs.signsschool.models.Rooms;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myRooms extends AppCompatActivity {

    ListAdapterMyRooms adapter;
    ListView listview;
    String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rooms);

        Button Fertig = findViewById(R.id.FertigButtonMyRooms);
        listview = findViewById(R.id.listViewMyRooms);


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(myRooms.this, com.signs.signsschool.Rooms.class));
            }
        });

        String url = "https://signs.school/GetMyRooms.php";



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");


                    List<Rooms> list = new ArrayList<>();



                    for (int i=0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);

                        String roomString = oneObject.getString("Room");
                        String dayString = oneObject.getString("Day");
                        Integer hourInt = oneObject.getInt("Hour");

                        switch(dayString) {

                            case "M":
                                day = "Montag";
                                break;
                            case "T":
                                day = "Dienstag";
                                break;
                            case "W":
                                day = "Mittwoch";
                                break;
                            case "Th":
                                day = "Donnerstag";
                                break;
                            case "F":
                                day = "Freitag";
                                break;



                        }
                        
                        
                        
                        String info = "reserviert am "+day+" für die "+hourInt+". Stunde ";


                        list.add(new Rooms(roomString, info));

                    }

                    adapter = new ListAdapterMyRooms(listview.getContext(), list);
                    listview.setAdapter(adapter);

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
                params.put("user", prefs.getString("user_id", "0"));
                params.put("status", String.valueOf(1));
                params.put("school_id", prefs.getString("school_id", "0"));

                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);


    }


}