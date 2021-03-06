package com.alamin.room_kotlin.fragments

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.alamin.room_kotlin.R
import com.alamin.room_kotlin.data.model.Address
import com.alamin.room_kotlin.data.model.User
import com.alamin.room_kotlin.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.row_user_list.view.*
import kotlinx.coroutines.launch

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
        //Ad menu
        setHasOptionsMenu(true);
        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser();
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            userViewModel.deleteUser(args.currentUser);
            findNavController().navigate(R.id.action_updateFragment_to_listFragment);
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete ${args.currentUser.firstName} ?")
        builder.setMessage("Are You Sure Want to Delete ${args.currentUser.firstName} ?")
        builder.create().show();
    }

    private fun updateItem() {
        val firstName: String = etAddFirstName.text.toString();
        val lastName: String = etAddLastName.text.toString();
        val age = etAddAge.text;
        if (inputCheck(firstName, lastName, age)){
            lifecycleScope.launch {
                val address = Address("Nikunja-2","Dhaka");
                val user = User(args.currentUser.id,firstName, lastName, Integer.parseInt(age.toString()), address, getBitmap());
                userViewModel.updateUser(user);
                findNavController().navigate(R.id.action_updateFragment_to_listFragment);
            }
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