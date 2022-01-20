package com.bakharaalief.learningcompose

sealed class Screen(val screen : String){
    class HomeScreen : Screen(screen = "home")
    class DetailScreen : Screen(screen = "detail")
}