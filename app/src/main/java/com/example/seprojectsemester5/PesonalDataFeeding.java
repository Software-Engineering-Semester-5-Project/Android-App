package com.example.seprojectsemester5;

import static com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class PesonalDataFeeding extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesonal_data_feeding);

        final Button button = (Button) findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesonalDataFeeding.this, QuestionAnswerPage.class);
                PesonalDataFeeding.this.startActivity(intent);
            }
        });
    }
}