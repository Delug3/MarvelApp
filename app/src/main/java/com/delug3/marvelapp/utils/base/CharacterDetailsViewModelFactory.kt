package com.delug3.marvelapp.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.delug3.marvelapp.character.repository.CharacterApiService
import com.delug3.marvelapp.data.repository.CharactersNetworkDataSource
import com.delug3.marvelapp.ui.viewmodels.CharacterDetailViewModel


class CharacterDetailsViewModelFactory(private val characterApiService: CharacterApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) {
            return CharacterDetailViewModel(CharactersNetworkDataSource(characterApiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}