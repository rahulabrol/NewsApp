package com.rahul.newsapp.local

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by abrol at 31/08/25.
 */
@SuppressLint("NewApi")
object OffsetDateTimeConverter {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(value: OffsetDateTime?): String? = value?.format(formatter)

    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String?): OffsetDateTime? = value?.let { OffsetDateTime.parse(it, formatter) }
}
