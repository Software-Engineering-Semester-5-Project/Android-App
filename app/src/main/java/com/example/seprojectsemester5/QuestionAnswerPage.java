package com.example.seprojectsemester5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

public class QuestionAnswerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer_page);
        RadioGroup fever = (RadioGroup) findViewById(R.id.fever);
        RadioGroup cough = (RadioGroup) findViewById(R.id.cough);
        RadioGroup bodyPain = (RadioGroup) findViewById(R.id.bodyPain);
        RadioGroup breathingProblem = (RadioGroup) findViewById(R.id.breathingProblem);
        RadioGroup eyeCheck = (RadioGroup) findViewById(R.id.eyeCheck);
        RadioGroup skinCheck = (RadioGroup) findViewById(R.id.skinCheck);
        EditText edit_text = (EditText) findViewById(R.id.edit_text);
    }
}