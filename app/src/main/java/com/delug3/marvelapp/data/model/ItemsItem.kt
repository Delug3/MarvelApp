package com.delug3.marvelapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ItemsItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("resourceURI")
    val resourceURI: String? = null
)