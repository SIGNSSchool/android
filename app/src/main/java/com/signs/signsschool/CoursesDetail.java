package com.signs.signsschool;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.signs.signsschool.common.Simple;
import com.signs.signsschool.common.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.models.Choice;
import com.signs.signsschool.models.Lesson;
import com.signs.signsschool.models.User;

import org.json.JSONArray;

import java.util.ArrayList;

public class CoursesDetail extends AppCompatActivity {
    Button Fertig, Löschen;
    ListView listview;
    ArrayList<Lesson> lessons = new ArrayList<>();
    ArrayList<Simple> usernames = new ArrayList<>();
    ArrayList<User> participants = new ArrayList<>();
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurs_anzeige);

        Fertig = (Button) findViewById(R.id.FertigButtonKursAnzeige);
        listview = (ListView) findViewById(R.id.listViewKursAnzeige);
        Löschen = (Button) findViewById(R.id.LöschenButtonKursAnzeige);


        TextView course = findViewById(R.id.ClassTVKursAnzeige);
        TextView teacher = findViewById(R.id.TeacherTVKursAnzeige);
        TextView subject = findViewById(R.id.FachTVKursAnzeige);


        TextView MH1 = findViewById(R.id.MH1);
        TextView MR1 = findViewById(R.id.MR1);
        TextView MH2 = findViewById(R.id.MH2);
        TextView MR2 = findViewById(R.id.MR2);

        TextView TH1 = findViewById(R.id.TH1);
        TextView TR1 = findViewById(R.id.TR1);
        TextView TH2 = findViewById(R.id.TH2);
        TextView TR2 = findViewById(R.id.TR2);

        TextView WH1 = findViewById(R.id.WH1);
        TextView WR1 = findViewById(R.id.WR1);
        TextView WH2 = findViewById(R.id.WH2);
        TextView WR2 = findViewById(R.id.WR2);

        TextView ThH1 = findViewById(R.id.ThH1);
        TextView ThR1 = findViewById(R.id.ThR1);
        TextView ThH2 = findViewById(R.id.ThH2);
        TextView ThR2 = findViewById(R.id.ThR2);

        TextView FH1 = findViewById(R.id.FH1);
        TextView FR1 = findViewById(R.id.FR1);
        TextView FH2 = findViewById(R.id.FH2);
        TextView FR2 = findViewById(R.id.FR2);

        course.setText(getIntent().getStringExtra("course"));
        teacher.setText(getIntent().getStringExtra("teacher"));
        subject.setText(getIntent().getStringExtra("subject"));

        Log.i("lessons", getIntent().getStringExtra("lessons"));

        try {
            JSONArray jsonArray = new JSONArray(getIntent().getStringExtra("lessons"));
            for (int i=0; i < jsonArray.length(); i++) {
                lessons.add(new Lesson(jsonArray.getJSONObject(i)));
            }
        } catch (Exception e) {
            Log.e("error parsing lessons", e.toString());
        }


        for (int i = 0; i < lessons.size(); i++) {

            Log.i("Days", lessons.get(i).getDay());
            Switch:
            switch (lessons.get(i).getDay()) {
                case "M":
                    if (MH1.getText().toString().equals("")) {

                        MH1.setText(lessons.get(i).getHour());
                        MR1.setText(lessons.get(i).getRoom());

                        continue;

                    } else if (MH2.getText().toString().equals("")) {

                        MH2.setText(lessons.get(i).getHour());
                        MR2.setText(lessons.get(i).getRoom());

                        continue;
                    }
                    break;

                case "T":

                    if (TH1.getText().toString().equals("")) {

                        TH1.setText(lessons.get(i).getHour());
                        TR1.setText(lessons.get(i).getRoom());


                        continue;

                    } else if (TH2.getText().toString().equals("")) {

                        TH2.setText(lessons.get(i).getHour());
                        TR2.setText(lessons.get(i).getRoom());

                        continue;
                    }

                    break;
                case "W":


                    if (WH1.getText().toString().equals("")) {

                        WH1.setText(lessons.get(i).getHour());
                        WR1.setText(lessons.get(i).getRoom());


                        continue;

                    } else if (WH2.getText().toString().equals("")) {

                        WH2.setText(lessons.get(i).getHour());
                        WR2.setText(lessons.get(i).getRoom());

                        continue;
                    }

                    break Switch;


                case "Th":

                    if (ThH1.getText().toString().equals("")) {

                        ThH1.setText(lessons.get(i).getHour());
                        ThR1.setText(lessons.get(i).getRoom());


                        continue;

                    } else if (ThH2.getText().toString().equals("")) {

                        ThH2.setText(lessons.get(i).getHour());
                        ThR2.setText(lessons.get(i).getRoom());

                        continue;
                    }


                    break Switch;


                case "F":
                    if (FH1.getText().toString().equals("")) {

                        FH1.setText(lessons.get(i).getHour());
                        FR1.setText(lessons.get(i).getRoom());


                        continue;

                    } else if (FH2.getText().toString().equals("")) {

                        FH2.setText(lessons.get(i).getHour());
                        FR2.setText(lessons.get(i).getRoom());

                        continue;
                    }


                    break Switch;
            }
        }
        
        try {
            JSONArray jsonArray = new JSONArray(getIntent().getStringExtra("participants"));
            for (int i=0; i < jsonArray.length(); i++) {
                participants.add(new User(jsonArray.getJSONObject(i)));
                String username = jsonArray.getJSONObject(i).getString("firstName") + " " + jsonArray.getJSONObject(i).getString("lastName");
                usernames.add(new Simple(username));
                Log.i("usernames", usernames.toString());
            }
        } catch (Exception e) {
            Log.e("error parsing participants", e.toString());
        }

        adapter = new SimpleAdapter(listview.getContext(), usernames);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();




        Löschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(CoursesDetail.this)
                        .setTitle("Kurs löschen")
                        .setMessage("Sie können dies nicht widerrufen.")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // Delete course
                            }
                        })

                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoursesDetail.this, Home.class));
            }
        });
    }
}