package com.delug3.marvelapp.data.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.delug3.marvelapp.data.model.ResultsItem
import com.delug3.marvelapp.data.model.UrlsItem


@Dao
interface CharactersDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<ResultsItem?>?)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUrls(characters: List<UrlsItem?>?)


    @Query("SELECT * FROM characters_table")
    fun getCharacters(): LiveData<List<ResultsItem>>
}