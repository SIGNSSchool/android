package com.signs.signsschool.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

import java.util.ArrayList;

public class NewsSubmissionsDetail extends AppCompatActivity {

    Button Fertig;
    TextView Title, Date, Name, Class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wahl_einsicht_detail);

        Fertig = findViewById(R.id.button34);
        Title = findViewById(R.id.textView23);
        Date = findViewById(R.id.textView41);
        Name = findViewById(R.id.textView31);
        Class = findViewById(R.id.textView33);

        ListView listView = findViewById(R.id.listviewnewseinsichtoptions);

        Title.setText(getIntent().getStringExtra("title"));
        Date.setText(getIntent().getStringExtra("submittedAt"));
        Name.setText(getIntent().getStringExtra("name"));

        switch (getIntent().getStringExtra("grade")) {
            case "fifth":
                Class.setText("Fünfter Jahrgang");
                break;
            case "sixth":
                Class.setText("Sechster Jahrgang");
                break;
            case "seventh":
                Class.setText("Siebter Jahrgang");
                break;
            case "eighth":
                Class.setText("Achter Jahrgang");
                break;
            case "ninth":
                Class.setText("Neunter Jahrgang");
                break;
            case "tenth":
                Class.setText("Zehnter Jahrgang");
                break;
            case "EF":
                Class.setText("Einführungsphase");
                break;
            case "Q1":
                Class.setText("Qualifikationsphase 1");
                break;
            case "Q2":
                Class.setText("Qualifikationsphase 2");
                break;
        }

        Bundle args = getIntent().getBundleExtra("BUNDLE");
        ArrayList<String> array = (ArrayList<String>) args.getSerializable("ARRAYLIST");


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsSubmissionsDetail.this, NewsSubmissions.class));
            }
        });
    }
}