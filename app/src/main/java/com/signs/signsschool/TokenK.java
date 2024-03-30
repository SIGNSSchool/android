package com.signs.signsschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TokenK extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_k);


        Button continueButton = findViewById(R.id.continueTokenK);
        Button backButton = findViewById(R.id.backTokenK);

        TextView token = findViewById(R.id.tokenTVK);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        token.setText(prefs.getString("user_id", "error"));


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://signs.school/ImageUploadK.html")));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TokenK.this, Menu.class);
                intent.putExtra("sendervalueMenu", "entschuldigen_student");
                startActivity(intent);

            }
        });
    }
}