package com.signs.signsschool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class KursAnzeigeDeleteStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurs_anzeige_delete_student);

        Button Delete = findViewById(R.id.DeleteButtonKursAnzeigeDelete);
        Button Back = findViewById(R.id.FertigButtonKursAnzeigeDelete);

        TextView Name = findViewById(R.id.nameKursAnzeigeDelete);

        String name = getIntent().getStringExtra("nameKursAnzeigeDel");
        String user_id = getIntent().getStringExtra("user_idKursAnzeigeDel");

        Name.setText(name);



        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KursAnzeigeDeleteStudent.this, Courses.class);
                startActivity(intent);

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest request = new StringRequest(Request.Method.POST, "https://signs.school/DeleteStudentKurse.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response", response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("username", name);
                        parameters.put("user_id", user_id);
                        parameters.put("course_id", String.valueOf(8));

                        return parameters;
                    }
                };
                requestQueue.add(request);

                Intent intent = new Intent(KursAnzeigeDeleteStudent.this, Courses.class);
                startActivity(intent);

            }
        });


    }
}