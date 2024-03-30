package com.signs.signsschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.signs.signsschool.news.CreateNews;

public class custom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        Button back = findViewById(R.id.backButtonCustom);
        Button confirm = findViewById(R.id.confirmButtonCustom);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(custom.this, CreateNews.class);
                intent.putExtra("recurring", "no");
                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(custom.this, CreateNews.class);
                intent.putExtra("recurring", "yes");
                startActivity(intent);

            }
        });




    }
}