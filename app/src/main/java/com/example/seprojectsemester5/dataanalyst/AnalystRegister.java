package com.example.seprojectsemester5.dataanalyst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.seprojectsemester5.R;

public class AnalystRegister extends AppCompatActivity {
    TextView goToAnalystLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyst_register);
        goToAnalystLogin = findViewById(R.id.goToAnalystLogin);
        goToAnalystLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnalystRegister.this, AnalystLogin.class));
                finish();
            }
        });
    }
}