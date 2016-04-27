package com.example.xianskel.dreamalyze.model;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.xianskel.dreamalyze.pojos.Dream;

public class API {
    public static void makeRequest(final Context context){
        //used for queueing our request
        RequestQueue queue = Volley.newRequestQueue(context);
        //URL we will submit the post request to using the endpoint sentiment
        String url = "https://api.aylien.com/api/v1/sentiment";
        String text = "";
        //make a new StringRequest object
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("Made request with response " + response);
                            JSONObject jsonResponse = new JSONObject(response);
                            System.out.println(jsonResponse.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            //add the params which will just be the text of the dreams
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //get all the text the user used in dreams
                params.put("text", Dream.getAllDreamText(context));
                return params;
            }
            //add the headers which is the APIKey and the ApplicationID
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-AYLIEN-TextAPI-Application-Key", "87cce47e4116e3a4e478e875151e25c0");
                headers.put("X-AYLIEN-TextAPI-Application-ID", "41d463c5");

                return headers;
            }
        };
        queue.add(postRequest);
    }
}

