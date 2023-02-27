package com.example.vitrolla_movil.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongDto(
    val name: String,
    val genre: String,
    val length: String,
    val artist: String
)
