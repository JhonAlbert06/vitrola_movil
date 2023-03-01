package com.example.vitrolla_movil.data

import retrofit2.Response
import retrofit2.http.GET

interface SongInterfaceApi {

    @GET("/Songs")
    suspend fun getSongs(): Response<List<SongDto>>
}