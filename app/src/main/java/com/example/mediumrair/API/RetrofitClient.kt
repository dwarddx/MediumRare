package com.example.mediumrair.API

import com.example.mediumrair.model.Login
import com.example.mediumrair.model.User
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("auth/login")
    fun login(@Body info: Login): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("users")
    fun registerUser(
        @Body info: User
    ): retrofit2.Call<ResponseBody>
}
class RetrofitInstance {
    companion object {
        val BASE_URL: String = "http://127.0.0.1:8080/"

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}