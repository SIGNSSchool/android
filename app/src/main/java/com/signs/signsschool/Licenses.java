package com.signs.signsschool;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Licenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licenses);
        
        Button Volley = findViewById(R.id.VolleyButton);
        Button Picasso = findViewById(R.id.picassoButton);
        Button JodaTime = findViewById(R.id.jodaTimeButton);

        Volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/google/volley/blob/master/LICENSE")));
            }
        });

        Picasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/square/picasso/blob/master/LICENSE.txt")));
            }
        });

        JodaTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.joda.org/joda-time/licenses.html")));
            }
        });
    }
}