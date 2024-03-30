package com.signs.signsschool.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.signs.signsschool.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AufgabenBewertungenS extends AppCompatActivity {

    Button Fertig;
    ListView listView;
    EditText searchView;
    ListAdapterAufgabenBewertungenS adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aufgaben_bewertungen_s);

        // searchView = findViewById(R.id.searchBarEvaluationS);
        Fertig = findViewById(R.id.evaluationSButton);
        listView = findViewById(R.id.listviewEvaluationS);
        List<ModelAufgabenBewertungenS> list = new ArrayList<>();

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AufgabenBewertungenS.this, Menu.class);
                intent.putExtra("sendervalueMenu", "aufgaben_student");
                startActivity(intent);
            }
        });

        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/assignments/submissions/" + Common.getAccountParamByKey("userId", this), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("evaluations", response);

                try {

                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject evaluation = jsonArray.getJSONObject(i);

                        list.add(new ModelAufgabenBewertungenS(evaluation.getString("assignmentId"), evaluation.getString("course"), evaluation.getString("userId"), evaluation.getString("updatedAt"), evaluation.getString("submission"), evaluation.getString("courseId"), evaluation.getString("createdAt"), evaluation.getString("teacherId"), evaluation.getString("pk"), evaluation.getString("evaluation"), new User(evaluation.getJSONObject("user"))));
                    }

                    adapter = new ListAdapterAufgabenBewertungenS(listView.getContext(), list);
                    listView.setAdapter(adapter);

                } catch (Exception e) {
                    Log.e("error", String.valueOf(e));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ModelAufgabenBewertungenS model = list.get(i);

                Intent intent = new Intent(AufgabenBewertungenS.this, AufgabenBewertungSDetail.class);
                intent.putExtra("course", model.getCourse());
                intent.putExtra("createdAt", model.getCreatedAt());
                intent.putExtra("submission", model.getSubmission());
                intent.putExtra("evaluation", model.getEvaluation());

                startActivity(intent);
            }
        });
    }
}