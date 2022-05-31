package com.dmitry.gamesapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmitry.gamesapp.R

// Класс данных, содержит поля информации об игре (заполненные данные - по умолчанию)
@Entity(tableName = "game_table")
data class Game (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: String,
    val studio: String,
    val genre: String,
    val rating: String,
    val description: String,
    val image: Int
)