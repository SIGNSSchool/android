package com.signs.signsschool.signs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.signs.signsschool.R;
import com.signs.signsschool.models.Choice;
import com.signs.signsschool.models.User;

import java.util.ArrayList;

public class SignsEinsichtAnzeigeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_einsicht_anzeige_details);

        ListAdapterChoices adapter;
        TextView Name = findViewById(R.id.nameTVSEAD);
        Button Fertig = findViewById(R.id.FertigButtonSEAD);
        ListView listView = findViewById(R.id.listViewChoices);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        Gson gson = new Gson();
        User user = gson.fromJson(preferences.getString("sender", ""), User.class);

        String senderName = user.getFirstName() + " " + user.getLastName();
        Name.setText(senderName);


        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(gson.fromJson(preferences.getString("choices", ""), Choice.class));


        adapter = new ListAdapterChoices(listView.getContext(), choices);
        listView.setAdapter(adapter);


        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignsEinsichtAnzeigeDetails.this, SignsEinsicht.class));
            }
        });
    }
}