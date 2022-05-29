package com.dmitry.gamesapp

import androidx.lifecycle.ViewModel
import com.dmitry.gamesapp.database.Game

//Класс представляет собой централизованное хранилище для объектов Game
class GameListViewModel : ViewModel() {

    val games = mutableListOf<Game>()

    //Инициализация данных
    init {
        for (i in 0 until 100) {
            val game = Game()
            games += game
        }
    }
}