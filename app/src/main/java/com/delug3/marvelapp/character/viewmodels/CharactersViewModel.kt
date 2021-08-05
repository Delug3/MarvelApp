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

    fun fetchCharacters(): MutableLiveData<List<ResultsItem>> {
        val charactersList: MutableLiveData<List<ResultsItem>> =
            MutableLiveData<List<ResultsItem>>()
        viewModelScope.launch {
            try {
                val response = getClientPublic?.create(CharacterApiService::class.java)
                    ?.getCharacters(Constants.timeStamp, Constants.PUBLIC_KEY, Constants.hashKey())

                charactersList.value = response?.data?.results as List<ResultsItem>?

            } catch (exception: Exception) {
                println("Handle $exception")
            }
        }
        return charactersList
    }

}