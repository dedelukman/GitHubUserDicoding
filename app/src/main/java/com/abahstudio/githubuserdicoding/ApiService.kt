package com.abahstudio.githubuserdicoding

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUserList(
        @Header("Authorization") authorization: String,
        @Query("q") q: String?
    ): Call<GithubUserResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Header("Authorization") authorization: String,
        @Path("username") username: String?
    ): Call<GithubUserDetailResponse>

    @GET("users/{username}/followers")
    fun getUserFollower(
        @Header("Authorization") authorization: String,
        @Path("username") username: String?
    ): Call<FollowingAndFollowerListResponse>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Header("Authorization") authorization: String,
        @Path("username") username: String?
    ): Call<FollowingAndFollowerListResponse>
}