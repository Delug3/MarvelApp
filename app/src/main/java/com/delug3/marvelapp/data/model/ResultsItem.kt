package com.delug3.marvelapp.data.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters_table")
data class ResultsItem(
    @Embedded(prefix = "thumbnail_")
    @field:SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null,

    @Ignore
    @field:SerializedName("stories")
    var stories: Stories? = null,

    @Ignore
    @field:SerializedName("series")
    var series: Series? = null,

    @Ignore
    @field:SerializedName("comics")
    var comics: Comics? = null,

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("description")
    var description: String? = null,

    @field:SerializedName("modified")
    var modified: String? = null,


    @field:SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @field:SerializedName("resourceURI")
    var resourceURI: String? = null,

    @Ignore
    @field:SerializedName("events")
    var events: Events? = null


)