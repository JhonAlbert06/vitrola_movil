package com.example.vitrolla_movil

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitrolla_movil.ui.theme.Vitrolla_movilTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Vitrolla_movilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(topBar = { TopBar() }) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Spacer(modifier = Modifier.width(30.dp))
                            infoBox(name = "Bohemian Rhapsody", genre = "Rock", artist = "Queen")

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar(){
    TopAppBar(
        title = { Text(text = "Vitrola Movil")},
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Icono de Menu")
            }
        },
        backgroundColor = Color.Black,
        contentColor = Color.White
    )
}

@Composable
fun infoBox(name: String, genre: String, artist: String) {
    Card(
        modifier = Modifier
            .height(370.dp)
            .size(350.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = 10.dp,
        backgroundColor = Color.LightGray,
        //onClick =
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(modifier = Modifier.height(250.dp).width(320.dp),painter = painterResource(id = R.drawable.bohemian_rhapsody) , contentDescription = "Bohemian Rhapsody" )

            Text(text = genre, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = name, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Red)
            Text(text = artist, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Vitrolla_movilTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(topBar = { TopBar() }) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.width(30.dp))
                    infoBox(name = "Bohemian Rhapsody", genre = "Rock", artist = "Queen")
                    
                }
            }
        }
    }
}