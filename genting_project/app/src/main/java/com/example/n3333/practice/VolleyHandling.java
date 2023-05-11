package com.example.n3333.practice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class VolleyHandling extends AppCompatActivity implements View.OnClickListener {

    private Button btnApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_handling);

        btnApi = findViewById(R.id.btn_api);
        btnApi.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Check", "OnResume Called...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Check", "onPause Called...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Check", "onDestroy Called...");
    }


    @Override
    public void onClick(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.example.com";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle response
                        Log.i("TAG","checking onResponse....");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                Log.i("TAG","checking onErrorResponse....");

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("param1", "value1");
                params.put("param2", "value2");
                return params;
            }
        };

        queue.add(stringRequest);

    }
}
