package com.delug3.marvelapp.common.network

import com.delug3.marvelapp.common.utilities.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient

fun getClientPublic(): Retrofit? {
    return Retrofit.Builder()
        .baseUrl(Constants().BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
