package com.signs.signsschool;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.common.Config;


public class Login extends AppCompatActivity {

    EditText PasswordEditText, EmailEditText;
    Button Bestätigen, Abbrechen, AccountErstellen;
    String result;
    RequestQueue requestQueue;
    Config config = new Config();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Handler mainHandler = new Handler(this.getMainLooper());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();

        TextView errordisplay = findViewById(R.id.errordisplay);

        EmailEditText = (EditText) findViewById(R.id.EMailEditTextLogin);
        PasswordEditText = (EditText) findViewById(R.id.PasswordEditTextLogin);
        Bestätigen = (Button) findViewById(R.id.button15);
        AccountErstellen = (Button) findViewById(R.id.button4);


        PasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }
            }
        });
        EmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        AccountErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://signs.school/createAccount.html")));
            }
        });


        Bestätigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EmailEditText.getText().toString().matches("") || PasswordEditText.getText().toString().matches("")) {
                    errordisplay.setText("Bitte füllen Sie beide Felder aus");
                    return;
                }

                UserService.getUser(EmailEditText.getText().toString(), getApplicationContext());
                UserService.getSchool(EmailEditText.getText().toString(), getApplicationContext());
                startActivity(new Intent(Login.this, Home.class));
        }
    });
}

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
