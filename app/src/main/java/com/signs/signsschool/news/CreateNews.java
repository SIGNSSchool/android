package com.signs.signsschool.news;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.Menu;
import com.signs.signsschool.R;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.common.Simple;
import com.signs.signsschool.common.SimpleAdapter;
import com.signs.signsschool.models.Course;
import com.signs.signsschool.temp_recurring;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateNews extends AppCompatActivity implements View.OnFocusChangeListener {
    EditText Title, Message, Date, Option;
    Button backButton, confirmButton;
    String courseId;
    RequestQueue requestQueue;
    JSONArray options = new JSONArray();
    String recurring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wahl_erstellen);

        Spinner courseSpinner = findViewById(R.id.gradeSpinnerNEWS);

        recurring = getIntent().getStringExtra("recurring");

        Common.getCoursesBySchoolId(this);
        ArrayList<Course> courses = Common.courses;
        ArrayList<String> coursenames = new ArrayList<>();

        for (int i = 0; i < courses.size(); i++) {
            coursenames.add(courses.get(i).getCourse());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coursenames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.notifyDataSetChanged();
        courseSpinner.setAdapter(adapter);

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseId = courseSpinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        confirmButton = (Button) findViewById(R.id.button38);
        backButton = (Button) findViewById(R.id.button39);
        Title = (EditText) findViewById(R.id.TitleNEWSErstellenTextField);
        Message = (EditText) findViewById(R.id.DescriptionNEWSErstellenTextField);
        Option = (EditText) findViewById(R.id.NEWSErstellenOption);

        ListView listview = findViewById(R.id.createnewslistview);

        Button add = findViewById(R.id.addButtonNEWSErstellen);

        List<Simple> list = new ArrayList<>();
        SimpleAdapter listadapter;

        listadapter = new SimpleAdapter(listview.getContext(), list);
        listview.setAdapter(listadapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                options.put(Option.getText().toString());

                list.add(new Simple((String) Option.getText().toString()));
                listadapter.notifyDataSetChanged();

                Option.getText().clear();
            }
        });

        Button recurringButton = findViewById(R.id.recurringButton);
        recurringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(CreateNews.this, temp_recurring.class));
            }
        });


        Button showDate = findViewById(R.id.showDatePicker);
        DatePicker datePicker = findViewById(R.id.datePickerNEWS);
        datePicker.setVisibility(View.GONE);

        Integer month = datePicker.getMonth();
        Integer day = datePicker.getDayOfMonth();
        Integer year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String Date = format.format(calendar.getTime());

        showDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (datePicker.getVisibility() == View.VISIBLE) {

                    datePicker.setVisibility(View.GONE);
                    showDate.setText("Abgabe auswählen");

                } else if (datePicker.getVisibility() == View.GONE) {

                    datePicker.setVisibility(View.VISIBLE);
                    showDate.setText("Datum verstecken");
                }

            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Title.setText(" ");
                Message.setText(" ");

                Intent intent = new Intent(CreateNews.this, Menu.class);
                intent.putExtra("sendervalueMenu", "news_school");
                startActivity(intent);
            }
        });


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestQueue = Volley.newRequestQueue(getApplicationContext());

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


                JSONObject jsonObject = new JSONObject();

                Boolean recurring = getIntent().getBooleanExtra("recurring", false);

                try {
                    jsonObject.put("title", Title.getText().toString());
                    jsonObject.put("description", Message.getText().toString());
                    jsonObject.put("submitBy", Date);
                    jsonObject.put("status", 1);
                    jsonObject.put("recurring", recurring);
                    jsonObject.put("courseId", courseId);
                    jsonObject.put("schoolId", prefs.getString("schoolId", "0"));
                    jsonObject.put("grade", getIntent().getStringExtra("grade"));
                    jsonObject.put("options", options);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                NewsService.createNews(jsonObject, getApplicationContext());
            }
        });
    }

    @Override
    public void onFocusChange(View view, boolean b) {

        if (!b) {
            hideKeyboard(view);
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}