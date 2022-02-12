package com.alamin.room_kotlin.data.repository

import androidx.lifecycle.LiveData
import com.alamin.room_kotlin.data.model.User
import com.alamin.room_kotlin.data.dao.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<User>> = userDao.readAllData();

    suspend fun addUser(user: User){
        userDao.adUser(user);
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user);
    }
}