package com.trinitywizards.test.daryhilmyiswara.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trinitywizards.test.daryhilmyiswara.adapter.viewholder.ContactViewHolder
import com.trinitywizards.test.daryhilmyiswara.databinding.ItemListContactBinding
import com.trinitywizards.test.daryhilmyiswara.model.UserContact

class ContactAdapter : RecyclerView.Adapter<ContactViewHolder>() {

    private var listGames = listOf<UserContact>()
    private var clickListener: (UserContact) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemListContactBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContactViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(listGames[position])
    }

    override fun getItemCount(): Int = listGames.size

    fun updateListGames(list: List<UserContact>) {
        listGames = list
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (UserContact) -> Unit) {
        clickListener = listener
    }

}