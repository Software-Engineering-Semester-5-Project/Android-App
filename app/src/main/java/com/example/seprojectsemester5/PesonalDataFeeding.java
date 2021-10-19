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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class PesonalDataFeeding extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesonal_data_feeding);



        EditText aadharNumber = (EditText) findViewById(R.id.uid);
        EditText Name = (EditText) findViewById(R.id.name);
        EditText Phone = (EditText) findViewById(R.id.phone);
        EditText Age = (EditText) findViewById(R.id.age);
        EditText Pin = (EditText) findViewById(R.id.pincode);
        RadioGroup Gender = findViewById(R.id.radioGroup);


        Button personalDataSubmit = (Button) findViewById(R.id.personalDataSubmit);

        personalDataSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String check = aadharNumber.getText().toString();
                if(check.length() == 12){
                    if(check.charAt(0) != '0' && check.charAt(0) != '1'){
                        if(check.matches("[0-9]+")){
                            if(Name.getText().toString().length() > 0 && Name.getText().toString().length()  > 0 && Pin.getText().toString().length() > 0){
                                        if(Age.getText().toString().length() <= 3 && Age.getText().toString().matches("[0-9]+")){
                                            if(Pin.getText().toString().length() == 6 && Pin.getText().toString().matches("[0-9]+")){
                                                int id = Gender.getCheckedRadioButtonId();
                                                RadioButton mode = findViewById(id);
                                                Intent intent = new Intent(PesonalDataFeeding.this, QuestionAnswerPage.class);
                                                intent.putExtra("Name", Name.getText().toString() );
                                                intent.putExtra("Age", Age.getText().toString());
                                                intent.putExtra("Phone", Phone.getText().toString());
                                                intent.putExtra("Pin", Pin.getText().toString());
                                                intent.putExtra("AadharNumber", aadharNumber.getText().toString());
                                                intent.putExtra("Gender", mode.getText().toString());

                                                PesonalDataFeeding.this.startActivity(intent);
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(), "Invalid Pin !", Toast.LENGTH_SHORT).show();
                                                Toast.makeText(getApplicationContext(), "Please Retry", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(), "Invalid Age !", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), "Please Retry", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                            else{
                                        Toast.makeText(getApplicationContext(), "Name, Age, Pin should be Non-Empty", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getApplicationContext(), "Please Retry", Toast.LENGTH_SHORT).show();
                                    }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "It should not contain any alphabet and special characters.", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Please Retry", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "It should not start with 0 and 1", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Please Retry", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "It should have 12 digits", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Please Retry", Toast.LENGTH_SHORT).show();
                }


            }

        });

    }
}