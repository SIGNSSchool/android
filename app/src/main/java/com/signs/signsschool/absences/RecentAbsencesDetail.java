package com.signs.signsschool.absences;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.R;
import com.squareup.picasso.Picasso;

public class RecentAbsencesDetail extends AppCompatActivity {


    Button Fertig, Entschuldigen, NichtEntschuldigen;
    TextView Name, Date, Reason, Test;
    ImageView imageView;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktuelle_fehlstunden_ldetail);

        Intent intent = getIntent();

        Fertig = findViewById(R.id.button45);
        Entschuldigen = findViewById(R.id.button49);
        NichtEntschuldigen = findViewById(R.id.button71);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Name = findViewById(R.id.textView43);
        Date = findViewById(R.id.textView102);
        Reason = findViewById(R.id.textView105);
        imageView = findViewById(R.id.AnhängeAbgegebeneAufgabeImage);
        Test = findViewById(R.id.TestTVAktuelleFehlstundenLDetail);


        String image = intent.getStringExtra("image");

        Picasso.get().load("https://signs.school/" + image).into(imageView);

        Name.setText(intent.getStringExtra("name"));
        Date.setText("Gesendet am: "+intent.getStringExtra("createdAt"));
        Reason.setText(intent.getStringExtra("test"));
        Test.setText(intent.getStringExtra("reason"));


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecentAbsencesDetail.this, RecentAbsences.class);
                startActivity(intent);

            }
        });

        Entschuldigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(RecentAbsencesDetail.this)
                        .setTitle("Schüler entschuldigen")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                updateAbsence(true);
                            }
                        })

                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        NichtEntschuldigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(RecentAbsencesDetail.this)
                        .setTitle("Schüler nicht entschuldigen")


                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                updateAbsence(false);

                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }


    public void updateAbsence(Boolean status) {
        StringRequest request = new StringRequest(Request.Method.PUT, Common.getApiUrl() + "absences?userId=" + Common.getAccountParamByKey("userId", this) + "&status=" + status + "&createdAt=" + getIntent().getStringExtra("createdAt"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("update absence", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }
}