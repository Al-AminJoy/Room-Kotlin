package com.alamin.room_kotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alamin.room_kotlin.R
import com.alamin.room_kotlin.adapter.ListAdapter
import com.alamin.room_kotlin.data.UserViewModel
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
        return view;
    }

    private fun setRecyclerView(view: View) {
        adapter = ListAdapter();
        recyclerView = view.recyclerView;
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(requireContext());
    }

}