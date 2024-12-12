package com.example.hangman_gabrielperez_teoaranda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.hangman_gabrielperez_teoaranda.ui.theme.Hangman_GabrielPerez_TeoArandaTheme
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.os.Handler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.navigation.NavType
import androidx.navigation.navArgument



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hangman_GabrielPerez_TeoArandaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.Splash.route) {
                    composable(Routes.Splash.route) {
                        SplashScreenContent(navController = navController)
                    }
                    composable(Routes.Menu.route) {
                        Screen2(navController = navController)
                    }
                    composable(Routes.Game.route) {
                        Game(navController = navController)
                    }
                    composable(
                        route = Routes.Result.route,
                        arguments = listOf(
                            navArgument("isWin") { type = NavType.BoolType },
                            navArgument("timeTaken") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val isWin = backStackEntry.arguments?.getBoolean("isWin") ?: false
                        val timeTaken = backStackEntry.arguments?.getInt("timeTaken") ?: 0
                        Screen4(
                            modifier = Modifier,
                            isWin = isWin,
                            timeTaken = timeTaken,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreenContent(navController: NavController, modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(5000)
        navController.navigate(Routes.Menu.route)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.hangman6), // Cambia por tu logo
            contentDescription = "Logo del juego",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Cargando el juego...",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = ComposeColor.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenContentPreview() {
    Hangman_GabrielPerez_TeoArandaTheme {
        SplashScreenContent(navController = rememberNavController())
    }
}