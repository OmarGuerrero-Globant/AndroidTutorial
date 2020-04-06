package com.example.persistance

import androidx.room.*


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @get:Query("select * from user")
    val allUser: List<User>

    @Query("select * from user where id = :userId")
    fun getUser(userId: Long): List<User>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Query("delete from user")
    fun removeAllUsers()
}