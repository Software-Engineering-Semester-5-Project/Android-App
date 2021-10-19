package com.example.seprojectsemester5

import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.os.Bundle
import android.content.Intent
import com.example.seprojectsemester5.dataanalyst.DataAnalystMainActivity

class SelectRole : AppCompatActivity() {
    private lateinit var collectorCard: CardView
    private lateinit var analystCard: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_role)
        collectorCard = findViewById(R.id.collectorCard)
        analystCard = findViewById(R.id.analystCard)

        collectorCard.setOnClickListener {
            startActivity(Intent(this@SelectRole, Login::class.java))
            finish()
        }
        analystCard.setOnClickListener { // bypassing the authenticating right now
            startActivity(Intent(this@SelectRole, DataAnalystMainActivity::class.java))
            // startActivity(new Intent(SelectRole.this, AnalystLogin.class));
            finish()
        }
    }
}