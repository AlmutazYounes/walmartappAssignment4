package com.example.walmartapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val firstNameEditText = findViewById<EditText>(R.id.editTextFirstName)
        val lastNameEditText = findViewById<EditText>(R.id.editTextLastName)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val createAccountButton = findViewById<Button>(R.id.buttonCreateAccount)

        createAccountButton.setOnClickListener {
            // Get the entered data
            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Validate the input (make sure none of the fields are empty)
            if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Create the User object
                val newUser = User(firstName, lastName, email, password)

                // Create an Intent to return the result
                val resultIntent = Intent()
                resultIntent.putExtra("NEW_USER", newUser) // You'll need to make User class implement Parcelable or Serializable
                setResult(RESULT_OK, resultIntent)

                // Show a Toast message
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG).show()

                // Finish the Activity
                finish()
            } else {
                Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
