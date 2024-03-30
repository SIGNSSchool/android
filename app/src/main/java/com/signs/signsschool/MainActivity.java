package com.signs.signsschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.absences.RecentAbsences;
import com.signs.signsschool.absences.FehlstundenS;
import com.signs.signsschool.absences.krankmeldenlaview;
import com.signs.signsschool.assignments.AufgabenEinsicht;
import com.signs.signsschool.assignments.Learning;
import com.signs.signsschool.news.ModelNews;
import com.signs.signsschool.news.NewsHomeAdapter;
import com.signs.signsschool.news.News;
import com.signs.signsschool.news.CreateNews;
import com.signs.signsschool.signs.Signs;
import com.signs.signsschool.signs.SignsErstellen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;



public class MainActivity extends AppCompatActivity {

    Button IDButton, Button1, Button2, Button3, Button4, roomButton;
    Button Button5;
    String accountstatus, TYPE;
    TextView greeting;
    RecyclerView recyclerView;
    NewsHomeAdapter adapter;
    String token;
    RequestQueue requestQueue;

    MyFirebaseMessagingService firebaseMessagingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        System.out.println("trying to get device token...");



        requestQueue = Volley.newRequestQueue(getApplicationContext());

                token = preferences.getString("token", "no_token");
                Log.i("token", token);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://signs.school/tokens.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("token response", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("token error", error.toString());
                    }
                }) {

                    public Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("type", "2");
                        params.put("dt", token);
                        params.put("os", "Android");
                        params.put("uid", preferences.getString("user_id", "0"));
                        return params;
                    }


                };
                requestQueue.add(stringRequest);



        IDButton =  findViewById(R.id.imageButton4);
        Button2 =  findViewById(R.id.imageButton2);
        Button3 =  findViewById(R.id.imageButton6);
        Button1 =  findViewById(R.id.imageButton);
        Button4 =  findViewById(R.id.imageButton5);
        Button5 =  findViewById(R.id.button69);
        greeting = findViewById(R.id.greetingHome);


        roomButton = findViewById(R.id.roomButtonHome);

        roomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Rooms.class));
            }
        });
        IDButton.setOnClickListener(v -> {
            System.out.println("id clicked");
            Intent intent = new Intent(MainActivity.this, newID.class);
            intent.putExtra("id", preferences.getString("user_id", "error"));
            startActivity(intent);
        });


        recyclerView = findViewById(R.id.newsGridHome);

        String url = "https://signs.school/GetNEWS.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("response", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");


                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);


                    ArrayList<ModelNews> list = new ArrayList<>();

                    for (int i=0; i < jArray.length(); i++) {

                        JSONObject oneObject = jArray.getJSONObject(i);

                        String title = oneObject.getString("Title");
                        String date = oneObject.getString("Date");
                        Integer id = oneObject.getInt("message_id");
                        String description = oneObject.getString("Description");
                        JSONArray optionArray = oneObject.getJSONArray("options");

                       // list.add(new ModelNews(title, date, description, id, optionArray));
                    }
                    adapter = new NewsHomeAdapter(recyclerView.getContext(), list);
                    recyclerView.setAdapter(adapter);

                } catch (Exception e) {

                    Log.i("error", String.valueOf(e));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }}) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usermail", prefs.getString("user_id", "0"));
                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

        String check = preferences.getString("check", "error");

        if (!Objects.equals(check, "GOOD")) {

            startActivity(new Intent(MainActivity.this, Login.class));
        } else if (Objects.equals(check, "BAD")) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }


        Button settingsButton = findViewById(R.id.settingsButtonHome);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Account.class);
                startActivity(intent);

            }
        });



            Intent intentTYPE = new Intent(MainActivity.this, SignsErstellen.class);
            intentTYPE.putExtra("TYPE", TYPE);




            String name = preferences.getString("username", "");
            String[] splited = name.split("\\s+");

            greeting.setTextSize(20);
            greeting.setText("Drücken Sie auf Account, \n um das Profil zu aktivieren");

            accountstatus = preferences.getString("accountstatus", "error");
            TYPE = preferences.getString("TYPE", "error");


                Log.i("accHome", accountstatus);

                    if (Objects.equals(accountstatus, "blocked")) {

                        // Make "Your account has been blocked" -message visible
                        Toast.makeText(MainActivity.this, "Ihr Account wurde gesperrt", Toast.LENGTH_LONG).show();


                        IDButton.setEnabled(false);
                        IDButton.setVisibility(View.GONE);

                        Button1.setEnabled(false);
                        Button1.setVisibility(View.GONE);

                        Button2.setEnabled(false);
                        Button2.setVisibility(View.GONE);

                        Button3.setEnabled(false);
                        Button3.setVisibility(View.GONE);

                        Button4.setEnabled(false);
                        Button4.setVisibility(View.GONE);

                        Button5.setEnabled(false);
                        Button5.setVisibility(View.GONE);

                        roomButton.setEnabled(false);
                        roomButton.setVisibility(View.GONE);

                    } else if (Objects.equals(accountstatus, "id")) {

                        greeting.setTextSize(40);
                        greeting.setText("hallo, "+splited[0].toLowerCase(Locale.ROOT)+"!");

                        // special spec for customer inquiry
                        IDButton.setEnabled(true);
                        IDButton.setVisibility(View.VISIBLE);

                        Button1.setEnabled(false);
                        Button1.setVisibility(View.GONE);

                        Button2.setEnabled(false);
                        Button2.setVisibility(View.GONE);

                        Button3.setEnabled(false);
                        Button3.setVisibility(View.GONE);

                        Button4.setEnabled(false);
                        Button4.setVisibility(View.GONE);

                        Button5.setEnabled(false);
                        Button5.setVisibility(View.GONE);

                        roomButton.setEnabled(false);
                        roomButton.setVisibility(View.GONE);

                    } else if (Objects.equals(accountstatus, "free")) {

                        greeting.setTextSize(40);
                        greeting.setText("hallo, "+splited[0].toLowerCase(Locale.ROOT)+"!");

                        switch (preferences.getString("TYPE", "errror")) {

                            case "Student":

                                roomButton.setEnabled(false);
                                roomButton.setVisibility(View.GONE);

                                IDButton.setEnabled(true);
                                IDButton.setVisibility(View.VISIBLE);

                                // set text for buttons

                                Button1.setText("hours");
                                Button2.setText("aufgaben");
                                Button3.setText("signs");
                                Button4.setText("news");
                                Button5.setText("entschuldigen");


                                Button1.setEnabled(true);
                                Button2.setEnabled(true);
                                Button3.setEnabled(true);
                                Button4.setEnabled(true);
                                Button5.setEnabled(true);


                                Button1.setVisibility(View.VISIBLE);
                                Button2.setVisibility(View.VISIBLE);
                                Button3.setVisibility(View.VISIBLE);
                                Button4.setVisibility(View.VISIBLE);
                                Button5.setVisibility(View.VISIBLE);



                                Button1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, hours.class);
                                        startActivity(intent);


                                    }
                                });
                                Button2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, Learning.class);
                                        startActivity(intent);

                                    }
                                });
                                Button3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, Signs.class);
                                        startActivity(intent);

                                    }
                                });


                                Button4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, News.class);
                                        startActivity(intent);

                                    }
                                });

                                Button5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Intent intent = new Intent(MainActivity.this, FehlstundenS.class);
                                        startActivity(intent);

                                    }
                                });

                                break;

                            case "Teacher":

                                IDButton.setEnabled(false);
                                IDButton.setVisibility(View.GONE);

                                Button1.setEnabled(true);
                                Button2.setEnabled(true);
                                Button3.setEnabled(true);
                                Button4.setEnabled(false);
                                Button5.setEnabled(true);
                                roomButton.setEnabled(true);


                                roomButton.setVisibility(View.VISIBLE);
                                Button1.setVisibility(View.VISIBLE);
                                Button2.setVisibility(View.VISIBLE);
                                Button3.setVisibility(View.VISIBLE);
                                Button4.setVisibility(View.GONE);
                                Button5.setVisibility(View.VISIBLE);

                                // set button texts

                                Button1.setText("hours");
                                Button2.setText("aufgaben");
                                Button3.setText("signs");
                                Button5.setText("entschuldigen");


                                Button1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, hours.class);
                                        startActivity(intent);
                                    }
                                });

                                Button2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, AufgabenEinsicht.class);
                                        intent.putExtra("Subject", "Mathematik");
                                        startActivity(intent);

                                    }
                                });

                                Button3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, Signs.class);
                                        startActivity(intent);

                                    }
                                });

                                Button5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, RecentAbsences.class);
                                        startActivity(intent);


                                    }
                                });

                                break;

                            case "Parent":

                                IDButton.setEnabled(false);
                                IDButton.setVisibility(View.GONE);
                                Button5.setEnabled(false);
                                Button5.setVisibility(View.GONE);


                                Button1.setEnabled(true);
                                Button2.setEnabled(false);
                                Button3.setEnabled(false);
                                Button4.setEnabled(false);
                                roomButton.setEnabled(false);


                                Button1.setVisibility(View.VISIBLE);
                                Button2.setVisibility(View.GONE);
                                Button3.setVisibility(View.GONE);
                                Button4.setVisibility(View.GONE);
                                roomButton.setVisibility(View.GONE);

                                // set button text

                                Button1.setText("signs");


                                Button1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, Signs.class);
                                        startActivity(intent);

                                    }
                                });


                                break;

                            case "School":

                                IDButton.setEnabled(false);
                                IDButton.setVisibility(View.GONE);


                                Button1.setEnabled(true);
                                Button2.setEnabled(true);
                                Button3.setEnabled(true);
                                Button4.setEnabled(true);
                                Button5.setEnabled(true);
                                roomButton.setEnabled(false);


                                Button1.setVisibility(View.VISIBLE);
                                Button2.setVisibility(View.VISIBLE);
                                Button3.setVisibility(View.VISIBLE);
                                Button4.setVisibility(View.VISIBLE);
                                Button5.setVisibility(View.VISIBLE);
                                roomButton.setVisibility(View.GONE);

                                // set button texts

                                Button1.setText("hours");
                                Button2.setText("kurse");
                                Button3.setText("signs");
                                Button4.setText("news");
                                Button5.setText("entschuldigen");


                                Button1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                        Intent intent = new Intent(MainActivity.this, hoursEdit.class);
                                        startActivity(intent);

                                    }
                                });


                                Button2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, Courses.class);
                                        intent.putExtra("Grade", "Fünfter");
                                        startActivity(intent);

                                    }
                                });


                                Button3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, Signs.class);
                                        startActivity(intent);

                                    }
                                });


                                Button4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, CreateNews.class);
                                        startActivity(intent);


                                    }
                                });

                                Button5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(MainActivity.this, krankmeldenlaview.class);
                                        startActivity(intent);


                                    }
                                });

                                break;
                        }
                    }
    }
}


