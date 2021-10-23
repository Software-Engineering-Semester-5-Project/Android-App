package com.example.seprojectsemester5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionAnswerPage extends AppCompatActivity {

    AlertDialog.Builder builder;
    SQLiteDBHelper DB;
    boolean lan_selected = true;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer_page);


        TextView coughq = findViewById(R.id.coughq);
        TextView feverq = findViewById(R.id.fevelq);
        TextView bodypainq = findViewById(R.id.bodypainq);
        TextView skinq = findViewById(R.id.skinq);
        TextView eyeq = findViewById(R.id.eyeq);
        TextView breathingq = findViewById(R.id.breatingq);


        RadioButton severe1 = findViewById(R.id.Severe1);
        RadioButton severe2 = findViewById(R.id.Severe2);
        RadioButton severe3 = findViewById(R.id.Severe3);
        RadioButton severe4 = findViewById(R.id.Severe4);
        RadioButton severe5 = findViewById(R.id.Severe5);
        RadioButton severe6 = findViewById(R.id.Severe6);

        RadioButton moderate1 = findViewById(R.id.Moderate);
        RadioButton moderate2 = findViewById(R.id.Moderate2);
        RadioButton moderate3 = findViewById(R.id.Moderate3);
        RadioButton moderate4 = findViewById(R.id.Moderate4);
        RadioButton moderate5 = findViewById(R.id.Moderate5);
        RadioButton moderate6 = findViewById(R.id.Moderate6);

        RadioButton mild1 = findViewById(R.id.Mild);
        RadioButton mild2 = findViewById(R.id.Mild1);
        RadioButton mild3 = findViewById(R.id.Mild2);
        RadioButton mild4 = findViewById(R.id.Mild3);
        RadioButton mild5 = findViewById(R.id.Mild4);
        RadioButton mild6 = findViewById(R.id.Mild6);

        RadioButton nothing1 = findViewById(R.id.Nothing1);
        RadioButton nothing2 = findViewById(R.id.Nothing2);
        RadioButton nothing3 = findViewById(R.id.Nothing3);
        RadioButton nothing4 = findViewById(R.id.Nothing4);
        RadioButton nothing5 = findViewById(R.id.Nothing5);
        RadioButton nothing6 = findViewById(R.id.Nothing6);








        Intent pastIntent = getIntent();
        String aadharNumber = pastIntent.getStringExtra("AadharNumber");
        String Name = pastIntent.getStringExtra("Name");
        String Phone = pastIntent.getStringExtra("Phone");
        String Age = pastIntent.getStringExtra("Age");
        String Gender = pastIntent.getStringExtra("Gender");
        String Pin = pastIntent.getStringExtra("Pin");
        String Lang = pastIntent.getStringExtra("Lang");


        if(Lang.equals("English")) {
            context = LocaleHelper.setLocale(QuestionAnswerPage.this, "en");
            resources = context.getResources();
            coughq.setText(resources.getString(R.string.coughq));
            skinq.setText(resources.getString(R.string.skinq));
            feverq.setText(resources.getString(R.string.feverq));
            bodypainq.setText(resources.getString(R.string.bodypainq));
            breathingq.setText(resources.getString(R.string.breatingq));
            eyeq.setText(resources.getString(R.string.eyeq));
            severe1.setText(resources.getString(R.string.severe));
            severe2.setText(resources.getString(R.string.severe));
            severe3.setText(resources.getString(R.string.severe));
            severe4.setText(resources.getString(R.string.severe));
            severe5.setText(resources.getString(R.string.severe));
            severe6.setText(resources.getString(R.string.severe));
            moderate1.setText(resources.getString(R.string.moderate));
            moderate2.setText(resources.getString(R.string.moderate));
            moderate3.setText(resources.getString(R.string.moderate));
            moderate4.setText(resources.getString(R.string.moderate));
            moderate5.setText(resources.getString(R.string.moderate));
            moderate6.setText(resources.getString(R.string.moderate));
            mild1.setText(resources.getString(R.string.mild));
            mild2.setText(resources.getString(R.string.mild));
            mild3.setText(resources.getString(R.string.mild));
            mild4.setText(resources.getString(R.string.mild));
            mild5.setText(resources.getString(R.string.mild));
            mild6.setText(resources.getString(R.string.mild));
            nothing1.setText(resources.getString(R.string.nothing));
            nothing2.setText(resources.getString(R.string.nothing));
            nothing3.setText(resources.getString(R.string.nothing));
            nothing4.setText(resources.getString(R.string.nothing));
            nothing5.setText(resources.getString(R.string.nothing));
            nothing6.setText(resources.getString(R.string.nothing));
        }
        else{
            context = LocaleHelper.setLocale(QuestionAnswerPage.this, "hi");
            resources = context.getResources();
            coughq.setText(resources.getString(R.string.coughq));
            skinq.setText(resources.getString(R.string.skinq));
            feverq.setText(resources.getString(R.string.feverq));
            bodypainq.setText(resources.getString(R.string.bodypainq));
            breathingq.setText(resources.getString(R.string.breatingq));
            eyeq.setText(resources.getString(R.string.eyeq));
            severe1.setText(resources.getString(R.string.severe));
            severe2.setText(resources.getString(R.string.severe));
            severe3.setText(resources.getString(R.string.severe));
            severe4.setText(resources.getString(R.string.severe));
            severe5.setText(resources.getString(R.string.severe));
            severe6.setText(resources.getString(R.string.severe));
            moderate1.setText(resources.getString(R.string.moderate));
            moderate2.setText(resources.getString(R.string.moderate));
            moderate3.setText(resources.getString(R.string.moderate));
            moderate4.setText(resources.getString(R.string.moderate));
            moderate5.setText(resources.getString(R.string.moderate));
            moderate6.setText(resources.getString(R.string.moderate));
            mild1.setText(resources.getString(R.string.mild));
            mild2.setText(resources.getString(R.string.mild));
            mild3.setText(resources.getString(R.string.mild));
            mild4.setText(resources.getString(R.string.mild));
            mild5.setText(resources.getString(R.string.mild));
            mild6.setText(resources.getString(R.string.mild));
            nothing1.setText(resources.getString(R.string.nothing));
            nothing2.setText(resources.getString(R.string.nothing));
            nothing3.setText(resources.getString(R.string.nothing));
            nothing4.setText(resources.getString(R.string.nothing));
            nothing5.setText(resources.getString(R.string.nothing));
            nothing6.setText(resources.getString(R.string.nothing));
        }



        builder = new AlertDialog.Builder(this);
        DB = new SQLiteDBHelper(this);


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
                for(int i = 0; i < a.size(); i ++) {
                    RadioButton mode = findViewById(a.get(i));
                    if (mode.getText().toString().equals("Severe")) {
                        survey += '3';
                    } else if (mode.getText().toString().equals("Moderate")) {
                        survey += '2';
                    } else if (mode.getText().toString().equals("Mild")) {
                        survey += '1';
                    } else {
                        survey += '0';
                    }

                }


                String Disease = "";

                String finalDisease = Disease;
                String finalSurvey = survey;
                builder.setMessage("Name - " + Name + "\nAadhar Number - " + aadharNumber + "\nAge - " + Age + "\nGender - " + Gender + "\nPin - " + Pin)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                Boolean insert = DB.insertData(aadharNumber, Name, Age, Pin, Phone, Gender, finalSurvey, finalDisease);
                                if(insert == true){
                                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(QuestionAnswerPage.this, Dashboard.class);
                                    QuestionAnswerPage.this.startActivity(intent);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Something Went Wrong !!!", Toast.LENGTH_SHORT).show();
                                }

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