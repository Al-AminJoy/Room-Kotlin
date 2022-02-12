package com.alamin.room_kotlin.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alamin.room_kotlin.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun adUser(user: User);

    @Update
    suspend fun updateUser(user : User);

    @Delete
    suspend fun deleteUser(user: User);

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser();

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>;
}