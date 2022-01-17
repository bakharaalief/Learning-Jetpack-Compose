package com.bakharaalief.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class Idol(val photo : Int, val title : String, val contentDesc : String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listIdol = listOf<Idol>(
            Idol(R.drawable.dahyun, "Lorem Bla bla bla 1", "wadaw"),
            Idol(R.drawable.dahyun, "Lorem Bla bla bla 2", "wadaw"),
            Idol(R.drawable.dahyun, "Lorem Bla bla bla 3", "wadaw"),
            Idol(R.drawable.dahyun, "Lorem Bla bla bla 4", "wadaw"),
            Idol(R.drawable.dahyun, "Lorem Bla bla bla 5", "wadaw"),
        )

        //place where view build
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar( backgroundColor = Color.White, title = { Text(text = "Dahyun List")})
                }
            ) {
                //create list
                ListImage(datas = listIdol)
            }
        }
    }
}

@Composable
fun ListImage(datas : List<Idol>){
    LazyColumn{
        items(items = datas){ data ->
            ImageCard(painter = painterResource(id = data.photo), title = data.title, contentDescription = data.contentDesc)
        }
    }
}


@Composable
fun ImageCard(
    painter : Painter,
    title : String,
    contentDescription : String
){
    Card(
        modifier = Modifier.padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ){
        //box stack 1
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            //image
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )

            //box stack 2
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            ) {

                //box stack 3
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}