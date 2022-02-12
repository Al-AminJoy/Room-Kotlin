package com.alamin.room_kotlin.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alamin.room_kotlin.R
import com.alamin.room_kotlin.data.model.User
import com.alamin.room_kotlin.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.row_user_list.view.*

class UpdateFragment : Fragment() {
    private var TAG: String = "UpdateFragment"

    private val args by navArgs<UpdateFragmentArgs>();
    private lateinit var userViewModel: UserViewModel;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false);

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java);

        view.etAddFirstName.setText(args.currentUser.firstName)
        view.etAddLastName.setText(args.currentUser.lastName)
        view.etAddAge.setText(args.currentUser.age.toString())
        view.btnUpdate.setOnClickListener {
            updateItem();
        }
        return view;
    }

    private fun updateItem() {
        val firstName: String = etAddFirstName.text.toString();
        val lastName: String = etAddLastName.text.toString();
        val age = etAddAge.text;
        if (inputCheck(firstName, lastName, age)){
            val user = User(args.currentUser.id,firstName, lastName, Integer.parseInt(age.toString()));
            userViewModel.updateUser(user);
            findNavController().navigate(R.id.action_updateFragment_to_listFragment);
        }
    }
    private fun inputCheck(firName : String, lastName: String, age : Editable):Boolean{
        return !(TextUtils.isEmpty(firName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

}