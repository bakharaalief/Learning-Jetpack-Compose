package com.bakharaalief.learningcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreen(datas : List<Idol>, navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = { Text(text = "Twice") },
            )
        },
    ) {
        LazyColumn{
            items(items = datas){ data ->
                ImageCard(
                    idol = data,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ImageCard(
    idol : Idol,
    navController: NavController
){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                navController.navigate("detail/" + idol.id)
            },
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
    ){
        //box stack 1
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            //image
            GlideImage(
                imageModel = idol.photo,
                circularReveal = CircularReveal(duration = 250),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
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
                    Text(
                        text = idol.stageName,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
            }
        }
    }
}