package com.example.coroutine_demo.data.local

import com.example.coroutine_demo.data.local.entity.User

interface DatabaseHelper {
    suspend fun getUsers(): List<User>

    suspend fun insertAll(users: List<User>)
}