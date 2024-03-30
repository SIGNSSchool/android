package com.signs.signsschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Id extends AppCompatActivity {
    TextView FirstName, DateOfBirth, School, IsARegistredStudentOf;
    ImageView UserPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);


        UserPhoto = (ImageView) findViewById(R.id.imageView7);
        FirstName = (TextView) findViewById(R.id.textView14);
        DateOfBirth = (TextView) findViewById(R.id.textView15);
        School = (TextView) findViewById(R.id.textView10);
        IsARegistredStudentOf = (TextView) findViewById(R.id.textView4);

        Button button = (Button) findViewById(R.id.button14);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Id.this, Home.class));
            }
        });
    }
}
