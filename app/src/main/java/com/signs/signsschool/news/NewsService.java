package com.signs.signsschool.news;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class NewsService {
    public static ArrayList<ModelNews> news = new ArrayList<>();
    public static ArrayList<ModelNewsSubmission> newsSubmissions = new ArrayList<>();

    public static void getNews(Context context) {
        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/news/" + Common.getAccountParamByKey("userId", context), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("news", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject newsResult = jsonArray.getJSONObject(i);

                        news.add(new ModelNews(newsResult.getString("submitBy"), newsResult.optString("recurringAt"), newsResult.getString("grade"), newsResult.getString("courseId"), newsResult.getString("createdAt"), newsResult.getString("schoolId"), newsResult.getString("description"), newsResult.getString("pk"), newsResult.getString("title"), newsResult.getBoolean("recurring"), newsResult.getJSONArray("options")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(request);
    }

    public static void getNewsSubmissions(Context context) {
        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/news/" + Common.getAccountParamByKey("userId", context) + "/submissions", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("news submissions", response.toString());

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject newsResult = jsonArray.getJSONObject(i);

                        newsSubmissions.add(new ModelNewsSubmission(newsResult.getString("courseId"), newsResult.getString("schoolId"), newsResult.getString("pk"), newsResult.getString("newsId"), newsResult.getString("submittedAt"), newsResult.getString("title"), new User(newsResult.getJSONObject("user")), newsResult.getJSONArray("choices")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(request);
    }

    public static void createNews(JSONObject jsonObject, Context context) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Common.getApiUrl() + "/news", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("response", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        }) {
            @Override
            public byte[] getBody() {
                return jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public static void createNewsSubmission(JSONObject jsonObject, Context context) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Common.getApiUrl() + "/news/" + Common.getAccountParamByKey("userId", context) + "/submissions", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("response", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(context).add(request);
    }
}
