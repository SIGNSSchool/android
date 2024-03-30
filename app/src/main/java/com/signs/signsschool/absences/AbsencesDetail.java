package com.signs.signsschool.absences;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;
import com.squareup.picasso.Picasso;

public class AbsencesDetail extends AppCompatActivity {
    Button Fertig;
    TextView Name, Date,  Class, Reason, Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krankmelden_laeinsicht_tag);

        Fertig = (Button) findViewById(R.id.button72);
        ImageView Image = (ImageView) findViewById(R.id.imageKrankmeldenLAEinsicht);

        Name = (TextView) findViewById(R.id.textView109);
        Date = (TextView) findViewById(R.id.textView107);
        Class = (TextView) findViewById(R.id.textView108);
        Reason = (TextView) findViewById(R.id.textView110);
        Status = (TextView) findViewById(R.id.statusAktuelleFehlstundenLA);

        String timeFrame = "Von   " + getIntent().getStringExtra("fromDate") + "     bis  " + getIntent().getStringExtra("toDate");
        String isExcused = getIntent().getBooleanExtra("isExcused", false) ? "Entschuldigt" : "Nicht entschuldigt";

        Name.setText(getIntent().getStringExtra("name"));
        Date.setText(getIntent().getStringExtra("createdAt"));
        Class.setText(timeFrame);
        Reason.setText(getIntent().getStringExtra("reason"));
        Status.setText(isExcused);

        try {
            String imageurl = getIntent().getStringExtra("imageKrankmeldenLAEinsicht");
            Log.i("Image", imageurl);

            Picasso.get().load("https://signs.school/" + imageurl).into(Image);

        } catch (Exception e) {
            System.out.println("errro" + e);
        }

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AbsencesDetail.this, Absences.class);
                startActivity(intent);
            }
        });
    }
}
