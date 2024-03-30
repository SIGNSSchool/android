package com.signs.signsschool.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.Menu;
import com.signs.signsschool.R;

import java.util.ArrayList;

public class NewsSubmissions extends AppCompatActivity {

    ListView listView;
    Button Fertig;
    TextView NameTV, DateTV, TitleTV;
    NewsSubmissionsAdapter adapter;
    ArrayList<ModelNewsSubmission> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wahl_einsicht);

        listView = findViewById(R.id.ListViewWahlEinsicht);
        Fertig = findViewById(R.id.button116);
        //searchView = (EditText)findViewById(R.id.searchViewWahlEinsicht);

        DateTV = findViewById(R.id.DateTVWahlEinsicht);
        NameTV = findViewById(R.id.nameTVWahlEinsicht);
        TitleTV = findViewById(R.id.TitleTVWahlEinsicht);

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsSubmissions.this, Menu.class);
                intent.putExtra("sendervalueMenu", "news_school");
                startActivity(intent);
            }
        });


        NewsService.getNewsSubmissions(this);
        list = NewsService.newsSubmissions;

        adapter = new NewsSubmissionsAdapter(listView.getContext(), list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModelNewsSubmission model = list.get(i);

                String name = model.getUser().getFirstName() + " " + model.getUser().getLastName();

                Intent intent = new Intent(NewsSubmissions.this, NewsSubmissionsDetail.class);
                intent.putExtra("name", name);
                intent.putExtra("title", model.getTitle());
                intent.putExtra("submittedAt", model.getSubmittedAt());
                //intent.putExtra("options", );
                startActivity(intent);
            }
        });
    }
}