package com.signs.signsschool.absences;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;
import com.signs.signsschool.absences.models.ModelFehlstundenS;

import java.util.ArrayList;

public class FehlstundenS extends AppCompatActivity {

    ListView listview;
    FehlstundenSAdapter adapter;
    Button Fertig;
    ArrayList<ModelFehlstundenS> list = new ArrayList<>();
    AbsenceService absenceService = new AbsenceService();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fehlstunden_s);

        listview = findViewById(R.id.ListViewFehlstundenS);
        Fertig = findViewById(R.id.buttonFehlstundenS);

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FehlstundenS.this, Menu.class);
                intent.putExtra("sendervalueMenu", "entschuldigen_student");
                startActivity(intent);
            }
        });

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                list = absenceService.getList(response);
                adapter = new FehlstundenSAdapter(listview.getContext(), list);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };

        Volley.newRequestQueue(this).add(new AbsenceRequest(listener, this));
    }
}