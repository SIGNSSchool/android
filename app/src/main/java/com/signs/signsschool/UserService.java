package com.signs.signsschool;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.common.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class UserService {
    static void getSchool(String email, Context context) {
        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/users/schools/email/" + email, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("school: " + response.toString());

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    JSONObject principal = jsonObject.getJSONObject("principal");

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("username", principal.getString("firstName") + principal.getString("lastName"));
                    editor.putString("account", "School");
                    editor.putBoolean("activated", jsonObject.getBoolean("activated"));
                    editor.putString("userId", jsonObject.getString("schoolId"));
                    editor.putString("email", jsonObject.getString("email"));
                    editor.putString("schoolId", jsonObject.getString("schoolId"));
                    editor.putString("createdAt", jsonObject.getString("createdAt"));

                    editor.putString("schoolname", jsonObject.getString("schoolname"));
                    editor.putString("city", jsonObject.getString("city"));
                    editor.putString("principalEmail", principal.getString("email"));

                    editor.apply();
                } catch (Exception e) {
                    Log.e("exception", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }

    static void getUser(String email, Context context) {
        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/users/user/email/" + email, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("user: " + response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject school = jsonObject.getJSONObject("school");
                    JSONObject principal = school.getJSONObject("principal");

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("username", jsonObject.getString("firstName") + jsonObject.getString("lastName"));
                    editor.putString("firstName", jsonObject.getString("firstName"));
                    editor.putString("account", jsonObject.getString("account"));
                    editor.putBoolean("activated", jsonObject.getBoolean("activated"));
                    editor.putString("userId", jsonObject.getString("userId"));
                    editor.putString("email", jsonObject.getString("email"));
                    editor.putString("schoolId", jsonObject.getString("schoolId"));
                    editor.putString("createdAt", jsonObject.getString("createdAt"));

                    editor.putString("schoolname", school.getString("schoolname"));
                    editor.putString("schoolActivated", school.getString("activated"));
                    editor.putString("city", school.getString("city"));
                    editor.putString("createdAt", school.getString("createdAt"));

                    editor.putString("principalName", principal.getString("firstName") + " " + principal.getString("lastName"));
                    editor.putString("principalEmail", principal.getString("email"));

                    editor.apply();
                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }

    static void storeToken(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, Common.getApiUrl() + "/users/tokens", new Response.Listener<String>() {
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
            @Override
            public byte[] getBody() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("token", preferences.getString("token", "no_token"));
                    jsonObject.put("os", "Android");
                    jsonObject.put("userId", preferences.getString("userId", "0"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }
}
