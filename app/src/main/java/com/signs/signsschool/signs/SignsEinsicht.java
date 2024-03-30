package com.signs.signsschool.signs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;
import com.signs.signsschool.models.Choice;
import com.signs.signsschool.models.LimitedChoice;
import com.signs.signsschool.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignsEinsicht extends AppCompatActivity {

    Button Fertig;
    ListView listview;
    TextView FullName, Subject, Date;
    EditText searchView;
    ListAdapterSIGNSEinsicht adapter;
    ArrayList<ModelSIGNSEinsicht> arrayList = new ArrayList<>();
    ArrayList<ModelSIGNSEinsicht> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_einsicht);


        Fertig = findViewById(R.id.button115);
        FullName = findViewById(R.id.nameTVSIGNSE);
        Subject = (TextView) findViewById(R.id.SubjectTVSIGNSE);
        Date = (TextView) findViewById(R.id.DateTVSIGNSE);
        listview = (ListView) findViewById(R.id.ListViewSIGNSEinsicht);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //searchView = (EditText)findViewById(R.id.searchBarSIGNSEinsicht);


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignsEinsicht.this, Menu.class);
                intent.putExtra("sendervalueMenu", "signs");
                startActivity(intent);
            }
        });

        /*searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                adapter.getFilter().filter(cs.toString());
                adapter.notifyDataSetChanged();
                Log.i("searching for", cs.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/


        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/signs/submissions/" + preferences.getString("userId", ""), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("signs submissions", response);

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject sentSign = jsonArray.getJSONObject(i);

                        LimitedChoice limitedChoice = new LimitedChoice(sentSign.getJSONObject("limitedQuestion").getString("question"), sentSign.getJSONObject("limitedQuestion").getString("choice"));

                        ArrayList<Choice> choices = new ArrayList<>();

                        for (int j = 0; j < sentSign.getJSONArray("choices").length(); j++) {
                            choices.add(new Choice(sentSign.getJSONArray("choices").getJSONObject(j).getString("question"), sentSign.getJSONArray("choices").getJSONObject(j).getString("answer")));
                        }

                        list.add(new ModelSIGNSEinsicht(new User(sentSign.getJSONObject("sender")), sentSign.getString("subject"), sentSign.getString("createdAt"), sentSign.getString("message"), sentSign.getBoolean("isConfirmed"), choices, limitedChoice));
                    }

                    adapter = new ListAdapterSIGNSEinsicht(listview.getContext(), list);
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

                ModelSIGNSEinsicht model = list.get(i);

                Gson gson = new Gson();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("sender", gson.toJson(model.getSender()));
                editor.putString("subject", model.getSubject());
                editor.putString("createdAt", model.getCreatedAt());
                editor.putString("message", model.getMessage());
                editor.putString("limitedChoice",  gson.toJson(model.getLimitedChoice()));
                editor.putString("choices", gson.toJson(model.getChoices()));
                editor.apply();

                startActivity(new Intent(SignsEinsicht.this, SignsEinsichtAnzeige.class));
            }
        });
    }
}
