package com.example.walmartapplication

import java.io.Serializable

class User(
    val firstname: String,
    val lastname: String,
    val username: String, // Assume the username is an email.
    val password: String
) : Serializable
