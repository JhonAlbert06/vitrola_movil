package com.example.vitrolla_movil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitrolla_movil.data.SongDto
import com.example.vitrolla_movil.data.SongInterfaceApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response
import javax.inject.Inject

data class SongsListUiState(
    val songs: List<SongDto> = emptyList<SongDto>()
)


@HiltViewModel
class SongViewModel @Inject constructor(
    private  val api: SongInterfaceApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(SongsListUiState())
    val uiState : StateFlow<SongsListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    it.copy(songs = api.getSongs().body() ?: emptyList())
                }catch (ioe: IOException){
                    it.copy(songs = emptyList())
                }
            }
        }
    }

}