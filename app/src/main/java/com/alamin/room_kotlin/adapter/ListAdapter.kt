package com.alamin.room_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alamin.room_kotlin.R
import com.alamin.room_kotlin.data.User
import kotlinx.android.synthetic.main.row_user_list.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var userList = emptyList<User>();

    fun setData(userList: List<User>){
        this.userList = userList;
        notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_user_list,parent,false));
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val user = userList[position];
        holder.itemView.txtId.text = user.id.toString()
        holder.itemView.txtFirstName.text = user.firstName;
        holder.itemView.txtLastName.text = user.lastName;
        holder.itemView.txtAge.text = user.age.toString();
    }

    override fun getItemCount(): Int {
        return  userList.size;
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    }

}