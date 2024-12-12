package com.example.hangman_gabrielperez_teoaranda

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Menu : Routes("menu")
    object Game : Routes("game")
    object Result : Routes("result/{isWin}/{timeTaken}") {
        fun createRoute(isWin: Boolean, timeTaken: Int) = "result/$isWin/$timeTaken"
    }
}