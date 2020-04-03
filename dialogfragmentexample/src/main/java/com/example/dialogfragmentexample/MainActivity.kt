package com.example.dialogfragmentexample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.dialogfragmentexample.MyDialogFragment.UserNameListener


class MainActivity : AppCompatActivity(), UserNameListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onFinishUserDialog(user: String?) =
        Toast.makeText(this, "Hello, $user", Toast.LENGTH_SHORT).show()

    fun onClick(view: View) {
        val manager: FragmentManager = supportFragmentManager
        val frag: Fragment? = manager.findFragmentByTag("fragment_edit_name")
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit()
        }
        when (view.id) {
            R.id.btn1 -> {
                val editNameDialog = MyDialogFragment()
                editNameDialog.show(manager, "fragment_edit_name")
            }
            R.id.btn2 -> {
                val alertDialogFragment = MyAlertDialogFragment()
                alertDialogFragment.show(manager, "fragment_edit_name")
            }
        }
    }
}