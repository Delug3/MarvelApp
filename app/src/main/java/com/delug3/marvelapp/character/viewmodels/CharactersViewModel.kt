package com.delug3.marvelapp.character.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delug3.marvelapp.character.model.ResultsItem
import com.delug3.marvelapp.character.repository.CharacterApiService
import com.delug3.marvelapp.common.network.RetrofitClient.getClientPublic

import com.delug3.marvelapp.common.utilities.Constants
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {
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
                val response = getClientPublic?.create(CharacterApiService::class.java)
                    ?.getCharacters(Constants.timeStamp,
                        Constants.PUBLIC_KEY,
                        Constants.hashKey(),
                        Constants.LIMIT,
                        offSet)

                charactersList.value = response?.data?.results as List<ResultsItem>?

            } catch (exception: Exception) {
                println("Handle $exception")
            }
        }
        return charactersList
    }

    fun updateOffset() {
        offSet += 15
    }

}