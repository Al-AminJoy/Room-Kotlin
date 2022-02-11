package com.alamin.room_kotlin.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alamin.room_kotlin.R
import com.alamin.room_kotlin.data.User
import com.alamin.room_kotlin.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private lateinit var userViewModel : UserViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      val view: View = inflater.inflate(R.layout.fragment_add, container, false);
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java);
        view.btnAdd.setOnClickListener {
            insertData();
        }
        return view;
    }

    private fun insertData() {
        val firstName: String = etAddFirstName.text.toString();
        val lastName: String = etAddLastName.text.toString();
        val age = etAddAge.text;
        if (inputCheck(firstName, lastName, age)){
            val user = User(0,firstName, lastName, Integer.parseInt(age.toString()));
            userViewModel.addUser(user);
            findNavController().navigate(R.id.action_addFragment_to_listFragment);
        }

    }

    private fun inputCheck(firName : String, lastName: String, age : Editable):Boolean{
        return !(TextUtils.isEmpty(firName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

}