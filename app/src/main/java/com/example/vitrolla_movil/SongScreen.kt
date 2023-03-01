package com.example.vitrolla_movil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vitrolla_movil.data.SongDto

@Composable
fun SongScreen(
    viewModel:SongViewModel = hiltViewModel()
){
    Scaffold(topBar = { TopBar() }) {
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
                        .fillMaxWidth(0.90f)
                        .padding(16.dp)
                    ){
                        ImageCard(
                            painter = painterResource(id = returnImg(song.name)),
                            contentDescription = "Image",
                            title = song.name,
                            // Se pasa el song que se lista
                            song = song,
                            ) {

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
        // OnClick
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


fun returnImg(name: String): Int {

    if (name == "Bohemian Rhapsody"){
        return R.drawable.bohemian_rhapsody;
    }

    if (name == "Stairway to Heaven"){
        return R.drawable.stairway_to_heaven;
    }

    if (name == "Hotel California"){
        return R.drawable.hotel_california;
    }

    if (name == "November Rain"){
        return R.drawable.november_rain;
    }

    if (name == "Don't Stop Believin'"){
        return R.drawable.don_t_stop_believin_;
    }

    if (name == "Sweet Child o' Mine"){
        return R.drawable.sweet_child_o__mine;
    }

    if (name == "Free Bird"){
        return R.drawable.free_bird;
    }

    if (name == "Dream On"){
        return R.drawable.dream_on;
    }

    if (name == "Knockin' on Heaven's Door"){
        return R.drawable.knockin__on_heaven_s_door;
    }

    if (name == "Livin' on a Prayer"){
        return R.drawable.livin__on_a_prayer;
    }


    return R.drawable.bohemian_rhapsody
}