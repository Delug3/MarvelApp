package com.delug3.marvelapp.data.repository

import androidx.annotation.WorkerThread
import com.delug3.marvelapp.data.model.ResultsItem
import com.delug3.marvelapp.data.model.ResultsWithUrls
import com.delug3.marvelapp.data.model.UrlsItem

import com.delug3.marvelapp.data.persistence.dao.CharactersDao

data class CharactersDatabaseDataSource(private val charactersDao: CharactersDao){


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAllCharacters(charactersRoomList: List<ResultsItem?>?) {
        charactersDao.insertAllCharacters(charactersRoomList)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAllUrls(urlsRoomList: List<UrlsItem?>?) {
        charactersDao.insertAllUrls(urlsRoomList)
    }

}
