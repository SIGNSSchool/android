package com.signs.signsschool.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.signs.signsschool.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AnhaengeAbgegebeneAufgabe extends AppCompatActivity {

    Button Fertig;
    ImageView imageView;
    TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anhaenge_abgegebene_aufgabe);

        Fertig = (Button)findViewById(R.id.FertigButtonAAA);
        imageView = (ImageView)findViewById(R.id.AufgabeIAAA);
        Name = (TextView)findViewById(R.id.NameTVAAA);



        Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AnhaengeAbgegebeneAufgabe.this, LAufgabeEinsichtAufgabe.class);
                startActivity(intent);


            }
        });

    }

    private class GETImage extends AsyncTask<Void, Void, Bitmap> {

        String name;

        public GETImage(String name) {
            this.name = name;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {

            String url = "";

            try {
                URLConnection connection = new URL(url).openConnection();
                connection.setConnectTimeout(1000 * 30);
                connection.setReadTimeout(1000 * 30);

                return BitmapFactory.decodeStream((InputStream)connection.getContent(), null, null);

            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (bitmap != null) {

                imageView.setImageBitmap(bitmap);
            }
        }
    }

}