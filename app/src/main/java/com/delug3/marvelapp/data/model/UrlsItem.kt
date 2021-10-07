package com.delug3.marvelapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "urls_item")
data class UrlsItem(

    @field:SerializedName("type")
    @ColumnInfo(name = "type")
    val type: String? = null,

    @field:SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String? = null,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "urls_id")
    var urlsId: Int? = null
)