package com.example.finalproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.finalproject.R

class MainActivity : AppCompatActivity() {

    private lateinit var button11: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash2)//set the layout "activity_dash2" for MainActivity

        button11 = findViewById(R.id.button11)

        //set a click listener for a "button11"
        button11.setOnClickListener{
            val intent = Intent( this, CatActivity::class.java)
            startActivity(intent)
        }


    }
}