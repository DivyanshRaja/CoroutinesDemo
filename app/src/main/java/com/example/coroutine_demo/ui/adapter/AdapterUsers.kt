package com.example.coroutine_demo.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coroutine_demo.data.model.ApiUser
import com.example.coroutine_demo.databinding.AdapterUserBinding


class AdapterUsers(
    private val users: ArrayList<ApiUser>
) : RecyclerView.Adapter<AdapterUsers.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterUserBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        users?.get(position)?.let { holder.bind(it, position) }
    }

    inner class ViewHolder(private val binding: AdapterUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("RestrictedApi")
        fun bind(data: ApiUser, position: Int) {

            binding.textViewUserName.text = data.name
            binding.textViewUserEmail.text = data.email
            Glide.with(binding.imageViewAvatar.context).load(data.avatar)
                .into(binding.imageViewAvatar)
        }
    }


    override fun getItemCount(): Int {
        return users?.size ?: 0
    }


    fun addData(list: List<ApiUser>) {
        users.addAll(list)
    }
}