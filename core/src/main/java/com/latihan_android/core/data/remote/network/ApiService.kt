package com.latihan_android.core.data.remote.network

import com.latihan_android.core.data.remote.response.DetailUserResponse
import com.latihan_android.core.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun getUser(@Query("q") query:String) : UserResponse

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username:String
    ) : DetailUserResponse

}