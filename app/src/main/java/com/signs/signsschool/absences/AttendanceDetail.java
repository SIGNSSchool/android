package com.signs.signsschool.absences;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.RecipientAdapter;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;
import com.signs.signsschool.models.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class AttendanceDetail extends AppCompatActivity {

    List<User> list = new ArrayList<>();
    String test;
    SharedPreferences prefs;
    Intent intentget;
    RequestQueue requestQueue;
    JSONArray students;
    RecipientAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_view);

         prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Button menu = findViewById(R.id.menuButtonAttendanceView);
        Button confirm = findViewById(R.id.confirmButtonAttendanceView);
        ListView listView = findViewById(R.id.listviewAttendanceView);
        TextView testTV = findViewById(R.id.testAttendance);

        View popup = findViewById(R.id.popupAttendance);
        popup.setVisibility(View.GONE);
        testTV.setVisibility(View.GONE);

        Button yes = findViewById(R.id.yesButton);
        Button no = findViewById(R.id.noButton);

        yes.setVisibility(View.GONE);
        no.setVisibility(View.GONE);

        intentget = getIntent();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        String students_string = intentget.getStringExtra("studentsAttendance");

        try {
             students = new JSONArray(students_string);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test = "YES";
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test = "NO";
            }
        });




        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AttendanceDetail.this, Menu.class);
                intent.putExtra("sendervalueMenu", "entschuldigen_teacher");
                startActivity(intent);
            }
        });
/*

        for (int i=0; i < students.length(); i++) {

            try {

                JSONObject object = students.getJSONObject(i);

                try {

                    list.add(new User(object.getString("name"), "", object.getString("userId")));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter = new RecipientAdapter(listView.getContext(), list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                User model = list.get(i);

                TextView nameTV = view.findViewById(R.id.nameRecipient);

                if (names.contains(model.getName())) {

                    names.remove(model.getName());
                    userIds.remove(model.getId());

                    //indexes.remove(i);
                    nameTV.setTextColor(Color.BLACK);

                    System.out.println("contains");

                } else {

                    names.add(model.getName());
                    userIds.add(model.getId());
                    //indexes.add(i);
                    nameTV.setTextColor(Color.RED);

                    System.out.println("doesnt contain");
                }
            }
        });

        status = 0;
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if (status == 0) {
                  confirm.setText("Abbrechen");
                  status = 1;
                  popup.setVisibility(View.VISIBLE);
                  testTV.setVisibility(View.VISIBLE);
                  yes.setVisibility(View.VISIBLE);
                  no.setVisibility(View.VISIBLE);

              } else {
                  confirm.setText("Bestätigen");
                  status = 0;
                  popup.setVisibility(View.GONE);
                  testTV.setVisibility(View.GONE);
                  yes.setVisibility(View.GONE);
                  no.setVisibility(View.GONE);
              }
            }
        });*/
    }
    public void storeCourseAttendance() {}
}