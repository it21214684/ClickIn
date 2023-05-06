package com.example.clickin.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.clickin.R

class HomeActivity : AppCompatActivity() {

    private lateinit var button10: Button
    private lateinit var button11: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        button10 = findViewById(R.id.button10)
        button11 = findViewById(R.id.button11)

        button10.setOnClickListener {
            val intent = Intent(this, VacanciessActivity::class.java)
            startActivity(intent)
        }
        button11.setOnClickListener {
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }
    }
}