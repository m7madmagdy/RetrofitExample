package com.kotlinapps.retrofitexample

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServices {
    @GET("photos")
    fun getMovie(): Call<List<Category>>

    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        fun create(): ApiServices {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofitBuilder.create(ApiServices::class.java)
        }
    }
}