package com.trinitywizards.test.daryhilmyiswara.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.trinitywizards.test.daryhilmyiswara.databinding.ItemListContactBinding
import com.trinitywizards.test.daryhilmyiswara.model.UserContact

class ContactViewHolder(
    private val binding: ItemListContactBinding,
    private val clickListener: (UserContact) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userContact: UserContact) {
        binding.run {
            "${userContact.firstname} ${userContact.lastname}".also { tvContactName.text = it }
            root.setOnClickListener {
                clickListener.invoke(userContact)
            }
        }
    }

}