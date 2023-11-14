package com.example.roll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class activity_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val firstNameEditText: EditText= findViewById(R.id.firstname)
        val lastNameEditText: EditText = findViewById(R.id.lastname)
        val mobileNumberEditText: EditText = findViewById(R.id.number)
        val passwordEditText: EditText = findViewById(R.id.password)
        val registerButton: Button = findViewById(R.id.register)

        registerButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val mobileNumber = mobileNumberEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || mobileNumber.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            } else {
                // Perform registration logic here (e.g., send data to a server, save to a database)
                // You can add your own implementation for registration

                // Display a success message
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

                // You may also navigate to another activity or perform additional actions upon successful registration
            }
        }
    }
}