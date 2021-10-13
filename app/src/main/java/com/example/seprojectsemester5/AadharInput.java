package com.example.seprojectsemester5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AadharInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar_input);


        /*
        *   It should have 12 digits.
        *   It should not start with 0 and 1.
        *   It should not contain any alphabet and special characters.
        */

        EditText aadharNumber = (EditText) findViewById(R.id.aadharNumber);
        Button aadharSubmit = (Button) findViewById(R.id.aadharSubmit);

        aadharSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = aadharNumber.getText().toString();
                if(check.length() == 12){
                    if(check.charAt(0) != '0' && check.charAt(0) != '1'){
                        if(check.matches("[0-9]+")){
                            Intent intent = new Intent(AadharInput.this, PesonalDataFeeding.class);
                            intent.putExtra("AadharNumber", check);
                            AadharInput.this.startActivity(intent);
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