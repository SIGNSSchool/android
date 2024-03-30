package com.signs.signsschool.assignments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;

public class AufgabeErstellenWeb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aufgabe_erstellen_web);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://signs.school/ImageUploadAE.html"));
        startActivity(browserIntent);


    }
}