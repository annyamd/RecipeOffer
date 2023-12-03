package com.example.recipeoffer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val login: String,
    val password: String
)
