package com.signs.signsschool.news;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.signs.signsschool.common.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.R;
import com.signs.signsschool.common.Simple;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsDetail extends AppCompatActivity {

    TextView Title, Date, Description;
    Button Abbrechen, Bestätigen;

    ArrayList<String> choices;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();

        Bestätigen = findViewById(R.id.button46);
        Abbrechen = findViewById(R.id.button47);
        Date = findViewById(R.id.DateTVNEWS);
        Title = findViewById(R.id.TitleTVNEWS);
        Description = findViewById(R.id.DescriptionTVNEWS);
        ListView listView = findViewById(R.id.listViewnewsOptions);

        Title.setText(intent.getStringExtra("title"));
        Date.setText(intent.getStringExtra("date"));
        Description.setText(intent.getStringExtra("description"));


        Abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsDetail.this, News.class));
            }
        });


        Bundle args = intent.getBundleExtra("options");
        ArrayList<Object> array = (ArrayList<Object>) args.getSerializable("ARRAYLIST");
        List<Simple> list = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            list.add(new Simple((String) array.get(i)));
        }

        adapter = new SimpleAdapter(listView.getContext(), list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Bestätigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();

                jsonArray.put(adapter.choices);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                try {
                    jsonObject.put("newsId", getIntent().getStringExtra("pk"));
                    jsonObject.put("title", Title.getText().toString());
                    jsonObject.put("description", Description.getText().toString());
                    jsonObject.put("courseId", "");
                    jsonObject.put("grade", "");
                    jsonObject.put("submitBy", Date.getText().toString());
                    jsonObject.put("schoolId", prefs.getString("schoolId", ""));
                    jsonObject.put("choices", jsonArray);

                } catch (JSONException e) {
                    Log.e("error parsing", e.toString());
                }

                NewsService.createNewsSubmission(jsonObject, getApplicationContext());
            }
        });
    }
}