package com.signs.signsschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.assignments.AbgegebeneAufgabeImage;
import com.signs.signsschool.assignments.Fach;

public class Tag extends AppCompatActivity {


    Button Fertig, AufgabeBearbeiten, AnhangÖffnen;
    TextView Title, SubmissionDate, Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);


        Fertig = (Button)findViewById(R.id.FertigButtonTag);
        AufgabeBearbeiten = (Button)findViewById(R.id.AufgabeBearbeitenbutton);
        Title  = findViewById(R.id.TitleTVTag);
        SubmissionDate = (TextView)findViewById(R.id.SubmissionDateTVTag);
        Description = (TextView)findViewById(R.id.DescriptionTVTag);

        AnhangÖffnen = (Button)findViewById(R.id.AnhangöffnenTag);

        Intent intent = getIntent();

        Title.setText(intent.getStringExtra("titleFA"));
        SubmissionDate.setText(intent.getStringExtra("dateFA"));
        Description.setText(intent.getStringExtra("descriptionFA"));


        String image = intent.getStringExtra("imageFA");


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Tag.this, Fach.class);
                startActivity(intent);

            }
        });


        AnhangÖffnen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Tag.this, AbgegebeneAufgabeImage.class);
                intent.putExtra("imageAbgegebeneAufgabeImage", image);
                startActivity(intent);


            }
        });

        AufgabeBearbeiten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent(Tag.this, AufgabeBearbeitenWeb.class);
                intent.putExtra("SubmissionDate", SubmissionDate.getText().toString());
                intent.putExtra("Title", Title.getText().toString());
                startActivity(intent);*/

                startActivity(new Intent(Tag.this, TokenAB.class));

            }
        });


    }



}