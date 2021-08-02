package com.delug3.marvelapp.character.model

import com.google.gson.annotations.SerializedName

data class UrlsItem(

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)