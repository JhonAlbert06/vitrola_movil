package com.example.vitrolla_movil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.vitrolla_movil.data.SongDto
import okio.IOException

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SongScreen(
    viewModel:SongViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`

    Scaffold(scaffoldState = scaffoldState, topBar = { TopBar() }) {
        val state by viewModel.uiState.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.width(30.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(state.songs){ song ->

                    Box(modifier = Modifier
                        .fillMaxWidth(0.98f)
                        .padding(8.dp)
                    ){
                        ImageCard(
                            painter = rememberImagePainter("http://192.168.1.20:8000/public/images/${song.name}.jpeg"),
                            contentDescription = "Image",
                            title = song.name,
                            song = song,
                            onClick = { try {
                                viewModel.createSong(song)
                            } catch (ioe: IOException){
                                viewModel.message = ioe.toString()
                            } }
                        )

                        if (!viewModel.istrue){
                            ShowMesseage(message = viewModel.message, scaffoldState = scaffoldState)
                        }

                    }

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        color = Color.White
                    )

                }
            }
        }
    }
}


@Composable
fun ShowMesseage(message: String, scaffoldState: ScaffoldState) {
    LaunchedEffect(scaffoldState.snackbarHostState) {
        scaffoldState.snackbarHostState.showSnackbar(
            message = message,
            actionLabel = "Añade otra Mas"
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier,
    song: SongDto,
    onClick: () -> Unit
){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
        onClick = onClick
    ){
        Box(modifier = Modifier
            .height(200.dp)
            .fillMaxSize()){
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Column {
                    Row {
                        Text(song.artist, style = TextStyle(color = Color.Cyan, fontSize = 16.sp ))
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(song.genre, style = TextStyle(color = Color.Cyan, fontSize = 16.sp ))
                    }
                    Text(title, style = TextStyle(color = Color.Red, fontSize = 16.sp, fontWeight = FontWeight.Bold ))
                }
            }
        }
    }
}