package com.delug3.marvelapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Comics(

    @field:SerializedName("collectionURI")
    val collectionURI: String? = null,

    @field:SerializedName("available")
    val available: Int? = null,

    @field:SerializedName("returned")
    val returned: Int? = null,

    @Embedded(prefix = "items_")
    @field:SerializedName("items")
    val items: List<ItemsItem?>? = null
)