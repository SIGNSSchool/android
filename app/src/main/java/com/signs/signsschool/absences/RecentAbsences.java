package com.signs.signsschool.absences;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;
import com.signs.signsschool.absences.models.Absence;
import com.signs.signsschool.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RecentAbsences extends AppCompatActivity {

    ListView listview;
    RecentAbsencesAdapter adapter;
    Button Fertig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktuelle_fehlstunden_l);


        listview = findViewById(R.id.ListViewAktuelleFehlstundenL);
        Fertig = findViewById(R.id.button112);
        //EditText searchView =  findViewById(R.id.searchViewAktuelleFehlstundenL);

        List<Absence> list = new ArrayList<>();

      /* searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                Log.i("Searching for", String.valueOf(s));
                adapter.getFilter().filter(s.toString());
                adapter.notifyDataSetChanged();

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecentAbsences.this, Menu.class);
                intent.putExtra("sendervalueMenu", "entschuldigen_teacher");
                startActivity(intent);
            }
        });

        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/absences/teachers/" + Common.getAccountParamByKey("userId", this), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("recent absences", response);

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject absence = jsonArray.getJSONObject(i);

                        list.add(new Absence(absence.getString("fromDate"), absence.getString("toDate"), absence.getString("createdAt").substring(0,10), absence.getString("updatedAt"), absence.getString("userId"), absence.getString("grade"), absence.getString("reason"), absence.getString("pk"), absence.getBoolean("isExcused"), new User(absence.getJSONObject("user"))));
                    }

                    adapter = new RecentAbsencesAdapter(listview.getContext(), list);
                    listview.setAdapter(adapter);

                } catch (Exception e) {

                    Log.i("error", String.valueOf(e));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Absence model = list.get(i);

                Intent intent = new Intent(RecentAbsences.this, RecentAbsencesDetail.class);

                String name = model.getUser().getFirstName() + " " + model.getUser().getLastName();

                intent.putExtra("name", name);
                intent.putExtra("fromDate", model.getFromDate());
                intent.putExtra("toDate", model.getToDate());
                intent.putExtra("createdAt", model.getCreatedAt());
                intent.putExtra("updatedAt", model.getUpdatedAt());
                intent.putExtra("reason", model.getReason());
                intent.putExtra("isExcused", model.getExcused());
                intent.putExtra("pk", model.getPk());

                startActivity(intent);
            }
        });
    }
}