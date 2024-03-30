package com.signs.signsschool.assignments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.R;

import java.util.HashMap;
import java.util.Map;

public class ErstellteAufgabenAnzeige extends AppCompatActivity {

    Button Löschen, Abbrechen;
    TextView Title, Date, Description;
    RequestQueue requestQueue;
    Integer message_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstellte_aufgaben_anzeige);

        Intent getIntent = getIntent();

        Löschen = findViewById(R.id.DeleteButtonEAA);
        Abbrechen = findViewById(R.id.AbbrechenButtonEAA);

        Title = findViewById(R.id.ATTVEAAn);
        Date = findViewById(R.id.DateTVEAAn);
        Description = findViewById(R.id.DescriptionTVEAAn);


        Title.setText(getIntent.getStringExtra("title"));
        Date.setText(getIntent.getStringExtra("date"));
        Description.setText(getIntent.getStringExtra("submission"));



        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ErstellteAufgabenAnzeige.this, ErstellteAufgaben.class));
            }
        });

        /*Löschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ErstellteAufgabenAnzeige.this, AufgabenUebersicht.class);

                Log.i("MessageID", String.valueOf(message_id));

                new AlertDialog.Builder(ErstellteAufgabenAnzeige.this)
                        .setTitle("Aufgabe löschen")
                        .setMessage("Sie können dies nicht widerrufen.")


                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                StringRequest request = new StringRequest(Request.Method.DELETE, DELETE_URL, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        System.out.println(response);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        Log.i("volleyerror", String.valueOf(error));
                                    }
                                }) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {

                                        Map<String, String> parameters = new HashMap<String, String>();
                                        parameters.put("message_id", String.valueOf(message_id));


                                        return parameters;
                                    }
                                };
                                Volley.newRequestQueue(ErstellteAufgabenAnzeige.this).add(request);
                                startActivity(intent);

                            }
                        })

                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });*/
    }
}