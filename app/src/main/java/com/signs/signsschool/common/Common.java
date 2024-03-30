package com.signs.signsschool.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.models.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Common {
    public static String getApiUrl() {
        Config config = new Config();
        return config.apiUrl;
    }

    public static String getAccountParamByKey(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        return preferences.getString(key, "");
    }

    public static ArrayList<Course> courses = new ArrayList<>();
    public static void getCoursesByTeacherId(Context context) {
        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/users/courses/teachers/" + Common.getAccountParamByKey("userId", context), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("courses", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0; i < jsonArray.length(); i++) {
                       courses.add(new Course((JSONObject) jsonArray.get(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }

    public static void getCoursesBySchoolId(Context context) {
        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/users/courses/" + Common.getAccountParamByKey("schoolId", context), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("courses", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0; i < jsonArray.length(); i++) {
                        courses.add(new Course((JSONObject) jsonArray.get(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }
}
