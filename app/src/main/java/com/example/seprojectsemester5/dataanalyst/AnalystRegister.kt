package com.example.seprojectsemester5.dataanalyst

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.seprojectsemester5.R
import android.content.Intent
import com.example.seprojectsemester5.authentication.AuthViewModel
import androidx.lifecycle.ViewModelProvider
import android.widget.Button
import android.widget.EditText
import com.example.seprojectsemester5.models.User
import android.widget.Toast
import com.example.seprojectsemester5.repositories.remote.Resource

class AnalystRegister : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analyst_register)

        val goToAnalystLogin : TextView = findViewById(R.id.goToAnalystLogin)
        goToAnalystLogin.setOnClickListener {
            startActivity(Intent(this@AnalystRegister, AnalystLogin::class.java))
            finish()
        }

        // viewModel
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
            .get(AuthViewModel::class.java)

        // register Button
        val registerButton = findViewById<Button>(R.id.data_analyst_register_button)
        val nameEditText : EditText = findViewById(R.id.data_analyst_register_name_edit_text)
        val emailEditText : EditText = findViewById(R.id.data_analyst_register_email_edit_text)
        val passwordEditText : EditText = findViewById(R.id.data_analyst_register_password_edit_text)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                viewModel.register(
                    User(
                        null,
                        name,
                        email,
                        password,
                        null,
                        null,
                        "Analyst",
                        null,
                        null
                    )
                )
            } else {
                Toast.makeText(this, "Please fill all details properly", Toast.LENGTH_LONG)
                    .show()
            }

        }

        // registerResponseResults
        viewModel.registerResponse.observe(this, {
            when(it){
                is Resource.Success -> {
                    if(it.value.status == "OK"){
                        Toast.makeText(this,
                            "User registration Successfully!!. Please validate your email through otp verification.",
                            Toast.LENGTH_LONG).show()

//                        lifecycleScope.launch {
//                            val user = it.value.User
//                            viewModel.userDetails.storeName(user?.name!!)
//                            viewModel.userDetails.storeEmail(user.email)
//                        }
                    } else {
                        Toast.makeText(this,
                            "${it.value.message}. Please try again.",
                            Toast.LENGTH_LONG).show()
                    }
                }
                else -> {
                    Toast.makeText(this,
                        "User registration Unsuccessful!!. Please try again.",
                        Toast.LENGTH_LONG).show()
                }
            }
        })

        val otpButton = findViewById<Button>(R.id.data_analyst_register_opt_button)
        val otpEditText = findViewById<EditText>(R.id.data_analyst_register_opt_edit_text)
        otpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val opt = otpEditText.text.toString()
            if(email.isNotEmpty() || opt.length == 6){
                viewModel.verify(email, opt)
            } else {
                Toast.makeText(this, "Please enter correct details", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.verifyResponse.observe(this, {
            when(it){
                is Resource.Success -> {
                    if(it.value.status == "OK"){
                        Toast.makeText(this,
                            "${it.value.message} successfully!! NGO Verification Pending.",
                            Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, AnalystLogin::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, it.value.message, Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                    Toast.makeText(this, "OTP verification failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })

        // observe stored email
//        viewModel.userDetails.email.asLiveData().observe(this, { it ->
//            val otpButton = findViewById<Button>(R.id.data_analyst_register_opt_button)
//            val otpEditText = findViewById<EditText>(R.id.data_analyst_register_opt_edit_text)
//
//            if(it!!.isNotEmpty()){
//                // otp button
//                registerButton.isEnabled = false
//
//                otpButton.setOnClickListener {
//                    val opt = otpEditText.text.toString()
//                    if(opt.length == 6){
//                        viewModel.verify(it.toString(), opt)
//                    } else {
//                        Toast.makeText(this, "Please enter correct otp", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } else {
//                registerButton.isEnabled = true
//                otpButton.isEnabled = false
//            }
//        })

        // observe user jwt
//        viewModel.userDetails.jwtToken.asLiveData().observe(this, {
//            if(!it.isNullOrEmpty()){
//                startActivity(Intent(this, DataAnalystMainActivity::class.java))
//                finish()
//            }
//        })
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        lifecycleScope.cancel()
//    }
}