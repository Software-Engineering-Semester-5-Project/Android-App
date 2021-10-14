package com.example.seprojectsemester5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionAnswerPage extends AppCompatActivity {

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer_page);

        Intent pastIntent = getIntent();
        String aadharNumber = pastIntent.getStringExtra("AadharNumber");
        String Name = pastIntent.getStringExtra("Name");
        String Phone = pastIntent.getStringExtra("Phone");
        String Age = pastIntent.getStringExtra("Age");
        String Gender = pastIntent.getStringExtra("Gender");
        String Pin = pastIntent.getStringExtra("Pin");
        builder = new AlertDialog.Builder(this);


        RadioGroup fever = (RadioGroup) findViewById(R.id.fever);
        RadioGroup cough = (RadioGroup) findViewById(R.id.cough);
        RadioGroup bodyPain = (RadioGroup) findViewById(R.id.bodyPain);
        RadioGroup breathingProblem = (RadioGroup) findViewById(R.id.breathingProblem);
        RadioGroup eyeCheck = (RadioGroup) findViewById(R.id.eyeCheck);
        RadioGroup skinCheck = (RadioGroup) findViewById(R.id.skinCheck);


        Button healthSubmit = (Button) findViewById(R.id.healthSubmit);

        healthSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id1 = fever.getCheckedRadioButtonId();
                int id2 = cough.getCheckedRadioButtonId();
                int id3 = bodyPain.getCheckedRadioButtonId();
                int id4 = breathingProblem.getCheckedRadioButtonId();
                int id5 = eyeCheck.getCheckedRadioButtonId();
                int id6 = skinCheck.getCheckedRadioButtonId();

                ArrayList<Integer> a = new ArrayList<Integer>();
                a.add(id1);
                a.add(id2);
                a.add(id3);
                a.add(id4);
                a.add(id5);
                a.add(id6);

                String survey = "";
                for(int i = 0; i < a.size(); i ++){
                    RadioButton mode = findViewById(a.get(i));
                    if(mode.getText().toString().equals("Severe")){
                        survey += '3';
                    }
                    else if(mode.getText().toString().equals("Moderate")){
                        survey += '2';
                    }
                    else if(mode.getText().toString().equals("Mild")){
                        survey += '1';
                    }
                    else{
                        survey += '0';
                    }

                }


//                Toast.makeText(getApplicationContext(), aadharNumber, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), Name, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), Phone, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), Age, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), Gender, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), Pin, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), survey, Toast.LENGTH_SHORT).show();



                builder.setMessage("Name - " + Name + "\nAadhar Number - " + aadharNumber + "\nAge - " + Age + "\nGender - " + Gender + "\nPin - " + Pin)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Retry", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Confirm Details");
                alert.show();
            }
        });
    }
}