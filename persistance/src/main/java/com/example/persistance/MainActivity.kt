package com.example.persistance

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var user: User
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = AppDatabase.getDatabase(applicationContext)

        database.userDao().removeAllUsers()

        val users =
            database.userDao().allUser
        if (users.isEmpty()) {
            database.userDao().addUser(User(1, "Test 1", 1))
            user = database.userDao().allUser[0]
            Toast.makeText(this, user.id.toString(), Toast.LENGTH_SHORT).show()
            val trophy = Trophy(user.id.toLong(), 0,getString(R.string.learn3))
            database.trophyDao().addTrophy(trophy)
            database.userDao().addUser(User(2, "Test 2", 2))
            database.userDao().addUser(User(3, "Test 3", 3))
        }
        updateFirstUserData()
    }

    private fun updateFirstUserData() {
        val user = database.userDao().allUser
        val trophiesForUser =
            database.trophyDao().findTrophiesForUser(user[0].id)
        Toast.makeText(this, trophiesForUser.toString(), Toast.LENGTH_SHORT).show()
        if (user.isNotEmpty()) {
            result.text =
                "${user[0].name}${getString(R.string.skill_points)}${user[0].skillPoints}${getString(R.string.trophy)}${trophiesForUser?.size}"
        }
    }

    fun onClick(view: View) {
        if (view.id == R.id.addtrophybutton) {
            Toast.makeText(this, user.id.toString(), Toast.LENGTH_SHORT).show()
            val trophy = Trophy(user.id.toLong(),1 ,getString(R.string.more))
            database.trophyDao().addTrophy(trophy)
        }
        if (view.id == R.id.increaseskills) {
            user.skillPoints++
            database.userDao().updateUser(user)
        }
        updateFirstUserData()
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        super.onDestroy()
    }
}