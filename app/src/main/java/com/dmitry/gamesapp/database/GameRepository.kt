package com.dmitry.gamesapp.database

import androidx.lifecycle.LiveData

class GameRepository(private val gameDao: GameDao) {

    val readData: LiveData<List<Game>> = gameDao.readData()

    suspend fun addGame(game: Game){
        gameDao.addGame(game)
    }
}