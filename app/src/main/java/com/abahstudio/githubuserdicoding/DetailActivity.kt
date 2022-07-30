package com.abahstudio.githubuserdicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.abahstudio.githubuserdicoding.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

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


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

    }

    companion object {
        const val DATA = "data"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}