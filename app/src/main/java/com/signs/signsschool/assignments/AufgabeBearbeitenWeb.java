package com.signs.signsschool.assignments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class AufgabeBearbeitenWeb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aufgabe_bearbeiten_web);


        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://signs.school/ImageUploadAB.html"));
        startActivity(browserIntent);

    }
}