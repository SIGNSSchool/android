package com.signs.signsschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.models.ModelhoursEdit;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class hoursEdit extends AppCompatActivity {
    ListAdapterhoursEdit adapter;
    List<ModelhoursEdit> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_edit);

        ListView listView = findViewById(R.id.listViewHoursEdit);
        Button back = findViewById(R.id.FertigHoursEdit);
        Button filter = findViewById(R.id.filterButtonHoursEdit);


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(hoursEdit.this, hoursEditGrades.class));

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(hoursEdit.this, Home.class));
            }
        });


        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl()+"/hours"+Common.getAccountParamByKey("schoolId", this), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("hours", response);

                try {
                   JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hour = jsonArray.getJSONObject(i);


                        //list.add(new HoursModel());
                    }

                    adapter = new ListAdapterhoursEdit(listView.getContext(), list);
                    listView.setAdapter(adapter);

                } catch (Exception e) {
                    Log.e("error", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(request);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModelhoursEdit model = list.get(i);


                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(hoursEdit.this);
                SharedPreferences.Editor editor = prefs.edit();

                /*editor.putString("hourHoursEdit", intValue);
                editor.putString("dateHoursEdit", dateItem);
                editor.putInt("hour_idHoursEdit", hour_id);
                editor.apply();*/

                startActivity(new Intent(hoursEdit.this, hoursEditDetail.class));
            }
        });
    }
}