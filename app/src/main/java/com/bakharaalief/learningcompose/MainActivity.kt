package com.bakharaalief.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme

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
            MaterialTheme {
                Navigation(listIdol)
            }
        }
    }
}