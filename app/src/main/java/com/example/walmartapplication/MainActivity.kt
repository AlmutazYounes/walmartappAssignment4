package com.example.walmartapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val usersList = arrayListOf(
        User("Mutaz", "Younes", "mutazyounes@example.com", "password123"),
        User("Jane", "Smith", "jane.smith@example.com", "password123"),
        User("Emily", "Johnson", "emily.johnson@example.com", "password123"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val signInButton = findViewById<Button>(R.id.button)
        val forgotPasswordText = findViewById<TextView>(R.id.textView4)
        val createAccountButton = findViewById<Button>(R.id.button2)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val user = usersList.find { it.username == email && it.password == password }

            if (user != null) {
                val intent = Intent(this, ShoppingCategoryActivity::class.java).apply {
                    putExtra("USERNAME", user.username)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_LONG).show()
            }
        }

        forgotPasswordText.setOnClickListener {
            val email = emailEditText.text.toString()
            val user = usersList.find { it.username == email }

            if (user != null) {
                // Logic to send the password to the user's email
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/html"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(user.username))
                    putExtra(Intent.EXTRA_SUBJECT, "Password Recovery")
                    putExtra(Intent.EXTRA_TEXT, "Your password is: ${user.password}")
                }
                startActivity(Intent.createChooser(intent, "Send Email"))
            } else {
                Toast.makeText(this, "Email not found!", Toast.LENGTH_LONG).show()
            }
        }

        createAccountButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, REGISTER_REQUEST_CODE) // Use a constant integer value for the request code
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REGISTER_REQUEST_CODE && resultCode == RESULT_OK) {
            val newUser = data?.getSerializableExtra("NEW_USER") as? User
            newUser?.let {
                usersList.add(it)
                Toast.makeText(this, "New account created for ${it.username}", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        private const val REGISTER_REQUEST_CODE = 1 // Unique request code for registering a user
    }
}
