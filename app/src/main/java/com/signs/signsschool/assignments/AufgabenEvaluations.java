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
import com.signs.signsschool.SubmissionAdapter;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;
import com.signs.signsschool.signs.Submission;
import com.signs.signsschool.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AufgabenEvaluations extends AppCompatActivity {

    ListView listview;
    Button Fertig;
    SubmissionAdapter adapter;
    EditText searchView;
    List<Submission> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laufgabe_bewertungen);


        listview = (ListView) findViewById(R.id.ListViewLAufgabeBewertungen);
        Fertig = (Button) findViewById(R.id.FertigButtonLAufgabeBewertungen);
        // searchView = (EditText)findViewById(R.id.searchViewLAufgabeBewertungen);

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AufgabenEvaluations.this, Menu.class);
                intent.putExtra("sendervalueMenu", "aufgaben_teacher");
                startActivity(intent);
            }
        });

        String courseId = getIntent().getStringExtra("courseId");

        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/assignments/submissions/"+courseId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("evaluations", response);

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject submission = jsonArray.getJSONObject(i);

                        list.add(new Submission(submission.getString("assignmentId"), submission.getString("course"), submission.getString("userId"), submission.getString("updatedAt"), submission.getString("submission"), submission.getString("courseId"), submission.getString("createdAt"), submission.getString("teacherId"), submission.getString("pk"), submission.getString("evaluation"), new User(submission.getJSONObject("user"))));
                    }

                    adapter = new SubmissionAdapter(listview.getContext(), list);
                    listview.setAdapter(adapter);

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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Submission model = list.get(i);

                Intent intent = new Intent(AufgabenEvaluations.this, AufgabenEvaluationsDetail.class);
                String name = model.getUser().getFirstName() + " " + model.getUser().getLastName();

                intent.putExtra("name", name);
                intent.putExtra("createdAt", model.getCreatedAt().substring(0,10));
                intent.putExtra("submission", model.getSubmission());
                intent.putExtra("evaluation", model.getEvaluation());

                startActivity(intent);
            }
        });
    }
}
