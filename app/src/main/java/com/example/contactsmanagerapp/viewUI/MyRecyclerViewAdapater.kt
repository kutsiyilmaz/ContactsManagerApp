package com.example.contactsmanagerapp.viewUI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsmanagerapp.R
import com.example.contactsmanagerapp.databinding.CardItemBinding
import com.example.contactsmanagerapp.room.User

class MyRecyclerViewAdapater(private val usersList:List<User>, private val clickListener: (User)->Unit)
    :RecyclerView.Adapter<MyViewHolder>()
{



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }


}

class MyViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(user: User, clickListener: (User) -> Unit){
        binding.nameTextView.text = user.name
        binding.emailTextView.text = user.email

        binding.listItemLayout.setOnClickListener {
            clickListener(user)
        }
    }
}