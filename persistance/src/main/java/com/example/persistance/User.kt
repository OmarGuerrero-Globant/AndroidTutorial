package com.example.persistance

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey val id : Int, var name : String, var level : Int, var skillPoints : Long = 0)