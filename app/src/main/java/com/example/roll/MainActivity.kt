package com.example.roll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView:  TextView = findViewById(R.id.creatid)
        val viewtext: TextView =findViewById(R.id.FP)
        textView.setOnClickListener {

            val intent = Intent(this,activity_register::class.java)
            startActivity(intent)
        }

        viewtext.setOnClickListener {
            val intent = Intent(this,homepage::class.java)
            startActivity(intent)
        }


    }
}
