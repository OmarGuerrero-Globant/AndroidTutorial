package com.example.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [User::class, Trophy::class],
    version = 16,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun trophyDao(): TrophyDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "userdatabase"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as AppDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}