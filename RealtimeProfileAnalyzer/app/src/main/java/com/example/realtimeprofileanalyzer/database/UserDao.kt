package com.example.realtimeprofileanalyzer.database

class UserDao(private val db: DatabaseHelper) {

    fun login(email: String, password: String): Boolean {
        return db.loginUser(email, password)
    }

    fun register(email: String, password: String): Boolean {
        return db.registerUser(email, password)
    }
}
