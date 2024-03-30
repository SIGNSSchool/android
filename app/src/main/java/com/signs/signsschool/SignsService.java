package com.signs.signsschool;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.models.User;
import com.signs.signsschool.models.LimitedQuestion;
import com.signs.signsschool.signs.SignsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignsService {
    public static ArrayList<SignsModel> signs = new ArrayList<>();

    public static void getSigns(Context context) {

        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/signs/" + Common.getAccountParamByKey("userId", context), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("signs: ", response);

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject oneObject = jsonArray.getJSONObject(i);

                        JSONArray jsonQuestions = oneObject.getJSONArray("questions");
                        ArrayList<String> questions = new ArrayList<>();

                        for (int j = 0; j < jsonQuestions.length(); j++) {
                            questions.add(jsonQuestions.getString(j));
                        }

                        JSONArray jsonOptions = oneObject.getJSONObject("limitedQuestion").getJSONArray("options");
                        ArrayList<String> options = new ArrayList<>();

                        for (int j = 0; j < jsonOptions.length(); j++) {
                            options.add(jsonOptions.getString(j));
                        }

                        signs.add(new SignsModel(oneObject.getString("subject"), oneObject.getString("submitBy"), oneObject.getString("createdAt"), oneObject.getString("message"), oneObject.getString("schoolId"), oneObject.getString("recipientId"), new User(oneObject.getJSONObject("recipient")), oneObject.getString("senderId"), new User(oneObject.getJSONObject("sender")), oneObject.getString("courseId"), oneObject.getString("pk"), questions, new LimitedQuestion(oneObject.getJSONObject("limitedQuestion").getString("question"), options)));
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
        Volley.newRequestQueue(context).add(request);
    }
}