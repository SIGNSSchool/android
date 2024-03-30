package com.signs.signsschool.absences;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.common.Common;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;
import com.signs.signsschool.absences.models.Absence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Absences extends AppCompatActivity {
//
    Button Fertig;
    ListView listView;
    AbsencesAdapter adapter;
    List<Absence> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fehlstunden_leinsicht);

        listView = (ListView) findViewById(R.id.ListViewFehlstundenEinsicht);
        Fertig = (Button) findViewById(R.id.FertigButtonFehlstundenEinsicht);

        // EditText searchView = findViewById(R.id.searchViewFehlstundenEinsicht);

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Absences.this, Menu.class);
                intent.putExtra("sendervalueMenu", "entschuldigen_school");
                startActivity(intent);
            }
        });

        String account = Common.getAccountParamByKey("account", this);

        if (Objects.equals(account, "School")) {

            AbsenceService.getAbsencesBySchoolId(this);
        } else if (Objects.equals(account, "Teacher")) {

            AbsenceService.getAbsencesByTeacherId(this);
        }

        list = AbsenceService.absences;
        adapter = new AbsencesAdapter(listView.getContext(), list);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Absence model = list.get(i);

                Intent intent = new Intent(Absences.this, AbsencesDetail.class);

                intent.putExtra("name", model.getUser().getFirstName() + " " + model.getUser().getLastName());
                intent.putExtra("isExcused", model.getExcused());
                intent.putExtra("reason", model.getReason());
                intent.putExtra("createdAt", model.getCreatedAt());
                //intent.putExtra("imageKrankmeldenLAEinsicht", model.getImage());
                intent.putExtra("fromDate", model.getFromDate());
                intent.putExtra("toDate", model.getToDate());
                startActivity(intent);
            }
        });
    }
}