package com.signs.signsschool.news;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.signs.signsschool.R;

import java.util.ArrayList;

public class CreatedNewsDetail extends AppCompatActivity {

    Button Abbrechen, Löschen;
    TextView Title, Date, Description;

    RequestQueue requestQueue;
    String finalDate, finalClass, finalTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstellte_newsanzeige);

        Title = findViewById(R.id.AufgabentitelENAn);
        Date = findViewById(R.id.DateTVENAn);
        Description = findViewById(R.id.DescriptionTVNEAn);

        ListView listview = findViewById(R.id.listviewnewserstellteoptions);

        Title.setText(getIntent().getStringExtra("title"));
        Date.setText(getIntent().getStringExtra("createdAt"));
        Description.setText(getIntent().getStringExtra("description"));


        Abbrechen = findViewById(R.id.AbbrechenButtonENAn);
        Löschen = findViewById(R.id.DeleteButtonENAn);

        Bundle args = getIntent().getBundleExtra("BUNDLE");
        ArrayList<String> array = (ArrayList<String>) args.getSerializable("ARRAYLIST");


        Abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreatedNewsDetail.this, CreatedNews.class));
            }
        });

        Löschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(CreatedNewsDetail.this)
                        .setTitle("Wahl löschen")
                        .setMessage("Sie können dies nicht widerrufen.")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // TODO delete action --> integrate erstellte news into news and display delete button for school accounts
                                startActivity(new Intent(CreatedNewsDetail.this, CreatedNews.class));
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}