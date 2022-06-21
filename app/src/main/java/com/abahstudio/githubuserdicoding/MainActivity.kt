package com.abahstudio.githubuserdicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var rvGitHubUser: RecyclerView
    private val list = ArrayList<GitHubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGitHubUser = findViewById(R.id.rv_githubuser)
        rvGitHubUser.setHasFixedSize(true)

        list.addAll(listGitHubUsers)
        showRecyclerList()
    }

    private val listGitHubUsers: ArrayList<GitHubUser>
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val listGitHubUser = ArrayList<GitHubUser>()
            for (i in dataName.indices) {
                val gitHubUser = GitHubUser(
                    dataName[i]
                    ,dataUsername[i]
                    ,dataLocation[i]
                    ,dataRepository[i]
                    ,dataCompany[i]
                    ,dataFollowers[i]
                    ,dataFollowing[i]
                    ,dataAvatar.getResourceId(i, -1)
                )
                listGitHubUser.add(gitHubUser)
            }
            return listGitHubUser
        }
    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvGitHubUser.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvGitHubUser.layoutManager = LinearLayoutManager(this)
        }
        val listGitHubUserAdapter = ListGitHubUserAdapter(list)
        rvGitHubUser.adapter = listGitHubUserAdapter

        listGitHubUserAdapter.setOnItemClickCallback(object : ListGitHubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GitHubUser) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }

}