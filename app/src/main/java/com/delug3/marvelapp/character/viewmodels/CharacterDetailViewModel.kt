package com.delug3.marvelapp.character.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delug3.marvelapp.character.model.ItemsItem
import com.delug3.marvelapp.character.model.ResultsItem
import com.delug3.marvelapp.character.model.Thumbnail
import com.delug3.marvelapp.character.repository.CharacterApiService
import com.delug3.marvelapp.common.network.RetrofitClient
import com.delug3.marvelapp.common.utilities.Constants
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {
    private var id: Int = 0
    val characterName: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    val characterDescription: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    val characterThumbnail: MutableLiveData<Thumbnail> by lazy { MutableLiveData<Thumbnail>() }
    val characterAppearanceInComics: MutableLiveData<List<ItemsItem>?> by lazy { MutableLiveData<List<ItemsItem>?>() }

    fun setCharacterId(characterId: Int) {
        this.id = characterId
    }

    fun getCharacterDetails() {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitClient.getClientPublic?.create(CharacterApiService::class.java)
                        ?.getCharacterDetails(
                            id,
                            Constants.timeStamp,
                            Constants.PUBLIC_KEY,
                            Constants.hashKey()
                        )
                val character = response?.data?.results as List<ResultsItem>?
                characterName?.value = character?.first()?.name
                characterDescription?.value = character?.first()?.description
                characterThumbnail.value = character?.first()?.thumbnail
                characterAppearanceInComics.value =
                    character?.first()?.comics?.items as List<ItemsItem>?

            } catch (exception: Exception) {
                println("Handle $exception")
            }
        }
    }
}

