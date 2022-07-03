package com.abahstudio.githubuserdicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<GitHubUser>("DATA")


        var imgAvatar: ImageView = findViewById(R.id.iv_detail_avatar)
        var tvName: TextView = findViewById(R.id.tv_detail_name)
        var tvUserName: TextView = findViewById(R.id.tv_detail_username)
        var tvFolowers: TextView = findViewById(R.id.tv_detail_folower)
        var tvFolowing: TextView = findViewById(R.id.tv_detail_folowing)
        var tvCompany: TextView = findViewById(R.id.tv_detail_company)
        var tvLocation: TextView = findViewById(R.id.tv_detail_location)
        var tvRepository: TextView = findViewById(R.id.tv_detail_repository)
        Glide.with(this)
            .load(data?.avatar!!)
            .circleCrop()
            .into(imgAvatar)
        tvName.text = data.name
        tvUserName.text = "@" + data.username
        tvFolowers.text = data.followers + " followers"
        tvFolowing.text = "* " +data.followers + " folowing"
        tvCompany.text = data.company
        tvLocation.text = data.location
        tvRepository.text = data.repository + " Repositories"

    }

    companion object {
        const val DATA = "data"
    }
}