package com.dmitry.gamesapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// Класс данных, содержит поля информации об игре (заполненные данные - по умолчанию)
@Parcelize
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
    val image: String
) : Parcelable


