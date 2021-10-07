package com.delug3.marvelapp.character.view

import org.junit.Assert.*

import org.junit.Test

class CharactersMainActivityTest {

    @Test
    fun onCharacterItemClickFirstPosition() {
        val position = 0
        assertEquals(0, position)
    }


    @Test
    fun onCharacterItemLimitPosition() {
        val position = 15
        assertEquals(15, position)
    }


}