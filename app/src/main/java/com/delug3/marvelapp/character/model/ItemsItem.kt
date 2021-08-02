package com.delug3.marvelapp.character.model

import com.google.gson.annotations.SerializedName

data class ItemsItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("resourceURI")
    val resourceURI: String? = null
)