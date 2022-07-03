package com.abahstudio.githubuserdicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.abahstudio.githubuserdicoding.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<GitHubUser>("DATA")


        Glide.with(this)
            .load(data?.avatar!!)
            .circleCrop()
            .into(binding.ivDetailAvatar)
        binding.tvDetailName.text = data.name
        binding.tvDetailUsername.text = "@" + data.username
        binding.tvDetailFolower.text = data.followers + " followers"
        binding.tvDetailFolowing.text = "* " +data.following + " folowing"
        binding.tvDetailCompany.text = data.company
        binding.tvDetailLocation.text = data.location
        binding.tvDetailRepository.text = data.repository + " Repositories"

    }

    companion object {
        const val DATA = "data"
    }
}