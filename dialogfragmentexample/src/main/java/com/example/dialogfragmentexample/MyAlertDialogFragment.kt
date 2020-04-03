package com.example.dialogfragmentexample

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment


class MyAlertDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setIcon(R.drawable.ic_error)
            .setTitle("Alert dialog fragment example")
            .setMessage("This is a message")
            .setPositiveButton("OK"
            ) { _, _ ->
                Toast.makeText(
                    activity,
                    "Pressed OK",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Cancel"
            ) { _, _ ->
                Toast.makeText(
                    activity,
                    "Cancel",
                    Toast.LENGTH_SHORT
                ).show()
            }.create()
    }
}