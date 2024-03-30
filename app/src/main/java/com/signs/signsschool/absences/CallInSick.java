package com.signs.signsschool.absences;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class CallInSick extends AppCompatActivity {

    private static final int LOAD_IMAGE = 1;

    EditText NumberOfDays, Reason, Date;
    Button Bestätigen, UploadDocument, Abbrechen;
    Spinner Stunde, Test;
    File file;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krankmelden_s);

        NumberOfDays = (EditText) findViewById(R.id.NumberOfDaysEditTextKrankmeldenS);
        Reason = (EditText) findViewById(R.id.ReasonEditTextKrankmeldenS);
        Date = (EditText)findViewById(R.id.DateEditTextKrankmeldenS);
        Bestätigen = (Button) findViewById(R.id.button61);
        UploadDocument = (Button) findViewById(R.id.button60);
        Abbrechen = (Button)findViewById(R.id.button62);
        imageView = (ImageView)findViewById(R.id.PreviewimageViewKrankmelden);
        Stunde = (Spinner)findViewById(R.id.StundenSpinnerKrankmeldenS);
        Test = (Spinner)findViewById(R.id.TESTSpinnerKrankmeldenS);


        NumberOfDays.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b) {

                    hideKeyboard(view);
                }

            }
        });
        Reason.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b) {
                    hideKeyboard(view);
                }
            }
        });

        Date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b) {
                    hideKeyboard(view);
                }
            }
        });


        ArrayAdapter stundenAdapter = ArrayAdapter.createFromResource(this, R.array.Stunden, android.R.layout.simple_spinner_item);
        stundenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Stunde.setAdapter(stundenAdapter);

        ArrayAdapter testAdapter = ArrayAdapter.createFromResource(this, R.array.test, android.R.layout.simple_spinner_item);
        testAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Test.setAdapter(testAdapter);

        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();


        Abbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Reason.getText().toString().equals("");
                Date.getText().toString().equals("");
                NumberOfDays.getText().toString().equals("");

                startActivity(new Intent(CallInSick.this, Menu.class));

            }
        });

        UploadDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);



            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        Bestätigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadImage(bitmap);


            }
        });







    }



    private void uploadImage(Bitmap imageBitmap){

        Bitmap bitmap= BitmapFactory.decodeFile(String.valueOf(imageView.getDrawable()));
        imageView.setImageBitmap(bitmap);


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] b = stream.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        ArrayList<Pair<String, String>> params = new ArrayList<Pair<String, String>>();
        params.add(new Pair<>("image", encodedImage));

        try {
            new AsyncUploader().execute("https://signs.school/KrankTest.php", getQuery(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String getQuery(List<Pair<String, String>> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for(Pair<String, String> pair : params){
            if(first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.first, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.second, "UTF-8"));
        }
        return result.toString();
    }

    private class AsyncUploader extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            String urlString = strings[0];
            String params = strings[1];
            URL url = null;
            InputStream stream = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);

                urlConnection.connect();

                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(params);
                wr.flush();

                stream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), 8);
                String result = reader.readLine();
                return result;
            }catch (IOException ioe){
                ioe.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return null;
        }

        @Override
        protected  void onPostExecute(String result) {
             }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




}


