package com.dmitry.gamesapp

// Класс данных, содержит поля информации об игре
data class Game (
    val id: Int,
    val title: String,
    val date: String,
    val studio: String,
    val genre: String,
    val rating: String,
    val description: String
)