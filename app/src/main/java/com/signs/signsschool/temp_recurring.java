package com.signs.signsschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.signs.signsschool.news.CreateNews;

public class temp_recurring extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_recurring);

        Button yes = findViewById(R.id.continueButton_temp_recurring);
        Button no = findViewById(R.id.backButton_temp_recurring);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(temp_recurring.this, CreateNews.class);
                intent.putExtra("recurring", "yes");
                startActivity(intent);

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(temp_recurring.this, CreateNews.class);
                intent.putExtra("recurring", "no");
                startActivity(intent);
            }
        });
    }
}