package com.signs.signsschool.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.signs.signsschool.Menu;
import com.signs.signsschool.R;

import java.util.ArrayList;


public class CreatedNews extends AppCompatActivity {

    ListView listView;
    Button Fertig;
    ArrayList<ModelNews> list = new ArrayList<>();
    CreatedNewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstellte_news);

        Fertig = findViewById(R.id.FertigButtonErstellteNEWS);
        listView = findViewById(R.id.ListViewErstellteNEWS);

        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatedNews.this, Menu.class);
                intent.putExtra("sendervalueMenu", "news_school");
                startActivity(intent);
            }
        });

        NewsService.getNews(this);
        list = NewsService.news;
        adapter = new CreatedNewsAdapter(listView.getContext(), list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ModelNews model = list.get(i);

                Intent intent = new Intent(CreatedNews.this, CreatedNewsDetail.class);
                intent.putExtra("title", model.getTitle());
                intent.putExtra("createdAt", model.getCreatedAt());
                intent.putExtra("description", model.getDescription());
                startActivity(intent);
            }
        });
    }
}

