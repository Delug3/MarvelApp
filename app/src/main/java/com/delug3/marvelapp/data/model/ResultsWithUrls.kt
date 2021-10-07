package com.delug3.marvelapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation


data class ResultsWithUrls(

    @Embedded
    val resultItems: ResultsItem,
    @Relation(
        parentColumn = "id",
        entityColumn = "urls_id"
    )
    val urlsItem: List<UrlsItem?>?

)
