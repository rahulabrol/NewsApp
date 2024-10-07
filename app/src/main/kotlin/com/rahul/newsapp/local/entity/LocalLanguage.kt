package com.rahul.newsapp.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by abrol at 25/08/24.
 */
@Entity(tableName = "Language")
data class LocalLanguage(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = "",
    @ColumnInfo(name = "name") val name: String = ""
)
