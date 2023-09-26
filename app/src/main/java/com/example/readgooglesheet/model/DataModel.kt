package com.example.readgooglesheet.model

data class DataModel(
    val majorDimension: String? = null,
    val range: String? = null,
    val values: List<List<String?>?>? = null
)