package com.example.madproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.madproject.R

class SellingPs : AppCompatActivity() {

    private lateinit var button6: Button
    private lateinit var button7: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selling_ps)


        button6 =findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)

        button6.setOnClickListener{


            val intent = Intent( this, SellProduct::class.java)
            startActivity(intent)
        }

        button7.setOnClickListener {
            val intent = Intent( this, FetchActivity::class.java)
            startActivity(intent)
        }

    }
}