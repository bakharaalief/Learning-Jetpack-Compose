package com.bakharaalief.learningcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun DetailScreen(navController: NavController, idIdol : Int){
    //get data
    val context = LocalContext.current

    //get link photo data
    val linkPhoto = context.resources.getStringArray(R.array.link_photo)[idIdol]
    val name = context.resources.getStringArray(R.array.name)[idIdol]
    val hangulKanjiName = context.resources.getStringArray(R.array.hangul_kanji_name)[idIdol]
    val stageName = context.resources.getStringArray(R.array.stage_name)[idIdol]

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = { Text(text = stageName) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "StarButton")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            //idol image
            GlideImage(
                imageModel = linkPhoto,
                circularReveal = CircularReveal(duration = 250),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(380.dp)
                    .fillMaxWidth()
            )

            //idol name
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )

            //hangul kanji name
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp),
                color = Color.Gray,
                text = hangulKanjiName,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            //idol desc
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp),
                text = stringResource(id = R.string.idol_content),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    }
}