package com.rahul.newsapp.utils

import com.rahul.newsapp.local.entity.LocalCountry
import com.rahul.newsapp.local.entity.LocalLanguage

object Constants {

    const val COUNTRY = "us"

    val LocalLANGUAGES = listOf(
        LocalLanguage("ar", "Arabic"),
        LocalLanguage("de", "German"),
        LocalLanguage("en", "English"),
        LocalLanguage("es", "Spanish"),
        LocalLanguage("fr", "French"),
        LocalLanguage("he", "Hebrew"),
        LocalLanguage("it", "Italian"),
        LocalLanguage("nl", "Dutch"),
        LocalLanguage("no", "Norwegian"),
        LocalLanguage("pt", "Portuguese"),
        LocalLanguage("ru", "Russian"),
        LocalLanguage("sv", "Swedish"),
        LocalLanguage("zh", "Chinese")
    )

    val COUNTRIES = listOf(
        LocalCountry("ae", "United Arab Emirates"),
        LocalCountry("ar", "Argentina"),
        LocalCountry("at", "Austria"),
        LocalCountry("be", "Belgium"),
        LocalCountry("bg", "Bulgaria"),
        LocalCountry("br", "Brazil"),
        LocalCountry("ca", "Canada"),
        LocalCountry("ch", "Switzerland"),
        LocalCountry("cn", "China"),
        LocalCountry("co", "Colombia"),
        LocalCountry("cu", "Cuba"),
        LocalCountry("cz", "Czechia"),
        LocalCountry("de", "Germany"),
        LocalCountry("eg", "Egypt"),
        LocalCountry("fr", "France"),
        LocalCountry("gb", "United Kingdom of Great Britain and Northern Ireland"),
        LocalCountry("gr", "Greece"),
        LocalCountry("hk", "Hong Kong"),
        LocalCountry("hu", "Hungary"),
        LocalCountry("id", "Indonesia"),
        LocalCountry("ie", "Ireland"),
        LocalCountry("il", "Israel"),
        LocalCountry("in", "India"),
        LocalCountry("it", "Italy"),
        LocalCountry("jp", "Japan"),
        LocalCountry("kr", "Korea"),
        LocalCountry("lt", "Lithuania"),
        LocalCountry("lv", "Latvia"),
        LocalCountry("ma", "Morocco"),
        LocalCountry("mx", "Mexico"),
        LocalCountry("my", "Malaysia"),
        LocalCountry("ng", "Nigeria"),
        LocalCountry("nl", "Netherlands"),
        LocalCountry("no", "Norway"),
        LocalCountry("nz", "New Zealand"),
        LocalCountry("ph", "Philippines"),
        LocalCountry("pl", "Poland"),
        LocalCountry("pt", "Portugal"),
        LocalCountry("ro", "Romania"),
        LocalCountry("rs", "Serbia"),
        LocalCountry("ru", "Russian Federation"),
        LocalCountry("sa", "Saudi Arabia"),
        LocalCountry("se", "Sweden"),
        LocalCountry("sg", "Singapore"),
        LocalCountry("si", "Slovenia"),
        LocalCountry("sk", "Slovakia"),
        LocalCountry("th", "Thailand"),
        LocalCountry("tr", "Turkiye"),
        LocalCountry("tw", "Taiwan, Province of China"),
        LocalCountry("ua", "Ukraine"),
        LocalCountry("us", "United States of America"),
        LocalCountry("ve", "Venezuela"),
        LocalCountry("za", "South Africa")
    )
}
