package com.example.seprojectsemester5.dataanalyst

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.seprojectsemester5.R
import android.content.Intent
import android.view.View
import com.example.seprojectsemester5.authentication.AuthViewModel
import androidx.lifecycle.ViewModelProvider
import android.widget.Button
import android.widget.EditText
import com.example.seprojectsemester5.models.User
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.seprojectsemester5.repositories.remote.Resource
import kotlinx.coroutines.launch
import android.content.SharedPreferences




class AnalystLogin : AppCompatActivity() {
    private lateinit var goToRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analyst_login)

        goToRegister = findViewById(R.id.goToAnalystRegister)
        goToRegister.setOnClickListener {
            startActivity(
                Intent(
                    this@AnalystLogin,
                    AnalystRegister::class.java
                )
            )
            finish()
        }

        // viewModel
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )
            .get(AuthViewModel::class.java)

        // user Details

        // login button
        (findViewById<View>(R.id.data_analyst_login_button) as Button).setOnClickListener {
            val email = (findViewById<View>(R.id.data_analyst_login_email_edit_text) as EditText)
                .text.toString()
            val password =
                (findViewById<View>(R.id.data_analyst_login_password_edit_text) as EditText)
                    .text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                viewModel.login(
                    User(
                        null,
                        null,
                        email,
                        password,
                        null,
                        null,
                        "Analyst",
                        null,
                        null
                    )
                )

//                lifecycleScope.launch {
//                    viewModel.userDetails.storeEmail(email)
//                }
            }
        }

        // observe login response
        viewModel.loginResponse.observe(this,{
            when(it){
                is Resource.Success -> {
                    if(it.value.status == "OK"){
                        Toast.makeText(this,
                            "User login Successfully!!.",
                            Toast.LENGTH_LONG).show()

//                        lifecycleScope.launch {
//                            viewModel.userDetails.storeJwtToken(it.value.token!!)
//                        }

                        val sharedpreferences = getSharedPreferences("FIXED", MODE_PRIVATE)
                        val editor = sharedpreferences.edit()
                        editor.putString("jwtToken", it.value.token!!)
                        editor.apply()
                        startActivity(Intent(this, DataAnalystMainActivity::class.java))
                    } else {
                        Toast.makeText(this,
                            "${it.value.message}. Please try again.",
                            Toast.LENGTH_LONG).show()
                    }
                }
                else -> {
                    Toast.makeText(this,
                        "User login Unsuccessful!!. Please try again.",
                        Toast.LENGTH_LONG).show()
                }
            }
        })

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