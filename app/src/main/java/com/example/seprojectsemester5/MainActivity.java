package com.example.seprojectsemester5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.seprojectsemester5.dataanalyst.DataAnalystMainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("FIXED", MODE_PRIVATE);
        String token = sharedPreferences.getString("roleToken", "ANY DEFAULT VALUE");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if(token.equals("Analyst")){
                    startActivity(
                            new Intent(
                                    MainActivity.this,
                                    DataAnalystMainActivity.class));
                } else if(token.equals("Collector")){
                    startActivity(new Intent(
                            MainActivity.this,
                            Dashboard.class
                    ));
                } else {
                    startActivity(new Intent(MainActivity.this, SelectRole.class));
                }
                finish();
            }
        },2000);
    }
}