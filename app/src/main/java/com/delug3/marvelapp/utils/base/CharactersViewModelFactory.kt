package com.delug3.marvelapp.utils.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.delug3.marvelapp.character.repository.CharacterApiService
import com.delug3.marvelapp.data.repository.CharactersDatabaseDataSource
import com.delug3.marvelapp.data.repository.CharactersNetworkDataSource
import com.delug3.marvelapp.ui.viewmodels.CharactersViewModel

class CharactersViewModelFactory(private val characterApiService: CharacterApiService, private val charactersDatabaseDataSource: CharactersDatabaseDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            return CharactersViewModel(CharactersNetworkDataSource(characterApiService), charactersDatabaseDataSource) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}