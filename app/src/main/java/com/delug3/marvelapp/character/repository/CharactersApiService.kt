package com.delug3.marvelapp.character.repository

import android.provider.SyncStateContract
import com.delug3.marvelapp.character.model.MarvelResponse
import com.delug3.marvelapp.common.utilities.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: String?,
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: String = Constants.LIMIT,
        @Query("offset") offset: Int
    ): MarvelResponse

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterDetails(
        @Path(value = "characterId") characterId: Int,
        @Query("ts") ts: String?,
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?
    ): MarvelResponse

}