package com.bakharaalief.learningcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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