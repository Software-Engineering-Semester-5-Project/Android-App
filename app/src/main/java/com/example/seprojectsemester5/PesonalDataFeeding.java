package com.example.seprojectsemester5;

import static com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seprojectsemester5.repositories.remote.Resource;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class PesonalDataFeeding extends AppCompatActivity {


    boolean lan_selected = true;
    Context context;
    Resources resources;
    String Lang = "English";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesonal_data_feeding);


        TextView ChangeLanguage = findViewById(R.id.changeLanguage);


        EditText aadharNumber = (EditText) findViewById(R.id.uid);
        EditText Name = (EditText) findViewById(R.id.name);
        EditText Phone = (EditText) findViewById(R.id.phone);
        EditText Age = (EditText) findViewById(R.id.age);
        EditText Pin = (EditText) findViewById(R.id.pincode);
        RadioGroup Gender = findViewById(R.id.radioGroup);
        TextView Gend = findViewById(R.id.gend);
        RadioButton one = findViewById(R.id.radio_button_1);
        RadioButton two = findViewById(R.id.radio_button_2);


        context = LocaleHelper.setLocale(PesonalDataFeeding.this,"en");
        resources =context.getResources();
        Name.setHint(resources.getString(R.string.name));
        aadharNumber.setHint(resources.getString(R.string.aadharNumber));
        Phone.setHint(resources.getString(R.string.phone));
        Age.setHint(resources.getString(R.string.age));
        Pin.setHint(resources.getString(R.string.pincode));
        Gend.setText(resources.getString(R.string.gender));
        one.setText(resources.getString(R.string.male));
        two.setText(resources.getString(R.string.female));



        ChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] language = {"English", "हिंदी"};
                int checkedItem = 0;
                if(lan_selected){
                    checkedItem = 0;
                }
                else{
                    checkedItem = 1;
                }
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PesonalDataFeeding.this);
                dialogBuilder.setTitle("Select a Language")
                        .setSingleChoiceItems(language, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(language[i].equals("English")){
                                    context = LocaleHelper.setLocale(PesonalDataFeeding.this,"en");
                                    resources =context.getResources();
                                    Name.setHint(resources.getString(R.string.name));
                                    aadharNumber.setHint(resources.getString(R.string.aadharNumber));
                                    Phone.setHint(resources.getString(R.string.phone));
                                    Age.setHint(resources.getString(R.string.age));
                                    Pin.setHint(resources.getString(R.string.pincode));
                                    Gend.setText(resources.getString(R.string.gender));
                                    setTitle(resources.getString(R.string.app_name));
                                    one.setText(resources.getString(R.string.male));
                                    two.setText(resources.getString(R.string.female));
                                    Lang = "English";
                                }
                                else if(language[i].equals("हिंदी"))
                                {
                                    context = LocaleHelper.setLocale(PesonalDataFeeding.this,"hi");
                                    resources =context.getResources();
                                    Name.setHint(resources.getString(R.string.name));
                                    aadharNumber.setHint(resources.getString(R.string.aadharNumber));
                                    Phone.setHint(resources.getString(R.string.phone));
                                    Age.setHint(resources.getString(R.string.age));
                                    Pin.setHint(resources.getString(R.string.pincode));
                                    Gend.setText(resources.getString(R.string.gender));
                                    setTitle(resources.getString(R.string.app_name));
                                    one.setText(resources.getString(R.string.male));
                                    two.setText(resources.getString(R.string.female));
                                    Lang = "हिंदी";
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                dialogBuilder.create().show();
            }
        });






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
                                                intent.putExtra("Lang", Lang);
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