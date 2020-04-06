package com.example.persistance

import androidx.room.*


@Dao
interface TrophyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTrophy(trophy: Trophy?)

    @Query("SELECT * FROM trophy WHERE userId=:userId")
    fun findTrophiesForUser(userId: Int): List<Trophy?>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTrophy(trophy: Trophy?)

    @Query("delete from trophy where id = :id")
    fun delete(id: Long)
}