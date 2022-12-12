package com.example.mediumrair.retrofit

import com.example.mediumrair.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointAPI {
    @GET("auth/user")
    suspend fun getUserData(
        @Query("Authorization") Bearer:String
    ): Response<User>
}