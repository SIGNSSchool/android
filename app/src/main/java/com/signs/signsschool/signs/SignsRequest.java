package com.signs.signsschool.signs;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.signs.signsschool.common.Common;

public class SignsRequest extends StringRequest {
    public SignsRequest(Response.Listener<String> listener, Context context) {
        super(Method.GET, Common.getApiUrl() + "/signs/" + Common.getAccountParamByKey("userId", context), listener, null);
    }
}
