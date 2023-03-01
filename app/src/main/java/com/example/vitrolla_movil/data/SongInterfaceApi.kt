package com.example.vitrolla_movil.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SongInterfaceApi {

    @GET("/Songs")
    suspend fun getSongs(): Response<List<SongDto>>

    @POST("/Songs/List")
    suspend fun createSong(@Body song: SongDto): Response<SongDto>

}