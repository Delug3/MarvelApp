package com.delug3.marvelapp.character.view

import com.delug3.marvelapp.character.model.ResultsItem
import java.util.*

interface Characters {

    interface View {
        fun setCharactersList(charactersList: ArrayList<ResultsItem?>?)
    }

    interface Presenter {

    }

    interface Model
}