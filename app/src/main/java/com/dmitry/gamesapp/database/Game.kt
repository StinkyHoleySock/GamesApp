package com.dmitry.gamesapp.database

import com.dmitry.gamesapp.R

// Класс данных, содержит поля информации об игре (заполненные данные - по умолчанию)
data class Game (
    val id: Int = 1,
    val title: String = "Title",
    val date: String = "01.01.2000",
    val studio: String = "Studio games",
    val genre: String = "Game genre",
    val rating: String = "16+",
    val description: String = "Short description of the game",
    val image: Int = R.drawable.ic_game_image
)