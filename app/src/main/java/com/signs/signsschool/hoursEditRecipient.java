package com.signs.signsschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.models.ModelhoursEditRecipient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class hoursEditRecipient extends AppCompatActivity {

    ListView listView;
    ListAdapterhoursEditRecipient adapter;
    String nameString;
    Integer idInt = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_edit_recipient);





        listView = findViewById(R.id.listviewHoursRecipien);

        Button back = findViewById(R.id.FertigHoursRecipient);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(hoursEditRecipient.this, hoursEditDetail.class));

            }
        });


       String  url = "https://signs.school/GetUsersSIGNS.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("responseRecipient", response);

                try {

                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");

                    listView = (ListView) findViewById(R.id.listviewHoursRecipien);

                    List<ModelhoursEditRecipient> list = new ArrayList<>();

                    for (int i=0; i < jArray.length(); i++) {


                        JSONObject oneObject = jArray.getJSONObject(i);


                            nameString = oneObject.getString("name");
                            idInt = oneObject.getInt("id");

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                TextView name = view.findViewById(R.id.namehoursRecipient);
                                TextView id = view.findViewById(R.id.idhoursRecipient);

                                String nameItem = name.getText().toString();
                                String idItem = id.getText().toString();


                                Log.i("idrec", String.valueOf(idItem));


                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = prefs.edit();

                                editor.putString("idhoursEdit", idItem);
                                editor.putString("namehoursEdit", nameItem);
                                editor.commit();


                                Intent intent = new Intent(hoursEditRecipient.this, hoursEditDetail.class);
                                startActivity(intent);


                            }
                        });


                        list.add(new ModelhoursEditRecipient(nameString, idInt));


                    }
                    adapter = new ListAdapterhoursEditRecipient(listView.getContext(), list);
                    listView.setAdapter(adapter);

                } catch (Exception e) {

                    Log.i("error", String.valueOf(e));

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }}) {

            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                   SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(hoursEditRecipient.this);

                    params.put("school_id", prefs.getString("user_id", "0"));

                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

    }
}