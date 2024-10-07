package com.rahul.newsapp.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by abrol at 25/08/24.
 */
@Entity(tableName = "NewsSource")
data class LocalNewsSource(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "sourceId") val sourceId: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "url") val url: String = "",
    @ColumnInfo(name = "category") val category: String = "",
    @ColumnInfo(name = "language") val language: String = "",
    @ColumnInfo(name = "country") val country: String = ""
)
