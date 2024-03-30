package com.signs.signsschool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.absences.Absences;
import com.signs.signsschool.absences.Attendance;
import com.signs.signsschool.absences.CallInSick;
import com.signs.signsschool.absences.FehlstundenS;
import com.signs.signsschool.absences.RecentAbsences;
import com.signs.signsschool.assignments.AufgabenBewertungenS;
import com.signs.signsschool.assignments.AufgabenEvaluations;
import com.signs.signsschool.assignments.ErstellteAufgaben;
import com.signs.signsschool.assignments.Learning;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.models.Course;
import com.signs.signsschool.news.CreateNews;
import com.signs.signsschool.news.CreatedNews;
import com.signs.signsschool.news.NewsSubmissions;
import com.signs.signsschool.signs.Signs;
import com.signs.signsschool.signs.SignsEinsicht;
import com.signs.signsschool.signs.SignsErstellen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Menu extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String sender, value;

    Integer intvalue;
    Spinner spinner;

    View spinnerView;
    ArrayList<String> subjects = new ArrayList<>();
    RequestQueue requestQueue;

    Button firstButton, secondButton, thirdButton, fourfthButton, homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intentget = getIntent();

        Common.getCoursesByTeacherId(this);
        ArrayList<Course> courses = new Common().courses;
        ArrayList<String> coursenames = new ArrayList<>();

        Log.i("courses here", courses.toString());

        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i));
            coursenames.add(courses.get(i).getCourse());
        }

        Log.i("coursenames", coursenames.toString());

        ArrayAdapter gradeAdapter = ArrayAdapter.createFromResource(this, R.array.Grade, android.R.layout.simple_spinner_item);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sender = intentget.getStringExtra("sendervalueMenu");
        spinner = findViewById(R.id.spinnerMenu);
        spinnerView = findViewById(R.id.view15);

        firstButton = findViewById(R.id.firstButtonMenu);
        secondButton = findViewById(R.id.secondButtonMenu);
        thirdButton = findViewById(R.id.thirdButtonMenu);
        fourfthButton = findViewById(R.id.fourfthButtonMenu);
        homeButton = findViewById(R.id.homeButtonMenu);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, Home.class));
            }
        });

        switch (sender) {

            case "news_school":
                spinnerView.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                firstButton.setText("erstellen");
                secondButton.setText("einsicht");
                thirdButton.setText("erstellte");
                fourfthButton.setText("DRAGS (coming soon)");

                secondButton.setVisibility(View.VISIBLE);
                secondButton.setEnabled(true);

                thirdButton.setVisibility(View.VISIBLE);
                thirdButton.setEnabled(true);
                fourfthButton.setVisibility(View.GONE);
                fourfthButton.setEnabled(false);

                ArrayAdapter<CharSequence> adapter_n = ArrayAdapter.createFromResource(this, R.array.Grade, android.R.layout.simple_spinner_item);
                adapter_n.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter_n);
                spinner.setOnItemSelectedListener(this);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                        value = spinner.getSelectedItem().toString();
                        System.out.println(value);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                break;
            case "entschuldigen_student":

                spinnerView.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                firstButton.setText("entschuldigen");
                secondButton.setText("fehlstunden");


                secondButton.setVisibility(View.VISIBLE);
                secondButton.setEnabled(true);

                thirdButton.setVisibility(View.GONE);
                thirdButton.setEnabled(false);
                fourfthButton.setVisibility(View.GONE);
                fourfthButton.setEnabled(false);


                break;
            case "entschuldigen_teacher":

                spinnerView.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                firstButton.setText("neuste");
                secondButton.setText("einsicht");
                thirdButton.setText("anwesenheit");
                fourfthButton.setText("entschuldigen");

                secondButton.setVisibility(View.VISIBLE);
                secondButton.setEnabled(true);

                thirdButton.setVisibility(View.GONE);
                thirdButton.setEnabled(false);

                fourfthButton.setVisibility(View.GONE);
                fourfthButton.setEnabled(false);


                spinner.setAdapter(gradeAdapter);
                spinner.setOnItemSelectedListener(this);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                        value = spinner.getSelectedItem().toString();
                        System.out.println(value);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                break;

            case "entschuldigen_school":

                spinnerView.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                firstButton.setText("fehlstunden");

                secondButton.setVisibility(View.GONE);
                secondButton.setEnabled(false);

                thirdButton.setVisibility(View.GONE);
                thirdButton.setEnabled(false);

                fourfthButton.setVisibility(View.GONE);
                fourfthButton.setEnabled(false);


                break;
            case "aufgaben_student":

                spinnerView.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                firstButton.setText("unterricht");
                secondButton.setText("bewertungen");

                secondButton.setVisibility(View.VISIBLE);
                secondButton.setEnabled(true);

                thirdButton.setVisibility(View.GONE);
                thirdButton.setEnabled(false);

                fourfthButton.setVisibility(View.GONE);
                fourfthButton.setEnabled(false);


                break;


            case "marks_student":

                spinner.setAdapter(gradeAdapter);
                spinner.setOnItemSelectedListener(this);

                spinnerView.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                firstButton.setText("anstehend");

                secondButton.setVisibility(View.GONE);
                secondButton.setEnabled(false);

                thirdButton.setVisibility(View.GONE);
                thirdButton.setEnabled(false);

                fourfthButton.setVisibility(View.GONE);
                fourfthButton.setEnabled(false);


                break;
            case "aufgaben_teacher":

                spinnerView.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                firstButton.setText("erstellen");
                secondButton.setText("bewertungen");

                secondButton.setVisibility(View.VISIBLE);
                secondButton.setEnabled(true);

                thirdButton.setVisibility(View.GONE);
                thirdButton.setEnabled(false);

                fourfthButton.setVisibility(View.GONE);
                fourfthButton.setEnabled(false);

                ArrayAdapter<String> adapter_a =
                        new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, coursenames);
                adapter_a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter_a);
                spinner.setOnItemSelectedListener(this);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        value = courses.get(i).getCourseId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("response", response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject oneObject = jsonArray.getJSONObject(i);

                                String subject = oneObject.getString("Subject");
                                Integer status = oneObject.getInt("Status");

                                if (status == 0) {
                                    subjects.add(subject);
                                }

                                adapter_a.notifyDataSetChanged();
                            }
                        } catch (Exception e) {

                            Log.e("error", String.valueOf(e));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(request);

                break;
            case "signs":

                spinnerView.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                firstButton.setText("senden");
                secondButton.setText("eingang");
                thirdButton.setText("einsicht");

                secondButton.setVisibility(View.VISIBLE);
                secondButton.setEnabled(true);

                thirdButton.setVisibility(View.VISIBLE);
                thirdButton.setEnabled(true);

                fourfthButton.setVisibility(View.GONE);
                fourfthButton.setEnabled(false);


                break;

            case "kurse":

                spinnerView.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                firstButton.setText("kurse");

                secondButton.setVisibility(View.GONE);
                secondButton.setEnabled(false);

                thirdButton.setVisibility(View.GONE);
                thirdButton.setEnabled(false);

                fourfthButton.setVisibility(View.GONE);
                fourfthButton.setEnabled(false);


                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Grade, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(this);


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        value = spinner.getSelectedItem().toString();
                        System.out.println(value);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                break;

        }

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (sender) {

                    case "news_school":


                        Intent intent_n = new Intent(Menu.this, CreateNews.class);
                        intent_n.putExtra("grade", value);
                        startActivity(intent_n);

                        break;
                    case "entschuldigen_student":

                        startActivity(new Intent(Menu.this, TokenK.class));


                        break;
                    case "entschuldigen_teacher":

                        System.out.println(value);

                        Intent intent_e = new Intent(Menu.this, RecentAbsences.class);
                        intent_e.putExtra("Grade", value);
                        startActivity(intent_e);


                        break;
                    case "entschuldigen_school":

                        startActivity(new Intent(Menu.this, Absences.class));
                        break;
                    case "aufgaben_teacher":


                        Intent intent_a = new Intent(Menu.this, TokenAE.class);
                        intent_a.putExtra("courseId", value);
                        startActivity(intent_a);


                        break;
                    case "aufgaben_student":

                        startActivity(new Intent(Menu.this, Learning.class));


                        break;
                    case "signs":

                        startActivity(new Intent(Menu.this, SignsErstellen.class));


                        break;

                    case "kurse":

                        Intent intent_k = new Intent(Menu.this, Courses.class);
                        intent_k.putExtra("Grade", value);
                        startActivity(intent_k);


                        break;

                    case "marks_student":

                        switch (spinner.getSelectedItem().toString()) {

                            case "Fünfter":
                                value = "5";
                                break;

                            case "Sechster":
                                value = "6";
                                break;

                            case "Siebter":
                                value = "7";
                                break;

                            case "Achter":
                                value = "8";
                                break;

                            case "Neunter":
                                value = "9";
                                break;

                            case "Zehnter":
                                value = "10";
                                break;

                            case "Einführungsphase":
                                value = "11";
                                break;

                            case "Qualifikationsphase 1":
                                value = "12";
                                break;


                            case "Qualifikationsphase 2":
                                value = "13";
                                break;

                        }

                        Intent intent = new Intent(Menu.this, Upcoming.class);
                        intent.putExtra("grade", value);
                        startActivity(intent);

                        break;


                }


            }

        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (sender) {
                    case "news_school":
                        switch (spinner.getSelectedItem().toString()) {

                            case "Fünfter":
                                value = "5";
                                break;

                            case "Sechster":
                                value = "6";
                                break;

                            case "Siebter":
                                value = "7";
                                break;

                            case "Achter":
                                value = "8";
                                break;

                            case "Neunter":
                                value = "9";
                                break;

                            case "Zehnter":
                                value = "10";
                                break;

                            case "Einführungsphase":
                                value = "11";
                                break;

                            case "Qualifikationsphase 1":
                                value = "12";
                                break;

                            case "Qualifikationsphase 2":
                                value = "13";
                                break;
                        }

                        Intent intent_n = new Intent(Menu.this, NewsSubmissions.class);
                        intent_n.putExtra("Grade", value);
                        startActivity(intent_n);

                        break;
                    case "entschuldigen_student":
                        startActivity(new Intent(Menu.this, FehlstundenS.class));

                        break;
                    case "entschuldigen_teacher":

                        System.out.println(value);


                        Intent intent_k = new Intent(Menu.this, Absences.class);
                        intent_k.putExtra("Grade", value);
                        startActivity(intent_k);

                        break;

                    case "aufgaben_teacher":

                        Intent intent_a = new Intent(Menu.this, AufgabenEvaluations.class);
                        intent_a.putExtra("courseId", value);
                        startActivity(intent_a);


                        break;
                    case "aufgaben_student":
                        startActivity(new Intent(Menu.this, AufgabenBewertungenS.class));


                        break;
                    case "signs":
                        startActivity(new Intent(Menu.this, Signs.class));

                        break;


                }


            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (sender) {

                    case "entschuldigen_teacher":

                        startActivity(new Intent(Menu.this, Attendance.class));

                        break;
                    case "news_school":

                        switch (spinner.getSelectedItem().toString()) {

                            case "Fünfter":
                                value = "5";
                                break;

                            case "Sechster":
                                value = "6";
                                break;

                            case "Siebter":
                                value = "7";
                                break;

                            case "Achter":
                                value = "8";
                                break;

                            case "Neunter":
                                value = "9";
                                break;

                            case "Zehnter":
                                value = "10";
                                break;

                            case "Einführungsphase":
                                value = "11";
                                break;

                            case "Qualifikationsphase 1":
                                value = "12";
                                break;


                            case "Qualifikationsphase 2":
                                value = "13";
                                break;

                        }


                        Intent intent_n = new Intent(Menu.this, CreatedNews.class);
                        intent_n.putExtra("Grade", value);
                        startActivity(intent_n);

                        break;

                    case "signs":

                        startActivity(new Intent(Menu.this, SignsEinsicht.class));

                        break;

                    case "aufgaben_teacher":

                        Intent intent_a = new Intent(Menu.this, ErstellteAufgaben.class);
                        intent_a.putExtra("courseId", value);
                        startActivity(intent_a);
                        break;
                }

            }
        });

        fourfthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Objects.equals(sender, "aufgaben_teacher")) {

                    Intent intent = new Intent(Menu.this, AufgabenEvaluations.class);
                    intent.putExtra("subject_aufgaben_teacher", value);
                    startActivity(intent);


                } else if (Objects.equals(sender, "entschuldigen_teacher")) {

                    startActivity(new Intent(Menu.this, CallInSick.class));
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (sender) {

            case "aufgaben_teacher":

                value = adapterView.getSelectedItem().toString();

                break;
            case "kurse":
            case "news_school":

                System.out.println(adapterView.getSelectedItem().toString());

                switch (adapterView.getSelectedItem().toString()) {

                    case "Fünfter":

                        intvalue = 5;
                        break;
                    case "Sechster":

                        intvalue = 6;
                        break;
                    case "Siebter":

                        intvalue = 7;
                        break;
                    case "Achter":

                        intvalue = 8;
                        break;
                    case "Neunter":

                        intvalue = 9;
                        break;
                    case "Zehnter":

                        intvalue = 10;
                        break;
                    case "EF":

                        intvalue = 11;
                        break;
                    case "Q1":

                        intvalue = 12;
                        break;
                    case "Q2":

                        intvalue = 13;
                        break;


                }
                break;


        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}