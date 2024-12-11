package com.example.hangman_gabrielperez_teoaranda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hangman_gabrielperez_teoaranda.ui.theme.Hangman_GabrielPerez_TeoArandaTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

class GameScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hangman_GabrielPerez_TeoArandaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Game(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Game(modifier: Modifier = Modifier) {
    var word = "KOTLIN"
    var guessedWord = remember { mutableStateOf("_ ".repeat(word.length)) }
    var incorrectGuesses = remember { mutableStateOf(0) }
    var letters = remember { mutableStateOf(('A'..'Z').toList()) }

    // Dividir las letras en filas de 5
    val letterRows = letters.value.chunked(5)

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Ahorcado", style = MaterialTheme.typography.headlineMedium)

            // Ahorcado Image (reemplazar con imágenes del ahorcado)
            Image(
                painter = painterResource(id = R.drawable.screen1),
                contentDescription = "Hangman",
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = "Palabra: ${guessedWord.value}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp
            )

            // Crear una fila para cada grupo de letras
            letterRows.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    row.forEach { letter ->
                        Button(
                            onClick = {
                                if (word.contains(letter)) {
                                    guessedWord.value = updateGuessedWord(word, guessedWord.value, letter)
                                } else {
                                    incorrectGuesses.value += 1
                                }
                                letters.value = letters.value.filter { it != letter } // Desactivar letras usadas
                            },
                            modifier = Modifier.size(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray
                            )
                        ) {
                            Text(text = letter.toString(), color = Color.White)
                        }
                    }
                }
            }

            Text(
                text = "Intentos restantes: ${6 - incorrectGuesses.value}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp
            )

            if (incorrectGuesses.value >= 6) {
                Text(text = "¡Has perdido! La palabra era: $word", color = Color.Red)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    // Reiniciar el juego
                    guessedWord.value = "_ ".repeat(word.length)
                    incorrectGuesses.value = 0
                    letters.value = ('A'..'Z').toList()
                },
                modifier = Modifier.size(200.dp, 60.dp)
            ) {
                Text(text = "Reiniciar juego")
            }
        }
    }
}

fun updateGuessedWord(word: String, guessedWord: String, letter: Char): String {
    val wordChars = word.toCharArray()
    val guessedChars = guessedWord.toCharArray()

    for (i in wordChars.indices) {
        if (wordChars[i] == letter) {
            guessedChars[i * 2] = letter
        }
    }
    return String(guessedChars)
}

@Preview(showBackground = true)
@Composable
fun PreviewGameScreen() {
    Game()
}
