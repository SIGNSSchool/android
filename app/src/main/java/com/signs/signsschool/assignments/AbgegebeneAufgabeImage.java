package com.signs.signsschool.assignments;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;
import com.squareup.picasso.Picasso;

public class AbgegebeneAufgabeImage extends AppCompatActivity {

    ImageView imageView;
    Button Fertig;
    TextView name;
    String Subject, Date, schoolID, SubmissionDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abgegebene_aufgabe_image);

        imageView = (ImageView) findViewById(R.id.imageAbgegebenAufgabeImage);
        Fertig = (Button) findViewById(R.id.FertigButtonAAI);
        name = (TextView) findViewById(R.id.NameTVAAI);
        Intent intent = getIntent();

        name.setText(intent.getStringExtra("nameAbgegebeneAufgabeImage"));


        Picasso.get().load("https://signs.school/"+intent.getStringExtra("imageAbgegebeneAufgabeImage")).into(imageView);


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AbgegebeneAufgabeImage.this, Tag.class);
                startActivity(intent);


            }
        });

    }

}