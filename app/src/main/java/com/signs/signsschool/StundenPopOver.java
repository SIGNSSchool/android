package com.signs.signsschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StundenPopOver extends AppCompatActivity {

    TextView Subject, Day, Time, Teacher, room, Aufgaben, Date;
    Button Fertig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stunden_pop_over);


        Fertig = (Button)findViewById(R.id.FertigButtonStundenPopOver);

        Intent intentget = getIntent();

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(StundenPopOver.this, hours.class));
            }
        });

        Subject = (TextView)findViewById(R.id.hoursDetailSubjectTextView);
        Date = findViewById(R.id.hoursDetailDateTextView);
        Day = (TextView)findViewById(R.id.hoursDetailDayTextView);
        Teacher = (TextView)findViewById(R.id.hoursDetailTeacherTextView);
        room = (TextView)findViewById(R.id.hoursDetailRoomTextView);
        Aufgaben = (TextView)findViewById(R.id.hoursDetailTaskTextView);
        Time = (TextView)findViewById(R.id.textView57);


        room.setText(intentget.getStringExtra("room"));
        Subject.setText(intentget.getStringExtra("subject"));
        Teacher.setText(intentget.getStringExtra("teacher"));
        Date.setText(intentget.getStringExtra("date"));


        switch (intentget.getStringExtra("Day")) {

            case "M":
                Day.setText("am Montag");
                break;
            case "T":
                Day.setText("am Dienstag");
                break;
            case "W":
                Day.setText("am Mittwoch");
                break;
            case "Th":
                Day.setText("am Donnerstag");
                break;
            case "F":
                Day.setText("am Freitag");
                break;


        }

        switch(intentget.getIntExtra("Hour", 1)) {

            case 1:

                Time.setText("8:00 - 8:45");
                break;
            case 2:

                Time.setText("8:50 - 9:35");
                break;

            case 3:

                Time.setText("9:55 - 10:40");
                break;

            case 4:

                Time.setText("10:45 - 11:30");
                break;

            case 5:

                Time.setText("11:45 - 12:30");
                break;

            case 6:

                Time.setText("12:35 - 13:20");
                break;

            case 7:

                Time.setText("13:35 - 14:20");
                break;

            case 8:

                Time.setText("14:25 - 15:10");
                break;

            case 9:

                Time.setText("15:25 - 16:10");
                break;

            case 10:

                Time.setText("16:15 - 17:00");

        }
    }
}