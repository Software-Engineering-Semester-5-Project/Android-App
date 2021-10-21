package com.example.seprojectsemester5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    TextView goToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        goToLogin = findViewById(R.id.goToLogin);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
                finish();
            }
        });

        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        Button otpSend = findViewById(R.id.otpSend);
        Button register = findViewById(R.id.registerSubmit);
        register.setAlpha(.2f);
        register.setClickable(false);

        EditText otp = findViewById(R.id.otp);

        otpSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(), "No Field Should be Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    final String url = "https://g0uravlathwal.herokuapp.com/user/register";
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("name", name.getText().toString());
                    params.put("email", email.getText().toString());
                    params.put("password", password.getText().toString());
                    params.put("role", "Collector");
                    final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        VolleyLog.v("Response:%n %s", response.toString(4));
                                        Log.e("VOLLEY", response.toString());
                                        Toast.makeText(getApplicationContext(), "Wait", Toast.LENGTH_SHORT).show();
                                        if(response.toString().substring(11,12).equals("O")){
                                            Toast.makeText(getApplicationContext(), "OTP Sent", Toast.LENGTH_SHORT).show();
                                            register.setAlpha(1f);
                                            register.setClickable(true);
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.e("Error: ", error.getMessage());
                            Log.e("VOLLEY", error.toString());
                            Toast.makeText(getApplicationContext(), "Failed " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            return params;
                        }
                    };
                    requestQueue.add(req);
                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otp.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Otp Should be Non-Empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    final String url = "https://g0uravlathwal.herokuapp.com/user/verify";
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("otp", otp.getText().toString());
                    params.put("email", email.getText().toString());
                    params.put("role", "Collector");
                    final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        VolleyLog.v("Response:%n %s", response.toString(4));
                                        Log.e("VOLLEY", response.toString());
                                        Toast.makeText(getApplicationContext(), "Wait", Toast.LENGTH_SHORT).show();
                                        if(response.toString().substring(11,12).equals("O")){
                                            Toast.makeText(getApplicationContext(), "Email Verification Sucess", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), "NGO Verification Pending", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), Login.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(), "Wrong OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.e("Error: ", error.getMessage());
                            Log.e("VOLLEY", error.toString());
                            Toast.makeText(getApplicationContext(), "Failed " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            return params;
                        }
                    };
                    requestQueue.add(req);
                }
            }
        });

    }
}