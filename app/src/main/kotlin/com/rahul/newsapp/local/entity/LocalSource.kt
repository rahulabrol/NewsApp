package com.rahul.newsapp.local.entity

import androidx.room.ColumnInfo

data class LocalSource(
    @ColumnInfo(name = "sourceId") var sourceId: String = "",
    @ColumnInfo(name = "name") var name: String = ""
)
