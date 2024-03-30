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
import java.util.List;

public class AufgabenEinsicht extends AppCompatActivity {


    Button Fertig, Detail;
    TextView FullName, Date, Status, Title;
    ListView listview;
    ListAdapterLAufgabeEinsicht adapter;
    EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_aufgabe_einsicht);


        Intent intent1 = getIntent();
        Intent intent2 = getIntent();
        List<ModelLAufgabeEinsicht> list = new ArrayList<>();

        String intentRes1 = intent1.getStringExtra("RESULT1");
        String intentRes2 = intent2.getStringExtra("RESULT2");


        FullName = (TextView) findViewById(R.id.nameTVAufgabenE);
        Date = (TextView) findViewById(R.id.DateTVAufgabenE);
        Status = (TextView) findViewById(R.id.StatusTVAufgabenE);
        Title = (TextView) findViewById(R.id.TitleTVAufgabenE);
        //searchView = (EditText) findViewById(R.id.searchViewAufgabeEinsicht);
        Fertig = (Button)findViewById(R.id.button111);


        listview = (ListView) findViewById(R.id.ListViewAufgabeEinsicht);



        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AufgabenEinsicht.this, Menu.class);
                intent.putExtra("sendervalueMenu", "aufgaben_teacher");
                startActivity(intent);
            }
        });

        String url = "https://signs.school/GetAufgabenEinsicht.php";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");




                    ArrayList<String> dates = new ArrayList<>();
                    ArrayList<String> descriptions = new ArrayList<>();
                    ArrayList<String> images = new ArrayList<>();
                    ArrayList<Integer> course_ids = new ArrayList<>();
                    ArrayList<String> subjects = new ArrayList<>();
                    ArrayList<String> titles = new ArrayList<>();
                    ArrayList<Integer> ids = new ArrayList<>();

                    for (int i=0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);

                        String nameString = oneObject.getString("Name");
                        String titleString = oneObject.getString("Title");
                        String dateString = oneObject.getString("SubmissionDate");
                        String descriptionString = oneObject.getString("Description");
                        String imageString = oneObject.getString("Image");
                        Integer course_idInt = oneObject.getInt("course_id");
                        Integer user_idInt = oneObject.getInt("user_id");
                        Integer message_idInt = oneObject.getInt("messageID");
                        String subjectString = oneObject.getString("Subject");





                        list.add(new ModelLAufgabeEinsicht(nameString, dateString, titleString, course_idInt, subjectString, user_idInt, message_idInt, descriptionString, imageString));
                    }

                    adapter = new ListAdapterLAufgabeEinsicht(listview.getContext(), list);
                    listview.setAdapter(adapter);

                } catch (Exception e) {

                    Log.i("error", String.valueOf(e));

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }});
        Volley.newRequestQueue(this).add(request);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView name = view.findViewById(R.id.nameLAufgabeEinsicht);
                TextView date = view.findViewById(R.id.dateLAufgabeEinsicht);

                String nameItem = name.getText().toString();
                String dateItem = date.getText().toString();


                ModelLAufgabeEinsicht model = list.get(i);

                Log.i("me", model.getName());


                String descriptionItem = model.getDescription();
                String imageItem = model.getImage();
                Integer course_idItem = model.getCourse_id();
                String subjectItem = model.getSubject();
                String titleItem = model.getTitle();

                Integer idItem = model.getId();
                Integer message_idItem = model.getMessage_id();



                Intent intent = new Intent(AufgabenEinsicht.this, LAufgabeEinsichtAufgabe.class);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AufgabenEinsicht.this);

                SharedPreferences.Editor editor = prefs.edit();

                editor.putInt("course_idAufgabeEinsicht", course_idItem);
                editor.putString("TitleAufgabeEinsicht", titleItem);
                editor.putInt("userAufgabeEinsicht", idItem);
                editor.putString("nameAufgabeEinsicht", nameItem);
                editor.putString("dateAufgabeEinsicht", dateItem);
                editor.putString("descriptionAufgabeEinsicht", descriptionItem);
                editor.putString("subjectAufgabeEinsicht", subjectItem);
                editor.putInt("message_idAufgabeEinsicht", message_idItem);


                editor.commit();

                intent.putExtra("nameAufgabeEinsicht", nameItem);
                intent.putExtra("dateAufgabeEinsicht", dateItem);
                intent.putExtra("descriptionAufgabeEinsicht", descriptionItem);
                intent.putExtra("imageAufgabeEinsicht", imageItem);
                intent.putExtra("message_idAufgabeEinsicht", course_idItem);
                intent.putExtra("subjectAufgabeEinsicht", subjectItem);

                startActivity(intent);

            }
        });
    }
}