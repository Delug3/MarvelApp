package com.delug3.marvelapp.data.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.delug3.marvelapp.data.model.ResultsItem
import com.delug3.marvelapp.data.model.ResultsWithUrls


@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<ResultsItem?>?)

    @Query("SELECT * FROM characters_table")
    fun getCharacters(): LiveData<List<ResultsItem>>
}