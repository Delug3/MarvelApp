package com.delug3.marvelapp.character.viewmodels

import com.delug3.marvelapp.data.model.ResultsItem
import org.hamcrest.core.Is
import org.junit.Assert.*

import org.junit.Test

class CharactersViewModelTest {

    @Test
    fun fetchCharacters() {


        val characters = listOf(
            ResultsItem(name = "Test 1"),
            ResultsItem(name = "Test 2"),
            ResultsItem(name = "Test 3")
        )

        assertThat(characters[1], Is.`is`("Test 1"))
    }

    @Test
    fun updateOffset() {
    }
}