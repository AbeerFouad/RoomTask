package com.example.testapp.presentation.ShowUsers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.domain.entities.User
import kotlinx.android.synthetic.main.user_item_layout.view.*

class UsersAdapter : ListAdapter<User, UsersAdapter.ViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.nameTv.text = user.name.value
            itemView.jobTitleTv.text = if (user.jobTitle != null) user.jobTitle.value else "N/A"
            itemView.ageTv.text = if (user.age != null) user.age.value.toString() else "N/A"
            itemView.genderTv.text = if (user.gender != null) user.gender.name else "N/A"

        }
    }
}

val diff = object : DiffUtil.ItemCallback<User?>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name == newItem.name
    }
}