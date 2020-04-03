package com.example.dialogfragmentexample

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_username.view.*


class MyDialogFragment
    : DialogFragment(), OnEditorActionListener {

    interface UserNameListener {
        fun onFinishUserDialog(user: String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_username, container)
        view.username.setOnEditorActionListener(this)
        view.username.requestFocus()
        dialog?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        )
        dialog?.setTitle("Please enter username")
        return view
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        val activity =
            activity as UserNameListener
        activity.onFinishUserDialog(view?.username?.text.toString())
        this.dismiss()
        return true
    }
}