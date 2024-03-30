package com.signs.signsschool.signs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class SignsErstellen extends AppCompatActivity {

    EditText Recipient, Subject, Message;
    String STORE_URL = "https://signs.school/StoreSignsAntwort.php", Sender, account;
    String GETDATA_URL = "https://signs.school/GetClassesSignsAntwort.php";
    Button Send, Abbrechen, Details;
    Spinner Class;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_erstellen);

        Intent intentget = getIntent();

        Array arrayList = null;

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arrayList, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Class.setAdapter(adapter);

        Send = findViewById(R.id.button41);
        Abbrechen = findViewById(R.id.button42);
        Details = findViewById(R.id.DetailsButtonSignsErstellen);
        Recipient = findViewById(R.id.RecipientEditText);
        Subject = findViewById(R.id.SubjectEditTextSignErstellen);
        Message = findViewById(R.id.MessageEditTextSignErstellen);
        requestQueue = Volley.newRequestQueue(getApplicationContext());


        Recipient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }
            }
        });

        Subject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }
            }
        });

        Message.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }
            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        account = prefs.getString("account", "");

        Recipient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                startActivity(new Intent(SignsErstellen.this, SIGNSErstellenRecipient.class));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        if (Objects.equals(account, "Teacher") || Objects.equals(account, "School")) {

            Details.setVisibility(View.VISIBLE);
            Details.setEnabled(true);
        } else {

            Details.setVisibility(View.GONE);
            Details.setEnabled(false);
        }

        if (!intentget.getStringExtra("name").isEmpty()) {
            //Recipient.setText(Editable.Factory.getInstance().newEditable(intentget.getStringExtra("name")));
        }

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendData();
            }
        });

        Abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignsErstellen.this, Menu.class);
                intent.putExtra("sendervalueMenu", "signs");
                startActivity(intent);
            }
        });

        Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignsErstellen.this, SignsErstellenDetails.class);
                startActivity(intent);
            }
        });
    }


    private void sendData() {

        StringRequest request = new StringRequest(Request.Method.POST, STORE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    //Toast.makeText(SignsErstellen.this, "success", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //  Toast.makeText(SignsErstellen.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                Gson gson = new Gson();
                params.put("sender", gson.toJson(preferences.getStringSet("user", new HashSet<>())));
                params.put("recipient", "");
                params.put("subject", Subject.getText().toString());
                params.put("message", Message.getText().toString());
                params.put("submitBy", "");
                params.put("schoolId", preferences.getString("schoolId", ""));
                params.put("courseId", "");


                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        requestQueue.add(request);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}