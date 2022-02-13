package com.alamin.room_kotlin.fragments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.alamin.room_kotlin.R
import com.alamin.room_kotlin.data.model.Address
import com.alamin.room_kotlin.data.model.User
import com.alamin.room_kotlin.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.coroutines.launch

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
            lifecycleScope.launch {
                val address = Address("Nikunja-2","Dhaka");
                val user = User(0,firstName, lastName, Integer.parseInt(age.toString()), address, getBitmap());
                userViewModel.addUser(user);
            }
            findNavController().navigate(R.id.action_addFragment_to_listFragment);
        }

    }

    private suspend fun getBitmap(): Bitmap {
        val loading: ImageLoader = ImageLoader(requireContext());
        val request: ImageRequest = ImageRequest.Builder(requireContext())
            .data("https://avatars3.githubusercontent.com/u/14994036?s=400&u=2832879700f03d4b37ae1c09645352a352b9d2d0&v=4")
            .build();

        val result = (loading.execute(request) as SuccessResult).drawable;
        return (result  as BitmapDrawable).bitmap;
    }

    private fun inputCheck(firName : String, lastName: String, age : Editable):Boolean{
        return !(TextUtils.isEmpty(firName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

}