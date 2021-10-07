package com.delug3.marvelapp.utils.network

import com.delug3.marvelapp.character.repository.CharacterApiService
import com.delug3.marvelapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun getClientPublic(): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                }
    val charactersApiService: CharacterApiService = getClientPublic().create(CharacterApiService::class.java)

}
