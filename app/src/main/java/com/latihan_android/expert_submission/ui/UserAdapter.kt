package com.latihan_android.expert_submission.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.latihan_android.core.databinding.UserRowBinding
import com.latihan_android.core.domain.module.Favorite

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>(){

    private var listData = ArrayList<Favorite>()
    var onItemClick: ((Favorite) -> Unit)? = null

    fun setData(newListData:List<Favorite>?){
        if(newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(LayoutInflater.from(parent.context).inflate(
        com.latihan_android.core.R.layout.user_row,parent,false))

    override fun onBindViewHolder(holder: UserAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = UserRowBinding.bind(itemView)
        fun bind(data:Favorite){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.userAvatar)
                    .into(imgUserPhotos)
                userName.text=data.userName
            }
        }
        init{
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }



}