package com.example.roll

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URLEncoder

class activity_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val firstNameEditText: EditText = findViewById(R.id.firstname)
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
                // Call function to send data to your API
                sendDataToAPI(firstName, lastName, mobileNumber, password)
            }
        }
    }

    private fun sendDataToAPI(firstName: String, lastName: String, mobileNumber: String, password: String) {
        val apiUrl = "https://swasthbackend.onrender.com/registerLogin/register"

        AsyncTask.execute {
            try {
                val url = URL(apiUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"

                // Set the content type to application/x-www-form-urlencoded
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")

                // Create URL-encoded form data
                val urlEncodedData = StringBuilder()
                urlEncodedData.append(URLEncoder.encode("mobileNumber", "UTF-8")).append("=").append(URLEncoder.encode(mobileNumber, "UTF-8")).append("&")
                urlEncodedData.append(URLEncoder.encode("FirstName", "UTF-8")).append("=").append(URLEncoder.encode(firstName, "UTF-8")).append("&")
                urlEncodedData.append(URLEncoder.encode("LastName", "UTF-8")).append("=").append(URLEncoder.encode(lastName, "UTF-8")).append("&")
                urlEncodedData.append(URLEncoder.encode("Password", "UTF-8")).append("=").append(URLEncoder.encode(password, "UTF-8"))

                // Write the data to the connection
                val os: OutputStream = connection.outputStream
                val osw = OutputStreamWriter(os, "UTF-8")
                osw.write(urlEncodedData.toString())
                osw.flush()
                osw.close()

                val responseCode = connection.responseCode

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Request was successful
                    val inputStream = connection.inputStream
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val response = reader.readText()

                    runOnUiThread {
                        Toast.makeText(this, "Registration successful: $response", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle error cases
                    runOnUiThread {
                        Toast.makeText(this, "Error during registration. Response code: $responseCode", Toast.LENGTH_SHORT).show()
                    }
                }

                connection.disconnect()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
