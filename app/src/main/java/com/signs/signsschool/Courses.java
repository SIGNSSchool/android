package com.signs.signsschool;

import android.content.Intent;
import android.os.Bundle;
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
import com.signs.signsschool.common.Common;
import com.signs.signsschool.models.Course;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Courses extends AppCompatActivity {

    ListView listview;
    Button Fertig;
    TextView Grade;
    CoursesAdapter adapter;
    Integer setgradeString = 5;
    EditText searchView;
    ArrayList<Course> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurse);

        listview = (ListView) findViewById(R.id.ListViewKurse);
        Fertig = (Button) findViewById(R.id.FertigButtonKurse);

        //searchView = (EditText)findViewById(R.id.searchViewKurse);

        switch (getIntent().getStringExtra("Grade")) {
            case "Fünfter":

                setgradeString = 5;
                break;
            case "Sechster":

                setgradeString = 6;
                break;
            case "Siebter":

                setgradeString = 7;
                break;
            case "Achter":

                setgradeString = 8;
                break;
            case "Neunter":

                setgradeString = 9;
                break;
            case "Zehnter":

                setgradeString = 10;
                break;
            case "EF":

                setgradeString = 11;
                break;
            case "Q1":

                setgradeString = 12;
                break;
            case "Q2":

                setgradeString = 13;
                break;
        }


        Grade = (TextView) findViewById(R.id.GradeTVKurse);
        String gradetext = getIntent().getStringExtra("Grade") + " " + "Jahrgang";
        Grade.setText(gradetext);

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Courses.this, Menu.class);
                intent.putExtra("sendervalueMenu", "kurse");
                startActivity(intent);

            }
        });

        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/users/courses/"+Common.getAccountParamByKey("schoolId", this), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("courses", response);

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject course = jsonArray.getJSONObject(i);

                        list.add(new Course(course));
                    }

                    adapter = new CoursesAdapter(listview.getContext(), list);
                    listview.setAdapter(adapter);

                } catch (Exception e) {

                    Log.e("error", String.valueOf(e));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(request);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Course model = list.get(i);
                
                Intent intent = new Intent(Courses.this, CoursesDetail.class);

                String name = model.getTeacher().getFirstName() + " " + model.getTeacher().getLastName();

                intent.putExtra("name", name);
                intent.putExtra("subject", model.getCourse());
                intent.putExtra("courseId", model.getCourseId());

                intent.putExtra("participants", model.getParticipants().toString());
                intent.putExtra("lessons", model.getLessons().toString());
                startActivity(intent);
            }
        });
    }
}