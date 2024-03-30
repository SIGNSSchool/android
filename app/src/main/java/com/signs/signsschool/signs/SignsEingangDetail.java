package com.signs.signsschool.signs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class SignsEingangDetail extends AppCompatActivity {

    Button Confirm, Fertig;
    TextView Name, Subject, Message, Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_eingang_detail);

        Confirm = (Button) findViewById(R.id.button74);
        Fertig = (Button) findViewById(R.id.button75);
        Name = (TextView) findViewById(R.id.textView61);
        Subject = (TextView) findViewById(R.id.SubjectTVSignsEingangDetail);
        Message = (TextView) findViewById(R.id.textView106);
        Date = (TextView) findViewById(R.id.DateTVSignsEingangDetail);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Name.setText(preferences.getString("signs_name", ""));
        Subject.setText(preferences.getString("signs_subject", ""));
        Message.setText(preferences.getString("signs_message", ""));
        Date.setText(preferences.getString("signs_createdAt", ""));


        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignsEingangDetail.this, SignBestaetigen.class));
            }
        });

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignsEingangDetail.this, Signs.class));
            }
        });
    }
}
