package com.example.madproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.madproject.R

class MainActivity : AppCompatActivity() {


    private lateinit var button9: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        button9 = findViewById(R.id.button9)

       button9.setOnClickListener{


            val intent = Intent( this, SellingPs::class.java)
           startActivity(intent)
      }


    }
}