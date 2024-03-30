package com.signs.signsschool;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class hoursEditDetail extends AppCompatActivity {

    String cancellledStatus = "NO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_edit_detail);

        Button Back = findViewById(R.id.backButtonhoursEdit);
        Button Continue = findViewById(R.id.continuehoursEdit);

        TextView hour = findViewById(R.id.hourTVhoursEdit);
        TextView date = findViewById(R.id.dateTVhoursEdit);

        EditText teacher = findViewById(R.id.teacherEditTexthoursEdit);
        EditText text = findViewById(R.id.textEditTexthoursEdit);
        EditText room = findViewById(R.id.roomEditTexthoursEdit);


        teacher.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }
            }
        });
        text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }
            }
        });
        room.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }
            }
        });


        Intent intent = getIntent();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


       // teacher.setText(prefs.getString("namehoursEdit", "N/A"));


        String id = prefs.getString("idhoursEdit", "Lehrer");


        String hourInt = prefs.getString("hourHoursEdit", "");
        Integer hour_id = prefs.getInt("hour_idHoursEdit", 0);




        Log.i("teacher", String.valueOf(id));
        Log.i("hourD", String.valueOf(hourInt));

        date.setText(prefs.getString("dateHoursEdit", ""));

        switch (hourInt) {


            case "1":

                hour.setText("erste Stunde");
                break;
            case "2":

                hour.setText("zweite Stunde");
                break;
            case "3":

                hour.setText("dritte Stunde");
                break;
            case "4":
                hour.setText("vierte Stunde");
                break;

            case "5":

                hour.setText("fünfte Stunde");
                break;

            case "6":
                hour.setText("sechste Stunde");
                break;


            case "7":
                hour.setText("siebte Stunde");
                break;

            case "8":
                hour.setText("achte Stunde");
                break;

            case "9":
                hour.setText("neunte Stunde");
                break;

            case "10":
            hour.setText("zehnte Stunde");
            break;

        }

        Switch cancelledSwitch = findViewById(R.id.checkboxhoursEditDetail);
        TextView cancelled = findViewById(R.id.cancelledTVhoursEdit);


        cancelledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (cancelledSwitch.isChecked()) {

                    cancelledSwitch.setChecked(true);
                    cancelled.setText("Stunde entfällt");
                    cancellledStatus = "YES";

                } else {

                    cancelled.setText("Stunde entfäll nicht");
                    cancelledSwitch.setChecked(false);
                    cancellledStatus = "NO";

                }
            }
        });






        teacher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                startActivity(new Intent(hoursEditDetail.this, hoursEditRecipient.class));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(hoursEditDetail.this, Home.class));
            }
        });



        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://signs.school/Updatehours.php";


                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("response", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    public Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<>();
                        params.put("hour_id", String.valueOf(hour_id));
                        params.put("teacher", String.valueOf(id));
                        params.put("room", room.getText().toString());
                        params.put("cancelled", cancellledStatus);
                        params.put("text", text.getText().toString());
                        params.put("date", date.getText().toString());


                        return params;
                    }

                };
                Volley.newRequestQueue(hoursEditDetail.this).add(request);

            }
        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}