package com.abahstudio.githubuserdicoding

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
    var name: String,
    var username: String,
    var location: String,
    var repository: String,
    var company: String,
    var followers: String,
    var following: String,
    var avatar: Int,
) : Parcelable
