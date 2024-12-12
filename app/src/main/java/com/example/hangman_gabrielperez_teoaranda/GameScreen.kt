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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hangman_GabrielPerez_TeoArandaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Game(navController = rememberNavController(), modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Game(navController: NavController, modifier: Modifier = Modifier) {
    // Lista de palabras posibles
    val wordList = listOf(
        "KOTLIN",
        "ANDROID",
        "JETPACK",
        "COMPOSE",
        "DESARROLLO",
        "PROGRAMACION",
        "VARIABLE",
        "FUNCION",
        "CLASE",
        "OBJETO"
    )
    
    // Seleccionar una palabra aleatoria
    var word = remember { wordList.random() }
    var guessedWord = remember { mutableStateOf("_ ".repeat(word.length)) }
    var incorrectGuesses = remember { mutableStateOf(0) }
    var letters = remember { mutableStateOf(('A'..'Z').toList()) }
    var timeTaken = remember { mutableStateOf(0) }

    // Agregar timer
    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            timeTaken.value++
        }
    }

    // Dividir las letras en filas de 5
    val letterRows = letters.value.chunked(5)

    // Función para obtener la imagen según el número de errores
    fun getHangmanImage(incorrectGuesses: Int): Int {
        return when (incorrectGuesses) {
            0 -> R.drawable.hangman0 // Base del ahorcado
            1 -> R.drawable.hangman1 // Cabeza
            2 -> R.drawable.hangman2 // Cabeza + Torso
            3 -> R.drawable.hangman3 // Cabeza + Torso + Brazo izquierdo
            4 -> R.drawable.hangman4 // Cabeza + Torso + Ambos brazos
            5 -> R.drawable.hangman5 // Cabeza + Torso + Ambos brazos + Pierna izquierda
            6 -> R.drawable.hangman6 // Ahorcado completo
            else -> R.drawable.hangman0
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(text = "Ahorcado", style = MaterialTheme.typography.headlineMedium)

            // Actualizar la imagen según el número de errores
            Image(
                painter = painterResource(id = getHangmanImage(incorrectGuesses.value)),
                contentDescription = "Hangman state ${incorrectGuesses.value}",
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = "Palabra: ${guessedWord.value}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 24.sp
            )

            // Crear una fila para cada grupo de letras
            letterRows.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
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

            // Agregar lógica para verificar victoria o derrota
            LaunchedEffect(guessedWord.value, incorrectGuesses.value) {
                if (!guessedWord.value.contains('_')) {
                    // Victoria
                    navController.navigate(Routes.Result.createRoute(true, timeTaken.value))
                } else if (incorrectGuesses.value >= 6) {
                    // Derrota
                    navController.navigate(Routes.Result.createRoute(false, timeTaken.value))
                }
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
    Game(navController = rememberNavController())
}
