package com.example.vitrolla_movil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitrolla_movil.data.SongDto
import com.example.vitrolla_movil.data.SongInterfaceApi
import com.example.vitrolla_movil.data.SongRepositori
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

data class SongsListUiState(
    val songs: List<SongDto> = emptyList()
)


@HiltViewModel
class SongViewModel @Inject constructor(
    private  val api: SongRepositori
) : ViewModel() {

    private val _uiState = MutableStateFlow(SongsListUiState())
    val uiState : StateFlow<SongsListUiState> = _uiState.asStateFlow()

    var message = ""
    var istrue = true

    init {
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    it.copy(songs = api.getSongs())
                }catch (ioe: IOException){
                    it.copy(songs = emptyList())
                }
            }
        }
    }

    fun createSong(song: SongDto) {
        viewModelScope.launch {
            try {
                api.createSong(song)
                message = showSnackbar("Cancion enviada a la PlayList Exitosamente")
                istrue = false
            } catch (ioe: IOException) {
                message = showSnackbar("Error: $ioe Al enviar la camcion")
                istrue = false
            }
        }
    }

    private fun showSnackbar(message: String): String {
        return message
    }

}