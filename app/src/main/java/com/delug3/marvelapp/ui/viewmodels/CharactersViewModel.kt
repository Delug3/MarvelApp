package com.delug3.marvelapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delug3.marvelapp.data.model.ResultsItem
import com.delug3.marvelapp.data.model.ResultsWithUrls
import com.delug3.marvelapp.data.model.UrlsItem
import com.delug3.marvelapp.data.repository.CharactersDatabaseDataSource
import com.delug3.marvelapp.data.repository.CharactersNetworkDataSource


import com.delug3.marvelapp.utils.Constants
import kotlinx.coroutines.launch

class CharactersViewModel(private val charactersNetworkDataSource: CharactersNetworkDataSource, private val charactersDatabaseDataSource: CharactersDatabaseDataSource) : ViewModel() {
    private var offSet: Int = 0

    /**
     * This method return a list from an endpoint
     * @param MutableLiveData<List<ResultsItem>>  mutable list where we store the
     * data that is coming from the endpoint
     * @return  characters list
     */
    @Suppress("UNCHECKED_CAST")
    fun fetchCharacters(): MutableLiveData<List<ResultsItem>> {
        val charactersList: MutableLiveData<List<ResultsItem>> =
            MutableLiveData<List<ResultsItem>>()
        viewModelScope.launch {
            try {
                val response = charactersNetworkDataSource.getCharacters(Constants.timeStamp,
                    Constants.PUBLIC_KEY,
                    Constants.hashKey(),
                    Constants.LIMIT,
                    offSet)

                charactersList.value = response.data?.results as List<ResultsItem>?
                insertCharactersInRoomDatabase(charactersList.value)

                val urls =  response.data?.results as List<ResultsItem>
                setUrlElementsIntoDatabase(urls)


            } catch (exception: Exception) {
                println("Handle $exception")
            }
        }
        return charactersList
    }

    private suspend fun setUrlElementsIntoDatabase(urls: List<ResultsItem>) {
        urls.forEach {
            insertUrlsInRoomDatabase(it.urls)
        }
    }

    private suspend fun insertUrlsInRoomDatabase(urls: List<UrlsItem?>?) {
        charactersDatabaseDataSource.insertAllUrls(urls)
    }

    private suspend fun insertCharactersInRoomDatabase(characterList: List<ResultsItem>?) {
        charactersDatabaseDataSource.insertAllCharacters(characterList)
    }

    fun updateOffset() {
        offSet += 15
    }

}