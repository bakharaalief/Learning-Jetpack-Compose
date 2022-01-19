package com.bakharaalief.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listIdol = arrayListOf<Idol>()

        //looping 9 x
        for (i in 0 until 9){
            val data = Idol(
                id = i,
                photo = resources.getStringArray(R.array.link_photo)[i],
                stageName = resources.getStringArray(R.array.stage_name)[i],
            )

            listIdol.add(data)
        }

        //place where view build
        setContent {
            Navigation(listIdol)
        }
    }
}

@Composable
fun Navigation(data : List<Idol>){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){

        //home screen
        composable(route = "home"){
            HomeScreen(datas = data, navController)
        }

        //detail screen
        composable(
            route = "detail/{idolId}",
            arguments = listOf(navArgument("idolId"){ type = NavType.IntType })
        ){
            DetailScreen(navController = navController, it.arguments?.getInt("idolId")!!)
        }
    }
}

@Composable
fun HomeScreen(datas : List<Idol>, navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = { Text(text = "Twice") },
            )
        }
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
                title = { Text(text = stageName)},
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
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
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                text = name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )

            //hangul kanji name
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).padding(bottom = 10.dp),
                color = Color.Gray,
                text = hangulKanjiName,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            //idol desc
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).padding(bottom = 10.dp),
                text = stringResource(id = R.string.idol_content),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    }
}