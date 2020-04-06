package com.example.persistance

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "trophy",
    foreignKeys = [ForeignKey( entity = User::class, parentColumns = ["id"], childColumns = ["useId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index(value = ["id"])]
    )
data class Trophy(@PrimaryKey(autoGenerate = true) val id : Long, var userId : Long, var description : String)