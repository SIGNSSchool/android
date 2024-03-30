package com.signs.signsschool.signs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;
import com.signs.signsschool.SignsService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Signs extends AppCompatActivity {

    ListView listview;

    ListAdapterSigns adapter;
    Button Fertig;
    ArrayList<SignsModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs);

        listview = findViewById(R.id.listview);
        Fertig = findViewById(R.id.button113);

        SignsService.getSigns(this);
        list = SignsService.signs;
        adapter = new ListAdapterSigns(listview.getContext(), list);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signs.this, Menu.class);
                intent.putExtra("sendervalueMenu", "signs");
                startActivity(intent);
            }
        });


        /*inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                Log.i("Searching for", String.valueOf(s));
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SignsModel model = list.get(i);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Signs.this);
                SharedPreferences.Editor editor = prefs.edit();

                Set<String> questions = new HashSet<>();
                questions.addAll(model.getQuestions());

                Gson gson = new Gson();
                String limitedQuestion = gson.toJson(model.getLimitedQuestion());

                editor.putString("signs_name", model.getSender().getFirstName());
                editor.putString("signs_subject", model.getSubject());
                editor.putString("signs_createdAt", model.getCreatedAt());
                editor.putString("signs_message", model.getMessage());
                editor.putStringSet("signs_questions", questions);
                editor.putString("signs_limitedQuestion", limitedQuestion);
                editor.apply();


                startActivity(new Intent(Signs.this, SignsEingangDetail.class));
            }
        });
    }
}