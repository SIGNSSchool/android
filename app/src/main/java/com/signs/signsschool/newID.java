package com.signs.signsschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.utils.URIBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class newID extends AppCompatActivity {

    TextView nameLabel, birthDateLabel, validLabel, schoolLabel;
    ImageView userImageView;
    URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_id);

        Button close = findViewById(R.id.newIDcloseButton);
        nameLabel = findViewById(R.id.newIDnameLabel);
        birthDateLabel = findViewById(R.id.newIDbirthDateLabel);
        validLabel = findViewById(R.id.newIDvalidLabel);
        schoolLabel = findViewById(R.id.newIDschoolLabel);
        userImageView = findViewById(R.id.newIDuserPhoto);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        nameLabel.setText(prefs.getString("username", ""));
        //birthDateLabel.setText(prefs.getString("dateOfBirth", ""));
        schoolLabel.setText(prefs.getString("schoolname", ""));

        Button verify = findViewById(R.id.newIDverifyButton);

        URIBuilder builder = null;
        try {
            builder = new URIBuilder("https://signs.school/verify");
            builder.addParameter("user", prefs.getString("userId", ""));
            builder.addParameter("school", prefs.getString("schoolId", ""));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        try {
            url = builder.build().toURL();
            Log.i("url in id", String.valueOf(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        //   Picasso.get().load(userphoto).into(userImageView);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(newID.this, Home.class));
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(newID.this, IDQrView.class);
                intent.putExtra("id_url", String.valueOf(url));
                startActivity(intent);

            }
        });

    }
}