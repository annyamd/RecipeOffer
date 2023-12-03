package com.example.recipeoffer.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeoffer.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

}