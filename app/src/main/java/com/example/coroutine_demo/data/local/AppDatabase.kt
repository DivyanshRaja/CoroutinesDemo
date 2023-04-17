package com.example.coroutine_demo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coroutine_demo.data.local.dao.UserDao
import com.example.coroutine_demo.data.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}