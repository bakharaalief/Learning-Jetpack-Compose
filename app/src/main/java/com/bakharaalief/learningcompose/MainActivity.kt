package com.bakharaalief.learningcompose

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bakharaalief.learningcompose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                //row 1
                Row() {
                    Text(
                        text = "Hello",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )

                    Text(
                        text = "All !",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                //row2
                Row() {
                    Text(
                        text = "I am",
                        fontSize = 40.sp
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Bakhara Alief",
                        fontSize = 40.sp
                    )
                }
            }
        }
    }
}