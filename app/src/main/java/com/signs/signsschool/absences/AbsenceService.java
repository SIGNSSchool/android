package com.signs.signsschool.absences;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.signs.signsschool.common.Common;
import com.signs.signsschool.absences.models.Absence;
import com.signs.signsschool.absences.models.ModelFehlstundenS;
import com.signs.signsschool.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AbsenceService {
    ArrayList<ModelFehlstundenS> list = new ArrayList<>();
    public static ArrayList<Absence> absences = new ArrayList<>();

    public static void getAbsencesBySchoolId(Context context) {

        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/absences/school/" + Common.getAccountParamByKey("schoolId", context), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("absences", response.toString());

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i =0; i < jsonArray.length(); i++) {
                        JSONObject absence = jsonArray.getJSONObject(i);

                        absences.add(new Absence(absence.getString("fromDate"), absence.getString("toDate"), absence.getString("createdAt").substring(0,10), absence.getString("updatedAt"), absence.getString("userId"), absence.getString("grade"), absence.getString("reason"), absence.getString("pk"), absence.getBoolean("isExcused"), new User(absence.getJSONObject("user"))));
                    }
                } catch (Exception e) {
                    Log.e("error parsing response", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error querying absences", error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);



    }

    public static void getAbsencesByTeacherId(Context context) {

        StringRequest request = new StringRequest(Request.Method.GET, Common.getApiUrl() + "/absences/teachers" + Common.getAccountParamByKey("userId", context), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("absences", response.toString());

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i =0; i < jsonArray.length(); i++) {
                        JSONObject absence = jsonArray.getJSONObject(i);

                        absences.add(new Absence(absence.getString("fromDate"), absence.getString("toDate"), absence.getString("createdAt").substring(0,10), absence.getString("updatedAt"), absence.getString("userId"), absence.getString("grade"), absence.getString("reason"), absence.getString("pk"), absence.getBoolean("isExcused"), new User(absence.getJSONObject("user"))));
                    }
                } catch (Exception e) {
                    Log.e("error parsing response", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error querying absences", error.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }

    public ArrayList<ModelFehlstundenS> getList(String response) {

        Log.i("absences: ", response);

        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject absence = jsonArray.getJSONObject(i);

                list.add(new ModelFehlstundenS(absence.getString("grade"), absence.getString("fromDate"), absence.getString("toDate"), absence.getString("reason"), absence.getString("userId"), absence.getString("updatedAt"), absence.getString("createdAt"), absence.getString("pk"), absence.getBoolean("isExcused")));
            }

        } catch (Exception e) {

            Log.i("error", String.valueOf(e));
        }
        return list;
    }

}

class AbsenceRequest extends StringRequest {
    public AbsenceRequest(Response.Listener<String> listener, Context context) {
        super(Method.GET, Common.getApiUrl() + "/absences/" + Common.getAccountParamByKey("userId", context), listener, null);
    }
}


