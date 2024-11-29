package com.example.hangman_gabrielperez_teoaranda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hangman_gabrielperez_teoaranda.ui.theme.Hangman_GabrielPerez_TeoArandaTheme

class MenuScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hangman_GabrielPerez_TeoArandaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen2(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Screen2(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.background(Color.White)) // Caja blanca
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre los elementos
        ) {
            Image(
                painter = painterResource(id = R.drawable.screen1),
                contentDescription = "Logo del juego",
                modifier = Modifier.size(190.dp)
            )
            Button(
                onClick = { },
                modifier = Modifier.size(200.dp, 60.dp)
            ) {
                Text(text = "Jugar")
            }
            Button(
                onClick = { },
                modifier = Modifier.size(200.dp, 60.dp)
            ) {
                Text(text = "Ayuda")
            }

        }
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewSplashScreenContent() {
    Screen2()
}