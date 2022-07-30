package com.abahstudio.githubuserdicoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abahstudio.githubuserdicoding.GitHubUser
import com.abahstudio.githubuserdicoding.R
import com.bumptech.glide.Glide

class ListGitHubUserAdapter(private val listGitHubUser: ArrayList<GitHubUser>) : RecyclerView.Adapter<ListGitHubUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_githubuser, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, username, _, _,company, _, _, avatar) = listGitHubUser[position]
        Glide.with(holder.itemView.context)
            .load(avatar)
            .circleCrop()
            .into(holder.imgAvatar)
        holder.tvName.text = name
        holder.tvUserName.text = "@" + username
        holder.tvCompany.text = company



        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listGitHubUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listGitHubUser.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgAvatar: ImageView = itemView.findViewById(R.id.img_item_avatar)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvUserName: TextView = itemView.findViewById(R.id.tv_item_username)
        var tvCompany: TextView = itemView.findViewById(R.id.tv_item_company)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GitHubUser)
    }


}

