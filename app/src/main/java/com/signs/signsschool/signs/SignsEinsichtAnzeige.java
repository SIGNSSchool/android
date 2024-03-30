package com.signs.signsschool.signs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.signs.signsschool.R;
import com.signs.signsschool.models.User;

public class SignsEinsichtAnzeige extends AppCompatActivity {

    Button Fertig, Details;
    TextView Message, Subject, Date, Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_einsicht_anzeige);

        Fertig = findViewById(R.id.FertigButtonSEA);
        Details = findViewById(R.id.detailsButtonSEA);
        Name = findViewById(R.id.NameTVSEA);
        Subject = findViewById(R.id.SubjectTVSEA);
        Date = findViewById(R.id.DateTVSEA);
        Message = findViewById(R.id.MessageTVSEA);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Gson gson = new Gson();
        User user = gson.fromJson(preferences.getString("sender", ""), User.class);

        String senderName = user.getFirstName() + " " + user.getLastName();

        Name.setText(senderName);
        Subject.setText(preferences.getString("subject", ""));
        Date.setText(preferences.getString("createdAt", ""));
        Message.setText(preferences.getString("message", ""));


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignsEinsichtAnzeige.this, SignsEinsicht.class));
            }
        });

        Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignsEinsichtAnzeige.this, SignsEinsichtAnzeigeDetails.class));
            }
        });
    }
}

