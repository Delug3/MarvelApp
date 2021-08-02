package com.delug3.marvelapp.common.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient

private const val BASE_URL = "https://gateway.marvel.com/"

fun getClientPublic(): Retrofit? {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
