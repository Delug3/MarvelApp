package com.delug3.marvelapp.character.model

import java.util.*

class CharactersResponse {

    private var results: ArrayList<ResultsItem?>? = null


    fun getResults(): ArrayList<ResultsItem?>? {
        return results
    }

    fun setResults(results: ArrayList<ResultsItem?>?) {
        this.results = results
    }


}