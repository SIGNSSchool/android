package com.signs.signsschool.absences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;
import com.signs.signsschool.absences.models.ModelAttendance;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attendance extends AppCompatActivity {

    ListView listview;
    AttendanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        Button menu = findViewById(R.id.menuButtonAttendance);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Attendance.this, Menu.class);
                intent.putExtra("sendervalueMenu", "entschuldigen_teacher");
                startActivity(intent);

            }
        });

        String url = "https://signs.school/GetPreAttendance.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");

                    listview =  findViewById(R.id.listviewAttendance);

                    List<ModelAttendance> list = new ArrayList<>();


                    for (int i=0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);

                        String coursenameString = oneObject.getString("coursename");
                        Integer gradeInt = oneObject.getInt("grade");
                        Integer course_idInt = oneObject.getInt("course_id");
                        String teacherString = oneObject.getString("teacher");
                        Integer hourInt = oneObject.getInt("Hour");

                        JSONArray json_students = oneObject.getJSONArray("students");



                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                ModelAttendance model = list.get(i);

                                Intent intent = new Intent(Attendance.this, AttendanceDetail.class);

                                intent.putExtra("nameAttendance", model.getCoursname());
                                intent.putExtra("course_idAttendance", model.getCourse_id());
                                intent.putExtra("gradeAttendance", model.getGrade());
                                intent.putExtra("studentsAttendance", model.getStudents().toString());


                                startActivity(intent);


                            }
                        });


                        list.add(new ModelAttendance(coursenameString, gradeInt, course_idInt, teacherString, hourInt, json_students));


                    }
                    adapter = new AttendanceAdapter(listview.getContext(), list);
                    listview.setAdapter(adapter);

                } catch (Exception e) {

                    Log.i("error", String.valueOf(e));

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }}) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Attendance.this);

            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("school_id", prefs.getString("school_id", "e"));
                params.put("user_id", prefs.getString("user_id", "e"));

                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

    }
}