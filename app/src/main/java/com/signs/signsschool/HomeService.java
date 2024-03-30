package com.signs.signsschool;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.news.NewsHomeAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeService {
    RequestQueue requestQueue;
    NewsHomeAdapter adapter;

    public void storeDeviceToken(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String token = preferences.getString("token", "no_token");
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
    }

    public List<tempModel> fetchNews() {
        List<tempModel> list = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.POST, "https://signs.school/GetNEWS.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("response", response);

                try {
                    JSONObject jObject = new JSONObject(response);
                    JSONArray jArray = jObject.getJSONArray("array");


                    for (int i=0; i < jArray.length(); i++) {
                        JSONObject oneObject = jArray.getJSONObject(i);

                        String title = oneObject.getString("Title");
                        String date = oneObject.getString("Date");
                        Integer id = oneObject.getInt("message_id");
                        String description = oneObject.getString("Description");
                        JSONArray optionArray = oneObject.getJSONArray("options");

                        list.add(new tempModel(title, date, description, id, optionArray));
                    }
                } catch (Exception e) {
                    Log.i("error", String.valueOf(e));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }}) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(new Home().getApplicationContext());
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usermail", prefs.getString("user_id", "0"));
                return params;
            }
        };
        Volley.newRequestQueue(new Home().getApplicationContext()).add(request);
        return list;
    }
}
