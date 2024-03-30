package com.signs.signsschool.assignments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.R;
import com.signs.signsschool.Tag;

import java.util.Calendar;

public class AufgabeBearbeiten extends AppCompatActivity {

    int LOAD_IMAGE = 1;

    EditText Beschreibung;
    TextView SubmissionDate, Date, Subject;
    Button Bestätigen, ChooseFile, Abbrechen;
    ImageView imageView;
    RequestQueue requestQueue;
    String STORE_URL = "http://box2463.temp.domains/~signssch/StoreAufgabeBearbeiten.php";
    String finalDate, finalSubject, finalSubmissionDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aufgabe_bearbeiten);

        Intent intent = getIntent();

        java.util.Date current = Calendar.getInstance().getTime();

        Beschreibung = (EditText) findViewById(R.id.Edittext1);
        SubmissionDate = (TextView) findViewById(R.id.textView45);
        Date = (TextView) findViewById(R.id.textView20);
        Subject = (TextView) findViewById(R.id.textView18);
        Bestätigen = (Button) findViewById(R.id.button24);
        Abbrechen = (Button)findViewById(R.id.button18);
        ChooseFile = (Button)findViewById(R.id.button25);
        //imageView = (ImageView)findViewById(R.id.);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        finalDate = intent.getStringExtra("Date");
        finalSubject = intent.getStringExtra("Subject");
        finalSubmissionDate = intent.getStringExtra("Date");




        if (Date != null && Subject != null && SubmissionDate != null) {

            Date.setText(finalDate);
            Subject.setText(finalSubject);
            SubmissionDate.setText(finalSubmissionDate);

        }

        Beschreibung.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {

                    hideKeyboard(view);
                }
            }
        });





        Abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AufgabeBearbeiten.this, Tag.class);
                startActivity(intent);

            }
        });


        ChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);
            }
        });

        }





        public void onClick(View view) {



        switch (view.getId()) {
            case R.id.imageView:
                Intent galPicker = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               // startActivity(galPicker, LOAD_IMAGE);
                 break;

            //case R.id.ChooseFile:
            //    Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            //    new Upload(image, Date.getText().toString(), Subject.getText().toString(), Beschreibung.getText().toString()).execute();

            //    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == LOAD_IMAGE && requestCode == RESULT_OK && data != null ) {

            Uri selected = data.getData();
            imageView.setImageURI(selected);



        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}