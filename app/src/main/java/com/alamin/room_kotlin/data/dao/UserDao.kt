package com.alamin.room_kotlin.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alamin.room_kotlin.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun adUser(user: User);

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>;

    @Update
    suspend fun updateUser(user : User);
}