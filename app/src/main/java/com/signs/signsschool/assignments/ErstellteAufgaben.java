package com.signs.signsschool.assignments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ErstellteAufgaben extends AppCompatActivity {

    Button Fertig;
    ListView listview;
    String subjectEA;
    TextView FullName, Subject, Date;
    EditText searchView;
    ListAdapterErstellteAufgaben adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstellte_aufgaben);

        listview = (ListView) findViewById(R.id.ListViewErstellteAufgaben);
        Fertig = (Button) findViewById(R.id.FertigErstellteAufgaben);

       // searchView = (EditText)findViewById(R.id.searchViewErstellteAufgaben);


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ErstellteAufgaben.this, Menu.class);
                intent.putExtra("sendervalueMenu", "aufgaben_teacher");
                startActivity(intent);

            }
        });

        String url = "https://signs.school/GetErstellteAufgaben.php";

        Intent intentget = getIntent();

        subjectEA = intentget.getStringExtra("subject_aufgaben_teacher");

        //Log.i("subjectEAAAA", subjectEA);


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");



                    List<ModelErstellteAufgaben> list = new ArrayList<>();

                    ArrayList<String> messages = new ArrayList<>();
                    ArrayList<String> dates = new ArrayList<>();


                    for (int i=0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);

                        String titleString = oneObject.getString("Title");
                        String dayString = oneObject.getString("Day");
                        String dateString = oneObject.getString("Date");
                        String messageString = oneObject.getString("Description");
                        Integer message_id = oneObject.getInt("message_id");



                        messages.add(messageString);

                        int finalI = i;

                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                ModelErstellteAufgaben model = list.get(i);


                                Integer index = dates.indexOf(model.getDate());
                                String messageItem = messages.get(index);


                                Intent intent = new Intent(ErstellteAufgaben.this, ErstellteAufgabenAnzeige.class);

                                intent.putExtra("titleErstellteAufgaben", model.getName());
                                intent.putExtra("dateErstellteAufgaben", model.getDate());
                                intent.putExtra("descriptionErstellteAufgaben", messageItem);
                                intent.putExtra("message_idEA", model.getMessage_id());

                                startActivity(intent);


                            }
                        });


                        list.add(new ModelErstellteAufgaben(titleString, dayString, dateString, message_id));


                    }

                    adapter = new ListAdapterErstellteAufgaben(listview.getContext(), list);
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
                params.put("usermail", prefs.getString("user_id", String.valueOf(0)));
                params.put("Subject", subjectEA);

                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

    }

}