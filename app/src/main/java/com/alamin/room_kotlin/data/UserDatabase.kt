package com.alamin.room_kotlin.data

import android.content.Context
import androidx.room.*
import com.alamin.room_kotlin.data.dao.UserDao
import com.alamin.room_kotlin.data.model.User
import com.alamin.room_kotlin.utils.Converters

@Database(entities = [User::class],version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserDatabase: RoomDatabase() {
 abstract fun userDao(): UserDao;

    companion object{

        private var INSTANCE: UserDatabase? =  null;

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE;
            if (tempInstance != null){
                return tempInstance;
            }

            synchronized(this){
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   UserDatabase::class.java,
                   "user_database")
                   .build();
                INSTANCE = instance;
                return instance;
            }
        }
    }
}