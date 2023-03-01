package com.example.vitrolla_movil.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

class SongRepositori @Inject constructor(
    private val api: SongInterfaceApi
) {
    suspend fun getSongs(): List<SongDto> {
        return withContext(Dispatchers.IO){
            val resonse = api.getSongs()
            resonse.body()?: emptyList()
        }
    }

    suspend fun createSong(song: SongDto): SongDto? {
        return withContext(Dispatchers.IO) {
            val res = api.createSong(song)
            res.body()
        }
    }
}