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
import com.signs.signsschool.Home;
import com.signs.signsschool.R;
import com.signs.signsschool.Tag;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fach extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.signscom2_2021.EXTRA_TEXT";

    Button Fertig;
    ListView listview;
    EditText searchView;
    ListAdapterFach adapter;
    TextView DateTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fach);

        Intent intent = getIntent();
        String text = intent.getStringExtra(Fach.EXTRA_TEXT);

        TextView head = findViewById(R.id.fachTV);

        if (intent.getIntExtra("fach_type", 0) == 2) {

            head.setText("neuste");
        } else if (intent.getIntExtra("fach_type", 0) == 1) {
            head.setText("aufgaben");
        }


       // searchView = (EditText)findViewById(R.id.searchBarFach);


        /*searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Fach.this.adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

            */

        /**Button button1 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAufgabeBearbeiten();
            }
        });*/


        Fertig = (Button)findViewById(R.id.FertigButtonFach);
        listview = (ListView)findViewById(R.id.ListViewFach);


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Fach.this, Home.class);
                startActivity(intent);

            }
        });

        String url = "https://signs.school/GetFach.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");


                    List<ModelFach> list = new ArrayList<>();




                    ArrayList<String> dates = new ArrayList<>();
                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<String> descriptions = new ArrayList<>();
                    ArrayList<String> images = new ArrayList<>();



                    for (int i=0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);

                        String dayString = oneObject.getString("Day");
                        String submissiondateString = oneObject.getString("SubmissionDate");
                        String titleString = oneObject.getString("Title");
                        String imageString = oneObject.getString("Image");
                        String descriptionString = oneObject.getString("Description");



                        dates.add(submissiondateString);
                        titles.add(titleString);
                        images.add(imageString);
                        descriptions.add(descriptionString);





                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                TextView day = view.findViewById(R.id.dayFach);
                                TextView date = view.findViewById(R.id.dateFach);

                                ModelFach model = list.get(i);


                                String dateItem = date.getText().toString();

                                Integer index = dates.indexOf(dateItem);
                                String descriptionItem = descriptions.get(index);
                                String titleItem = titles.get(index);
                                String imageItem = images.get(index);



                                Intent intent = new Intent(Fach.this, Tag.class);

                                intent.putExtra("titleFA", titleItem);
                                intent.putExtra("dateFA", dateItem);
                                intent.putExtra("descriptionFA", descriptionItem);
                                intent.putExtra("imageFA", imageItem);

                                startActivity(intent);


                            }
                        });

                        list.add(new ModelFach(dayString, submissiondateString));

                    }

                    adapter = new ListAdapterFach(listview.getContext(), list);
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
                params.put("Subject", intent.getStringExtra("SubjectFach"));
                params.put("type", String.valueOf(intent.getIntExtra("fach_type", 0)));

                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

    }



}





