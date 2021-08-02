package com.delug3.marvelapp.character.repository

import com.delug3.marvelapp.character.model.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {

    @GET("/v1/public/characters")
    fun getEpisodes(
        @Query("ts") ts: String?,
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?
    ): Call<CharactersResponse?>?
}