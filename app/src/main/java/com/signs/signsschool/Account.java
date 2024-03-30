package com.signs.signsschool;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Account extends AppCompatActivity {
    TextView nameTV, birthDateTV, phoneNumberTV, mailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Button changeButton = findViewById(R.id.changeAccountButton);
        Button logOut = findViewById(R.id.logOutButton);
        Button stats = findViewById(R.id.statsButtonAccount);

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Account.this, Stats.class));
            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(Account.this)
                        .setTitle("Möchtest du dich ausloggen?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                editor.putString("check", "BAD");
                                editor.commit();

                                startActivity(new Intent(Account.this, Login.class));
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.signs.school/Settings.html")));
            }
        });


        nameTV = findViewById(R.id.nameTVAccount);
        birthDateTV = findViewById(R.id.birthDateTVAccount);
        phoneNumberTV = findViewById(R.id.phoneNumberTVAccount);
        mailTV = findViewById(R.id.mailTVAccount);


        mailTV.setVisibility(View.GONE);

        nameTV.setText(prefs.getString("username", "error"));
        //birthDateTV.setText(prefs.getString("dateOfBirth", "error"));
        phoneNumberTV.setText(prefs.getString("schoolname", "error"));


        Button backButton = findViewById(R.id.backButtonAccount);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Account.this, Home.class));
            }
        });

        Button licenses = findViewById(R.id.showLicensesButton);

        licenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Account.this, Licenses.class));
            }
        });
    }
}