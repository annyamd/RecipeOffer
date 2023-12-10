package com.example.recipeoffer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.recipeoffer.R
import com.example.recipeoffer.databinding.FragmentEnterBinding
import com.example.recipeoffer.data.db.AppDatabase

class EnterFragment : Fragment(R.layout.fragment_enter) {

    private var appDatabase: AppDatabase? = null
    private var binding: FragmentEnterBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            createDatabase()

//            appDatabase?.getUserDao()?.addUser(User("anya", "123"))
        }

        binding?.loginButton?.setOnClickListener {
            val login = binding?.loginEt?.text.toString()
            val password = binding?.passwordEt?.text.toString()

            val foundUser = appDatabase?.getUserDao()?.getUserByLogin(login)
            if (foundUser?.password == password) {
                Toast.makeText(context, "YOU PASSED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "NO PASS... SORRY", Toast.LENGTH_SHORT).show()
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
