package com.example.hangman_gabrielperez_teoaranda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hangman_gabrielperez_teoaranda.ui.theme.Hangman_GabrielPerez_TeoArandaTheme

class ResultScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hangman_GabrielPerez_TeoArandaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen4(
                        modifier = Modifier.padding(innerPadding),
                        isWin = true, // Cambiar dependiendo del resultado
                        timeTaken = 45 // Cambiar dependiendo del tiempo
                    )
                }
            }
        }
    }
}

@Composable
fun Screen4(modifier: Modifier, isWin: Boolean, timeTaken: Int) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (isWin) Color.White else Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Título de resultado
            Text(
                text = if (isWin) "¡Has ganado!" else "¡Has perdido!",
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )

            // Mostrar el tiempo que se ha tardado
            Text(
                text = "Tiempo: $timeTaken segundos",
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Botones para reiniciar el juego o volver al menú
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        // Acción para reiniciar el juego
                    },
                    modifier = Modifier.size(200.dp, 60.dp)
                ) {
                    Text(text = "Volver a jugar")
                }

                Button(
                    onClick = {
                        // Acción para volver al menú
                    },
                    modifier = Modifier.size(200.dp, 60.dp)
                ) {
                    Text(text = "Volver al menú")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Screen4Preview() {
    Hangman_GabrielPerez_TeoArandaTheme {
        Screen4(modifier = Modifier, isWin = true, timeTaken = 45)
    }
}
