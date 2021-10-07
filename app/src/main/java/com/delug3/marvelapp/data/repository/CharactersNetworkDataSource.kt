package com.delug3.marvelapp.data.repository

import com.delug3.marvelapp.character.repository.CharacterApiService

class CharactersNetworkDataSource(private val characterApiService: CharacterApiService) {

    suspend fun getCharacters(
        ts: String?,
        apikey: String?,
        hash: String?,
        limit: String,
        offset: Int
    ) = characterApiService.getCharacters(ts, apikey, hash, limit, offset)

    suspend fun getCharacterDetails(
        characterId: Int,
        ts: String?,
        apikey: String?,
        hash: String?
    ) = characterApiService.getCharacterDetails(characterId, ts, apikey, hash)
}