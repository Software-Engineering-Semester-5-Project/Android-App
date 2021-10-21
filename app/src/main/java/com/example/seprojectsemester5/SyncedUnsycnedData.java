package com.example.seprojectsemester5;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SyncedUnsycnedData extends AppCompatActivity {

    SQLiteDBHelper DB;
    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synced_unsycned_data);


        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active = manager.getActiveNetworkInfo();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DB = new SQLiteDBHelper(this);

        ArrayList<ArrayList<String>> DATA = new ArrayList<ArrayList<String>>();

        Cursor res = DB.getData();
        if(res.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No Entries Found", Toast.LENGTH_SHORT).show();
        }
        else{
            while (res.moveToNext()){
                ArrayList<String> a = new ArrayList<>();
                a.add(res.getString(0));
                a.add(res.getString(1));
                a.add(res.getString(2));
                a.add(res.getString(3));
                a.add(res.getString(4));
                a.add(res.getString(5));
                a.add(res.getString(6));
                a.add(res.getString(7));
                DATA.add(a);
            }

            int N = DATA.size();
            String[] aadharNumber = new String[N];
            String[] name = new String[N];
            String[] age = new String[N];
            String[] gender = new String[N];
            String[] pin = new String[N];
            String[] phone = new String[N];

            for(int i = 0; i < N; i ++){
                aadharNumber[i] = "UID " + DATA.get(i).get(0);
                name[i] = DATA.get(i).get(1);
                age[i] = DATA.get(i).get(2);
                gender[i] = DATA.get(i).get(5);
                pin[i] = DATA.get(i).get(3);
                phone[i] = DATA.get(i).get(4);
            }

            recyclerView.setAdapter(new UnsyncedDataAdapter(aadharNumber, name, age, gender, pin, phone));

        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(active != null){

                   final String url = "https://g0uravlathwal.herokuapp.com/member/fillsurvey";
                   final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MTZlNzJmNzdkZWY2MjAwMTY5OTM2OGMiLCJlbWFpbCI6InByZXJpdGtyamhhQGdtYWlsLmNvbSIsInBhc3N3b3JkIjoiJDJhJDEwJDMuWlB4QXV5WllxNzVQcmRIZ05OVU92Zi5MS0FlTTA2UFhjRGIvdnkvZk9yeW5ZeVlTajQyIiwicm9sZSI6IkNvbGxlY3RvciIsIm5hbWUiOiJQcmVyaXQgS3VtYXIgSmhhIiwidmVyaWZ5IjoiVmVyaWZpZWQiLCJ2ZXJpZnluZ28iOiJWZXJpZmllZCIsIl9fdiI6MCwiaWF0IjoxNjM0NjQwNTY4fQ.CWVn78OGhq6f4_8QSoq0_iIAuJB7yUoFsdF_JO9urLY";

                    int N = DATA.size();
                    String aadharNumber;
                    String name;
                    String age;
                    String gender;
                    String pin;
                    String phone;
                    String survey;
                    String disease;
                   for(int i = 0; i < DATA.size(); i ++){
                       aadharNumber = DATA.get(i).get(0);
                       name = DATA.get(i).get(1);
                       age = DATA.get(i).get(2);
                       gender = DATA.get(i).get(5);
                       pin = DATA.get(i).get(3);
                       phone = DATA.get(i).get(4);
                       survey = DATA.get(i).get(6);
                       disease = DATA.get(i).get(7);


                       HashMap<String, String> params = new HashMap<String, String>();
                       params.put("name", name);
                       params.put("age", age);
                       params.put("aadharNumber", aadharNumber);
                       params.put("gender", gender);
                       params.put("pin", pin);
                       params.put("phone", phone);
                       params.put("survey", survey);
                       params.put("disease", disease);


                       Toast.makeText(getApplicationContext(), params.get("survey"), Toast.LENGTH_SHORT).show();


                       final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


                       JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                               new Response.Listener<JSONObject>() {
                                   @Override
                                   public void onResponse(JSONObject response) {
                                       try {
                                           VolleyLog.v("Response:%n %s", response.toString(4));
                                           Log.e("VOLLEY", response.toString());
                                           Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

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
                               params.put("x-auth-token", token);

                               return params;
                           }
                       };
                       requestQueue.add(req);



                   }





                    progressBar = new ProgressDialog(view.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Syncing to Cloud ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();
                    //reset progress bar and filesize status
                    progressBarStatus = 0;
                    fileSize = 0;

                    new Thread(new Runnable() {
                        public void run() {
                            while (progressBarStatus < 100) {
                                // performing operation
                                progressBarStatus = doOperation();
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                // Updating the progress bar
                                progressBarHandler.post(new Runnable() {
                                    public void run() {
                                        progressBar.setProgress(progressBarStatus);
                                    }
                                });
                            }
                            // performing operation if file is downloaded,
                            if (progressBarStatus >= 100) {
                                // sleeping for 1 second after operation completed
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                // close the progress bar dialog
                                progressBar.dismiss();
                            }
                        }
                    }).start();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Connectivity Issue", Toast.LENGTH_SHORT).show();
                }







                }//end of onClick method

        });

    }



    public int doOperation() {
        //The range of ProgressDialog starts from 0 to 10000
        while (fileSize <= 10000) {
            fileSize++;
            if (fileSize == 1000) {
                return 10;
            } else if (fileSize == 2000) {
                return 20;
            } else if (fileSize == 3000) {
                return 30;
            } else if (fileSize == 4000) {
                return 40;
            } else if(fileSize == 5000) {
                return 50;
            } else if(fileSize == 6000) {
                return 60;
            } else if(fileSize == 7000) {
                return 70;
            } else if(fileSize == 8000) {
                return 80;
            } else if(fileSize == 9000) {
                return 90;
            }
         /* else {
                return 100;
            }*/
        }//end of while
        return 100;
    }//end of doOperation
}