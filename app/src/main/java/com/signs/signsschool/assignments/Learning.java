package com.signs.signsschool.assignments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.common.Config;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Learning extends AppCompatActivity {

    ListView listview;
    ListAdapterLearning adapter;
    Config config = new Config();

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        listview = findViewById(R.id.listViewLearning);
        Button newest = findViewById(R.id.newestTaskButton);

        newest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Learning.this, Fach.class);
                intent.putExtra("fach_type", 2);
                startActivity(intent);
            }
        });

        Button fertig = findViewById(R.id.learningButton);

        fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Learning.this, Menu.class);
                intent.putExtra("sendervalueMenu", "aufgaben_student");
                startActivity(intent);


            }
        });

        StringRequest request = new StringRequest(Request.Method.POST, config.apiUrl+"assignments/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");


                    List<ModelLearning> list = new ArrayList<>();


                    for (int i = 0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);

                        String subjectString = oneObject.getString("Subject");
                        String imageString = oneObject.getString("Image");
                        Integer statusInt = oneObject.getInt("Status");


                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int in, long l) {

                                TextView name = view.findViewById(R.id.subjectLearning);


                                String titleItem = name.getText().toString();


                                Intent intent = new Intent(Learning.this, Fach.class);

                                intent.putExtra("SubjectFach", titleItem);
                                intent.putExtra("fach_type", 1);
                                startActivity(intent);


                            }
                        });

                        if (statusInt == 0) {

                            list.add(new ModelLearning(subjectString, imageString, statusInt));
                        }

                    }

                    adapter = new ListAdapterLearning(listview.getContext(), list);
                    listview.setAdapter(adapter);

                } catch (Exception e) {

                    Log.i("error", String.valueOf(e));

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("school_id", prefs.getString("school_id", String.valueOf(0)));
                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

    }
}

