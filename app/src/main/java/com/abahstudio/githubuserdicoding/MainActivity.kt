package com.abahstudio.githubuserdicoding

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import android.content.Intent
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    private lateinit var rvGitHubUser: RecyclerView
    private val list = ArrayList<GitHubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGitHubUser  = findViewById(R.id.rv_githubuser)
        rvGitHubUser.setHasFixedSize(true)

        list.addAll(listGitHubUsers)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
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