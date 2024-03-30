package com.signs.signsschool.absences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class AttestAnsicht extends AppCompatActivity {

    Button Fertig;
    TextView Attest;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attest_ansicht);

        Fertig = findViewById(R.id.FertigButtonKAA);
        Attest = findViewById(R.id.NameTVKAA);
        imageView = findViewById(R.id.AufgabeIKAA);


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AttestAnsicht.this, CallInSick.class);
                startActivity(intent);
            }
        });
    }
}