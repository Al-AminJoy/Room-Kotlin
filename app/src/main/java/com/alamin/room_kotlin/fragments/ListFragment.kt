package com.alamin.room_kotlin.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alamin.room_kotlin.R
import com.alamin.room_kotlin.adapter.ListAdapter
import com.alamin.room_kotlin.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    lateinit var adapter: ListAdapter;
    lateinit var recyclerView: RecyclerView;
    lateinit var userViewModel: UserViewModel;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false);
        setRecyclerView(view);
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java);
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it);
        })
        view.fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment);
        }
        setHasOptionsMenu(true);
        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAll();
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            userViewModel.deleteAllUser();
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_-> }
        builder.setTitle("Delete All !!!")
        builder.setMessage("Are You Sure Want to Delete Everything ?")
        builder.create().show();
    }

    private fun setRecyclerView(view: View) {
        adapter = ListAdapter();
        recyclerView = view.recyclerView;
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(requireContext());
    }

}