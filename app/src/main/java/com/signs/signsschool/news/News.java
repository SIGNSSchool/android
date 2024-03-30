package com.signs.signsschool.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.Home;
import com.signs.signsschool.R;

import java.io.Serializable;
import java.util.ArrayList;

public class News extends AppCompatActivity {

    ListView listView;
    Button Fertig;
    EditText searchview;
    NewsAdapter adapter;
    ArrayList<ModelNews> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsuebersicht);

        Fertig = findViewById(R.id.button141);
        listView = findViewById(R.id.ListViewNEWSÜbersicht);
        // searchview = (EditText)findViewById(R.id.searchBarNewsUebersicht);


        NewsService.getNews(getApplicationContext());
        list = NewsService.news;

        adapter = new NewsAdapter(listView.getContext(), list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(News.this, Home.class));
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModelNews model = list.get(i);


                Intent intent = new Intent(News.this, NewsDetail.class);
                intent.putExtra("pk", model.getPk());
                intent.putExtra("title", model.getTitle());
                intent.putExtra("createdAt", model.getCreatedAt());
                intent.putExtra("description", model.getDescription());

                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) model.getOptions());
                intent.putExtra("optionsNews", args);
                startActivity(intent);
            }
        });
    }
}