package com.kotlinapps.retrofitexample

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServices {
    @GET("categories")
    fun getMovie(): Call<List<Category>>

    companion object {
        val BASE_URL = "https://aghour-services-backend.herokuapp.com/api/"
        fun create(): ApiServices {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofitBuilder.create(ApiServices::class.java)
        }
    }
}