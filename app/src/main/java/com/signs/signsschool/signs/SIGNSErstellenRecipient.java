package com.signs.signsschool.signs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.RecipientAdapter;
import com.signs.signsschool.R;
import com.signs.signsschool.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SIGNSErstellenRecipient extends AppCompatActivity {

    Integer status = 0;
    String url;
    ListView listView;
    RecipientAdapter adapter;
    String name, grade, uid;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signserstellen_recipient);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        listView = (ListView) findViewById(R.id.listViewRecipient);

        if (status == 0) {

            url = Common.getApiUrl() + "/users/" + preferences.getString("schoolId", "");//"https://signs.school/GetUsersSIGNSAndroidAPI.php";
        } else if (status == 1) {

            url = Common.getApiUrl() + "/users/courses/" + preferences.getString("schoolId", "");//https://signs.school/GetKurseSIGNSAndroidAPI.php";
        }


        Button changeRecipient = findViewById(R.id.buttonrecipient);

        changeRecipient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (changeRecipient.getText().toString().equals("An Klasse senden")) {

                    status = 1;
                    changeRecipient.setText("An Person senden");
                    System.out.println("class clicked");

                } else if (changeRecipient.getText().toString().equals("An Person senden")) {

                    status = 0;
                    changeRecipient.setText("An Klasse senden");
                    System.out.println("person clicked");
                }


            }
        });


        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> coursenames = new ArrayList<>();
        ArrayList<String> classids = new ArrayList<>();


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("recipients", response);

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    List<User> list = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject recipient = jsonArray.getJSONObject(i);

                        Log.i("recipient", recipient.toString());

                        if (status == 0) {

                            name = recipient.getString("firstName") + " " + recipient.getString("lastName");
                            uid = recipient.getString("userId");

                            names.add(name);
                            ids.add(uid);
                        } else if (status == 1) {

                            name = recipient.getString("course");
                            grade = recipient.getString("grade");
                            uid = recipient.getString("courseId");

                            coursenames.add(name);
                            classids.add(uid);
                        }

                        //list.add(new User(name, grade, uid));
                    }

                    adapter = new RecipientAdapter(listView.getContext(), list);
                    listView.setAdapter(adapter);

                } catch (Exception e) {

                    Log.e("error", String.valueOf(e));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(request);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(SIGNSErstellenRecipient.this, SignsErstellen.class);


                if (status == 0) {
                    intent.putExtra("id", ids.get(i));
                    intent.putExtra("name", names.get(i));
                } else if (status == 1) {
                    intent.putExtra("id", classids.get(i));
                    intent.putExtra("name", coursenames.get(i));
                }

                startActivity(intent);
            }
        });
    }
}