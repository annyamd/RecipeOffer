package com.example.recipeoffer

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.recipeoffer.db.AppDatabase

class EnterFragment : Fragment(R.layout.fragment_enter) {

    private var appDatabase: AppDatabase? = null
    private lateinit var loginEditView: EditText
    private lateinit var passwordEditView: EditText
    private lateinit var loginBtn: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            createDatabase()
            bindViews(view)

//            appDatabase?.getUserDao()?.addUser(User("anya", "123"))

            view.findViewById<Button>(R.id.login_button)
                .setOnClickListener {
                    val login = loginEditView.text.toString()
                    val password = passwordEditView.text.toString()

                    val foundUser = appDatabase?.getUserDao()?.getUserByLogin(login)
                    if (foundUser?.password == password) {
                        Toast.makeText(context, "YOU PASSED", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "NO PASS... SORRY", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun createDatabase() {
        appDatabase =
            activity?.applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "localDb"
                )
                    //                will block ui
                    .allowMainThreadQueries()
                    .build()
            }

    }

    private fun bindViews(view: View) {
        loginEditView = view.findViewById(R.id.login_et)
        loginBtn = view.findViewById(R.id.login_button)
        passwordEditView = view.findViewById(R.id.password_et)
    }

}
