package com.example.seprojectsemester5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectRole extends AppCompatActivity {
    CardView collectorCard,analystCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);
        collectorCard = findViewById(R.id.collectorCard);
        analystCard = findViewById(R.id.analystCard);
        collectorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectRole.this,Login.class));
                finish();
            }
        });
        analystCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectRole.this,AnalystLogin.class));
                finish();
            }
        });
    }
}