package com.example.endlessscrollsampleproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.endlessscrollsampleproject.R
import com.example.endlessscrollsampleproject.databinding.ItemLoadingBinding
import com.example.endlessscrollsampleproject.databinding.ItemUserBinding
import com.example.endlessscrollsampleproject.model.User
import javax.inject.Inject

class MainAdapter @Inject constructor() : ListAdapter<User, RecyclerView.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_USER_ITEM) {
            DataBindingUtil.inflate<ItemUserBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false
            ).let { MainViewHolder(it) }
        } else {
            DataBindingUtil.inflate<ItemLoadingBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_loading,
                parent,
                false
            ).let { LoadingViewHolder(it) }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MainViewHolder) {
            holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == null) VIEW_TYPE_LOADING else VIEW_TYPE_USER_ITEM
    }

    override fun submitList(list: MutableList<User?>?) {
        super.submitList(if (list != null) ArrayList<User?>(list) else null);
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }

        const val VIEW_TYPE_USER_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }
}

class MainViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        binding.user = user
    }
}

class LoadingViewHolder(private val binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind() { }
}