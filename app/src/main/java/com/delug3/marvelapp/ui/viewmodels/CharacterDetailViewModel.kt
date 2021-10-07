package com.delug3.marvelapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delug3.marvelapp.data.model.ItemsItem
import com.delug3.marvelapp.data.model.ResultsItem
import com.delug3.marvelapp.data.model.Thumbnail
import com.delug3.marvelapp.data.repository.CharactersNetworkDataSource
import com.delug3.marvelapp.utils.Constants
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val charactersNetworkDataSource: CharactersNetworkDataSource) : ViewModel() {
    private var id: Int = 0
    val characterName: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    val characterDescription: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    val characterThumbnail: MutableLiveData<Thumbnail> by lazy { MutableLiveData<Thumbnail>() }
    val characterAppearanceInComics: MutableLiveData<List<ItemsItem>?> by lazy { MutableLiveData<List<ItemsItem>?>() }


    /**This method set up the character id that we will use in the coroutine
     *
     */
    fun setCharacterId(characterId: Int) {
        this.id = characterId
    }


    /**This method obtain the details of a single character from an endpoint
     * We just have to specify the character id
     */
    @Suppress("UNCHECKED_CAST")
    fun getCharacterDetails() {
        viewModelScope.launch {
            try {
                val response = charactersNetworkDataSource.getCharacterDetails(id,
                    Constants.timeStamp,
                    Constants.PUBLIC_KEY,
                    Constants.hashKey())

                val character = response.data?.results as List<ResultsItem>?
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

