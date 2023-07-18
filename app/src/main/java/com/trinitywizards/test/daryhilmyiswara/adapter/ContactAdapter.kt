package com.trinitywizards.test.daryhilmyiswara.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trinitywizards.test.daryhilmyiswara.adapter.viewholder.ContactViewHolder
import com.trinitywizards.test.daryhilmyiswara.databinding.ItemListContactBinding
import com.trinitywizards.test.daryhilmyiswara.model.UserContact

class ContactAdapter : RecyclerView.Adapter<ContactViewHolder>() {

    private var listContact = mutableListOf<UserContact>()
    private var clickListener: ((UserContact, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemListContactBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContactViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(listContact[position])
    }

    override fun getItemCount(): Int = listContact.size

    fun updateListContact(list: List<UserContact>) {
        listContact.clear()
        listContact.addAll(list)
        notifyDataSetChanged()
    }

    fun updateContact(userContact: UserContact, position: Int) {
        listContact[position] = userContact
        notifyItemChanged(position)
    }

    fun setOnClickListener(listener: (UserContact, Int) -> Unit) {
        clickListener = listener
    }

}